package com.example.server.controller;


import com.example.server.entity.Area;
import com.example.server.service.IAreaService;
import com.example.server.service.IStallService;
import com.example.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private IAreaService areaService;
    @Autowired
    private IStallService stallService;

    @ApiOperation(value = "获取区域信息")
    @GetMapping("/list")
    public List<Area> getAllArea(HttpServletRequest request,String enable) {
        List<Area> allArea = areaService.getAllArea(enable);
        request.getSession().setAttribute("oneArea",allArea.get(0).getAreaId());
        return allArea;
    }

    @ApiOperation(value = "增加区域")
    @PostMapping("/addArea")
    public RespBean addArea(@RequestBody Area area){
        if (areaService.save(area)){
            return RespBean.success("增加成功");
        }
        return RespBean.error("增加失败");
    }

    @ApiOperation(value = "修改区域")
    @PutMapping("/updateArea")
    public RespBean updateArea(@RequestBody Area area){
        if (areaService.updateById(area)){
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @ApiOperation(value = "删除区域")
    @DeleteMapping("/deleteArea/{areaId}")
    public RespBean deleteArea(@PathVariable Integer areaId){
        if (areaService.removeById(areaId)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }





}
