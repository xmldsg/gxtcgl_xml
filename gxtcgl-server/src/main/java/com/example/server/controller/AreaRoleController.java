package com.example.server.controller;


import com.example.server.service.IAreaRoleService;
import com.example.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xueminglu
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/area-role")
public class AreaRoleController {

    @Autowired
    private IAreaRoleService areaRoleService;

    @ApiOperation(value = "更新车辆类型区域")
    @PutMapping("/")
    public RespBean updateCarTypeArea(Integer carTypeId, Integer[] aIds){
        return areaRoleService.updateCarTypeArea(carTypeId,aIds);
    }

}
