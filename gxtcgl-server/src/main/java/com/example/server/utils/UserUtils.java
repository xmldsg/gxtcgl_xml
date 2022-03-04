package com.example.server.utils;

import com.example.server.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>操作员工具类</p>
 *
 * @author : xueminglu
 * @date : 2021-12-27 15:52
 **/

public class UserUtils {

    public static User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
