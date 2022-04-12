package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.Mail;
import com.example.server.entity.User;
import com.example.server.mapper.MailMapper;
import com.example.server.mapper.UserMapper;
import com.example.server.service.IMailService;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xueminglu
 * @since 2022-01-21
 */
@Service
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail> implements IMailService {

    @Value("${spring.mail.username}")
    private String fromName;

    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public RespBean sendMail(Mail mail) {
        //保存到数据库
        Date date = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String str = df.format(date);//获取String类型的时间
        mail.setSendTime(str);

        int i = mailMapper.insert(mail);
        boolean flag = false;


        try {
            SimpleMailMessage message = new SimpleMailMessage();
            //邮件设置
            message.setSubject(mail.getMailTitle());
            message.setText(mail.getMailText());
            message.setTo(mail.getSendTo());
            message.setFrom(fromName);
            javaMailSender.send(message);
            flag = true;

        } catch (Exception e) {
            log.error("SendEmailMessage error: ", e);
        }
        if (i == 1 && flag) {
            return RespBean.success("发送成功");
        }
        return RespBean.error("发送失败");
    }

    @Override
    @Transactional
    public RespBean sendMails(Mail mail) {
        //保存到数据库
        Date date = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String str = df.format(date);//获取String类型的时间
        boolean flag = false;
        int i=0;
        //查询邮箱列表
        List<User> userList=userMapper.selectList(new QueryWrapper<User>().select("mailbox").ne("roleId",3));
        List<String> mails = new ArrayList<>();
        for (User user:userList){
            Mail mail1=new Mail();
            mail1.setSendTo(user.getMailbox());
            mail1.setSendTime(str);
            mail1.setMailText(mail.getMailText());
            mail1.setMailTitle(mail.getMailTitle());
            mail1.setSendFrom(mail.getSendFrom());
            i+=mailMapper.insert(mail1);
            mails.add(user.getMailbox());
        }


        //发送到邮箱
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //设置发件人Email
            simpleMailMessage.setFrom(fromName);
            //设置邮件主题
            simpleMailMessage.setSubject(mail.getMailTitle());
            //设置邮件主题内容
            simpleMailMessage.setText(mail.getMailText());
            //批量发送
            String[] list= mails.toArray(new String[0]);
            simpleMailMessage.setTo(list);

            javaMailSender.send(simpleMailMessage);
            flag=true;

        } catch (Exception e) {
            log.error("SendEmailMessage error: ", e);

        }
        if (flag&&1==userList.size()) {
            return RespBean.success("群发送成功");
        }else {
            return RespBean.error("群发送失败");
        }

    }

    @Override
    public PageBean getMailbox(Integer currentPage, Integer size, String sendTo) {

        Page<Mail> mailPage=new Page<>(currentPage,size);
        IPage<Mail> mailIPage=mailMapper.selectPage(mailPage,new QueryWrapper<Mail>().eq("sendTo",sendTo).or().eq("sendFrom",sendTo).orderByDesc("sendTime"));
        PageBean pageBean=new PageBean(mailIPage.getTotal(),mailIPage.getRecords());
        return pageBean;
    }
}
