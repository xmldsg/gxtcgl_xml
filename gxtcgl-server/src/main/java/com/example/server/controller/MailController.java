package com.example.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.entity.Mail;
import com.example.server.entity.User;
import com.example.server.service.IMailService;
import com.example.server.service.IUserService;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xueminglu
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/mail")
public class MailController {


    @Autowired
    private IMailService mailService;
//    @Autowired
//    private IUserService userService;

    @ApiOperation(value = "发送邮件")
    @PostMapping("/send")
    public RespBean sendMail(@RequestBody Mail mail) {
        return mailService.sendMail(mail);
    }

    @ApiOperation(value = "群发邮件")
    @PostMapping("/sends")
    public RespBean sendMails(@RequestBody Mail mail){
        return mailService.sendMails(mail);
    }

//    @ApiOperation(value = "获取管理员邮箱")
//    @GetMapping("/admin")
//    public List<User> getAdminMail(){
//        return userService.list(new QueryWrapper<User>().select("name","mailbox").eq("roleId",3));
//    }

    @ApiOperation(value = "删除邮件")
    @DeleteMapping("/{id}")
    public RespBean deleteMail(@PathVariable Integer id){
        if (mailService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/deletes")
    public RespBean deleteMails(Integer[] ids){
        if (mailService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "根据邮箱获取邮件")
    @GetMapping("/mailbox")
    public PageBean getMailbox(@RequestParam(defaultValue = "1") Integer currentPage,
                               @RequestParam(defaultValue = "10") Integer size,
                               String sendTo){
        return mailService.getMailbox(currentPage,size,sendTo);

    }





}
