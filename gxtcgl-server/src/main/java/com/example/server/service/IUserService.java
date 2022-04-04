package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.Role;
import com.example.server.entity.User;
import com.example.server.entity.UserInfo;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-16
 */
public interface IUserService extends IService<User> {

    /**
     * 登录之后返回token
     * @param userId
     * @param password
     * @param request
     * @return
     */
    RespBean login(String username, String password,String code, HttpServletRequest request);


    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    Role getRoles(Integer id);

    List<User> getAllAdmin(String keywords);

    User getUserByUsername(String username);

    PageBean getUserPage(Integer currentPage, Integer size, UserInfo userInfo);

    List<UserInfo> getUserList();

    RespBean updateAdminPass(String oldPass, String pass, Integer id);

    User getUser(int userId);
}
