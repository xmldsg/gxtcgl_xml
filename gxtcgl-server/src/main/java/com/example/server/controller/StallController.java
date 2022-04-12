package com.example.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.server.entity.Cart;
import com.example.server.entity.Stall;
import com.example.server.service.ICartService;
import com.example.server.service.IStallService;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private ICartService cartService;


    @ApiOperation(value = "根据区域Id获取车位信息")
    @GetMapping("/getStall")
    public PageBean getStallByAreaId(@RequestParam(defaultValue = "1") Integer currentPage,
                                     @RequestParam(defaultValue = "20") Integer size,
                                     Integer areaId,
                                     HttpServletRequest request){
        return stallService.getStallByAreaId(currentPage,size,areaId,request);
    }

    @ApiOperation(value = "获取一停车车位信息")
    @GetMapping("/yTChe")
    public List<Stall> getYTChe(){
        return stallService.ligetYTChest();
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

    @ApiOperation(value = "修改车位状态")
    @PutMapping("/updateStalls")
    @Transactional
    public RespBean updateStalls(@RequestBody Stall stall){
        if(stall.getStallState().equals("占位")){

            cartService.update(new UpdateWrapper<Cart>().set("carState","未停").eq("carId",stall.getCarId()));
            stall.setStallState("空位");
            stall.setUserId(null);
            stall.setCarId(null);
            stall.setJssj(null);
            stall.setKssj(null);
        }else {
            cartService.update(new UpdateWrapper<Cart>().set("carState","已停").eq("carId",stall.getCarId()));
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp = new Timestamp(date.getTime());
            stall.setKssj(df.format(timestamp));
            stall.setStallState("占位");
        }
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
