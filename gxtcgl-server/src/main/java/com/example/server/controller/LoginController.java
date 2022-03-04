package com.example.server.controller;

import com.example.server.entity.User;
import com.example.server.entity.UserLoginParam;
import com.example.server.service.IRoleService;
import com.example.server.service.IUserService;
import com.example.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@RestController
@Api(tags = "LoginController")
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;


    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        return userService.login(userLoginParam.getUsername(),userLoginParam.getPassword(),userLoginParam.getCode(),request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/user/info")
    public User getUserInfo(Principal principal){
        if (null==principal){
            return null;
        }
        String username=principal.getName();
        User user=userService.getUserByUsername(username);
        user.setPassword(null);
        user.setRole(roleService.getRoles(user.getId()));
        return user;
    }


    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功！");
    }

    @ApiOperation(value = "更新当前用户信息")
    @PutMapping("/update/info")
    public RespBean updateAdmin(@RequestBody User user, Authentication authentication){
        if (userService.updateById(user)){
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user,null,authentication.getAuthorities()));
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "更新用户密码")
    @PutMapping("/admin/pass")
    public RespBean updateAdminPass(@RequestBody Map<String,Object> infoPass){
        String oldPass = (String)infoPass.get("oldPass");
        String pass = (String) infoPass.get("pass");
        Integer id = (Integer) infoPass.get("id");
        return userService.updateAdminPass(oldPass,pass,id);
    }


}
