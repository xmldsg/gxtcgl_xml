package com.example.server.controller;


import com.example.server.entity.Stall;
import com.example.server.service.IStallService;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
@RestController
@RequestMapping("/stall")
public class StallController {

    @Autowired
    private IStallService stallService;


    @ApiOperation(value = "根据区域Id获取车位信息")
    @GetMapping("/getStall")
    public PageBean getStallByAreaId(@RequestParam(defaultValue = "1") Integer currentPage,
                                     @RequestParam(defaultValue = "20") Integer size,
                                     Integer areaId,
                                     HttpServletRequest request){
        return stallService.getStallByAreaId(currentPage,size,areaId,request);
    }

    @ApiOperation(value = "新增车位")
    @PostMapping("/addStall")
    public RespBean addStall(@RequestBody Stall stall){
        stall.setStallState("空位");
        if (stallService.save(stall)){
            return RespBean.success("新增成功");
        }
        return RespBean.error("新增失败");
    }

    @ApiOperation(value = "修改车位")
    @PutMapping("/updateStall")
    public RespBean updateStall(@RequestBody Stall stall){
        if (stallService.updateById(stall)){
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @ApiOperation(value = "删除车位")
    @DeleteMapping("/delete/{stallId}")
    public RespBean deleteStall(@PathVariable Integer stallId){
        if (stallService.removeById(stallId)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "获取区域最大的车位编号")
    @GetMapping("/maxNum")
    public RespBean maxStallNum(Integer areaId){
        return stallService.maxStallNum(areaId);
    }

    @ApiOperation(value = "获取已设置停车位")
    @GetMapping("/count")
    public RespBean countStall(Integer areaId) {
        return RespBean.success(null,stallService.countStall(areaId));
    }

}
