package com.example.server.controller;


import com.example.server.entity.Menu;
import com.example.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-19
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "根据用户角色查询菜单")
    @GetMapping("/list")
    public List<Menu> getMenusWithRole() {
        return menuService.getMenusWithRole();
    }

}
