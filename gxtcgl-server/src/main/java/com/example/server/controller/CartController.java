package com.example.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.entity.Area;
import com.example.server.entity.Cart;
import com.example.server.entity.Stall;
import com.example.server.entity.User;
import com.example.server.service.IAreaService;
import com.example.server.service.ICartService;
import com.example.server.service.IStallService;
import com.example.server.service.IUserService;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-16
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private IStallService stallService;
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "获取车辆信息-分页")
    @PostMapping("/allCar")
    public PageBean getPageAllCarts(@RequestBody Cart cart) {

        return cartService.getPageAllCarts(cart.getCurrentPage(), cart.getSize(), cart);
    }

    @ApiOperation(value = "获取所有区域地址信息")
    @GetMapping("/allArea")
    public List<Area> getAllAreas() {
        return areaService.list(new QueryWrapper<Area>().select("address", "areaId").groupBy("address"));
    }

    @ApiOperation(value = "获取区域名称信息")
    @GetMapping("/allArea/address")
    public List<Area> getAllAreasWithAreaName(String address) {
        if (!address.equals("")) {
            return areaService.list(new QueryWrapper<Area>().select("areaId", "areaName").eq("address", address));
        }
        return areaService.list(new QueryWrapper<Area>().select("areaId", "areaName"));
    }

    @ApiOperation(value = "获取所有车位信息")
    @GetMapping(value = "/allStall")
    public List<Stall> getAllStalls(){
        return stallService.list(new QueryWrapper<Stall>().select("stallId","stallNum","stallNature"));
    }

    @ApiOperation(value = "新增车辆")
    @PostMapping("/addCart")
    public RespBean addCart(@RequestBody Cart cart){
        cart.setCarState("未停");
        if (cartService.save(cart)){
            return RespBean.success("新增成功");
        }
        return RespBean.error("新增失败");
    }

    @ApiOperation(value = "更新车辆")
    @PutMapping("/updateCart")
    public RespBean updateCart(@RequestBody Cart cart){
        if (cartService.updateById(cart)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除车辆")
    @DeleteMapping("/delete/{carId}")
    public RespBean deleteCart(@PathVariable Integer carId){
        if (cartService.removeById(carId)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除车辆")
    @DeleteMapping("/deletes")
    public RespBean deleteCarts(Integer[] ids){
        if (cartService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "获取临时人员")
    @GetMapping("/byIdGetCar")
    public List<Cart> byIdGetCar(int userId){
        return cartService.list(new QueryWrapper<Cart>().eq("userId",userId));
    }


    @ApiOperation(value = "获取临时人员")
    @GetMapping("/linshi")
    public List<User> getLinshi(){
        return userService.list(new QueryWrapper<User>().select("id","name").eq("roleId",4));
    }

    @ApiOperation(value = "获取个人车辆信息")
    @GetMapping("/getYuCar")
    public List<Cart> getYuCar(@PathVariable Cart cart){
       return cartService.getAllCarts(cart);

    }

    @ApiOperation(value = "导出车辆资料")
    @GetMapping("/export")
    public void exportUser(HttpServletResponse response){
        List<Cart> cartList=cartService.getAllCarts(null);
        ExportParams params = new ExportParams("车辆表", "车辆表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Cart.class, cartList);
        ServletOutputStream outputStream = null;

        try {
            //输出流
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("车辆表.xls", "UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
