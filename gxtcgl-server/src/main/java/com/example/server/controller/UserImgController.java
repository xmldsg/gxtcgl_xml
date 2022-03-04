package com.example.server.controller;


import com.example.server.entity.UserImg;
import com.example.server.service.IUserImgService;
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
 * @since 2021-12-23
 */
@RestController
@RequestMapping("/user/img")
public class UserImgController {

    @Autowired
    private IUserImgService userImgService;

    @ApiOperation("获取图片列表")
    @GetMapping()
    public List<UserImg> getListImg(){
        return userImgService.list();
    }

}
