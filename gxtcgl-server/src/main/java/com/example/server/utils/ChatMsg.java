package com.example.server.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>消息</p>
 *
 * @author : xueminglu
 * @date : 2021-12-29 14:29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {

    private String from;
    private  String to;
    private String content;
    private LocalDateTime date;
    private String formNickName;
}
