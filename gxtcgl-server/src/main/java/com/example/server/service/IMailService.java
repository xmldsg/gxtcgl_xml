package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.Mail;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xueminglu
 * @since 2022-01-21
 */
public interface IMailService extends IService<Mail> {

    RespBean sendMail(Mail mail);

    RespBean sendMails(Mail mail);

    PageBean getMailbox(Integer currentPage, Integer size, String sendTo);
}
