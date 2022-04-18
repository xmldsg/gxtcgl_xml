package com.example.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.entity.CarType;
import com.example.server.entity.Cart;
import com.example.server.entity.Role;
import com.example.server.entity.User;
import com.example.server.service.ICarTypeService;
import com.example.server.service.ICartService;
import com.example.server.service.IRoleService;
import com.example.server.service.IUserService;
import com.example.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>注册</p>
 *
 * @author : xueminglu
 * @date : 2022-01-07 15:01
 **/
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ICartService cartService;
    @Autowired
    private ICarTypeService carTypeService;

    @ApiOperation(value = "获取角色列表")
    @GetMapping("/role")
    public List<Role> getallRole(){
        return roleService.list(new QueryWrapper<Role>().notIn("roleId",3));
    }



    @ApiOperation(value = "获取车辆类型列表")
    @GetMapping("/clist")
    public List<CarType> getList(){
        return carTypeService.list();
    }

    @ApiOperation(value = "注册用户")
    @PostMapping("/add")
    public RespBean doRegister(@RequestBody User user){
        if (user.getPassword()!=null){
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
        }
        if (userService.save(user)){
            return RespBean.success("用户信息添加成功，请填写下一步",user.getId());
        }
        return RespBean.error("用户信息添加失败");
    }

    @ApiOperation(value = "注册车辆")
    @PostMapping("/cart")
    public RespBean doAddCart(@RequestBody Cart cart){
        if (cart.getUserId()==null){
            return RespBean.error("请先完成第一步用户注册");
        }
        if (cartService.save(cart)){
            return RespBean.success("注册成功，请登录");
        }

        return RespBean.error("注册车辆信息失败");
    }

    @ApiOperation(value = "删除保存角色")
    @DeleteMapping("/{id}")
    public RespBean deleteZhuCeUser(@PathVariable Integer id){
        if (userService.removeById(id)){
            return RespBean.success("取消注册");
        }
        return RespBean.error("数据库异常请联系管理员");
    }

}
