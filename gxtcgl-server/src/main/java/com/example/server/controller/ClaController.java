package com.example.server.controller;


import com.example.server.entity.Cla;
import com.example.server.service.IClaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xueminglu
 * @since 2022-03-29
 */
@RestController
@RequestMapping("/cla")
public class ClaController {

    @Autowired
    private IClaService claService;

    @ApiOperation(value = "获取分类的区域信息")
    @GetMapping("/getAllCla")
    public List<Cla> getAllCla(){
        return claService.getAllCla();
    }

}
