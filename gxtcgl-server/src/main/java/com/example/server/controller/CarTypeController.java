package com.example.server.controller;


import com.example.server.entity.CarType;
import com.example.server.service.ICarTypeService;
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
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/car-type")
public class CarTypeController {

    @Autowired
    private ICarTypeService carTypeService;

    @ApiOperation(value = "获取车辆类型列表")
    @GetMapping("/list")
    public List<CarType> getList(){
        return carTypeService.list();
    }

}
