package com.example.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.server.entity.User;
import com.example.server.service.IUserService;
import com.example.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>管理员</p>
 *
 * @author : xueminglu
 * @date : 2021-12-31 10:18
 **/
@RestController
public class AdminController {

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "获取操作员(聊天)")
    @GetMapping("/chat/admin")
    public List<User> getAllAdmin(String keywords){
        return userService.getAllAdmin(keywords);
    }

    @ApiOperation(value = "新增操作员")
    @PostMapping("/add/admin")
    public RespBean addAdmin(@RequestBody User user){
        if (user.getPassword()!=null){
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
        }

        if (userService.save(user)){
            return RespBean.success("新增成功");
        }
        return RespBean.error("新增失败");
    }

    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/admins")
    public List<User> getAllAdmins(String keywords){
        return userService.getAllAdmin(keywords);
    }

    @ApiOperation(value = "更新操作员")
    @PutMapping("/update/admin")
    public RespBean updateAdmin(Integer id,Integer roleId){
        if (userService.update(new UpdateWrapper<User>().set("roleId",roleId).eq("id",id))){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/delete/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        if (userService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }


}
