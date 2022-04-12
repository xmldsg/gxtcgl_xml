package com.example.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.example.server.entity.Cart;
import com.example.server.entity.Stall;
import com.example.server.entity.User;
import com.example.server.entity.UserInfo;
import com.example.server.service.ICartService;
import com.example.server.service.IRoleService;
import com.example.server.service.IStallService;
import com.example.server.service.IUserService;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-16
 */
@RestController
@RequestMapping("/user/basic")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IStallService stallService;

    @ApiOperation(value = "导出用户资料")
    @GetMapping("/export")
    public void exportUser(HttpServletResponse response) {
        List<UserInfo> userList = userService.getUserList();
        ExportParams params = new ExportParams("用户表", "用户表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, UserInfo.class, userList);
        ServletOutputStream outputStream = null;

        try {
            //输出流
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("用户表.xls", "UTF-8"));
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

//    @ApiOperation(value = "导入用户资料")
//    @PostMapping("/import")
//    public RespBean importEmp(MultipartFile file) {
//        ImportParams params = new ImportParams();
//        //去掉标题行
//        params.setTitleRows(1);
//
//        List<Role> roleList=roleService.list();
//        try {
//            List<UserInfo> list = ExcelImportUtil.importExcel(file.getInputStream(), UserInfo.class, params);
//            list.forEach(userInfo -> {
//
//                //角色Id
//                userInfo.setRoleId(roleList.get(roleList.indexOf(new Role(userInfo.getRole().getRoleName()))).getRoleId());
//
//            });
//            if (userService.saveBatch(list)){
//                return RespBean.success("导入成功");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return RespBean.error("导入失败");
//
//    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    public RespBean addUser(@RequestBody User user) {
        if (user.getPassword() != null) {
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
        }
        if (userService.save(user)) {
            return RespBean.success("新增成功");
        }
        return RespBean.error("新增失败");
    }

    @ApiOperation(value = "查询所有用户（分页）")
    @GetMapping("/all")
    public PageBean getAllUser(@RequestParam(defaultValue = "1") Integer currentPage,
                               @RequestParam(defaultValue = "10") Integer size,
                               UserInfo userInfo) {
        return userService.getUserPage(currentPage, size, userInfo);

    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public RespBean deleteUser(@PathVariable Integer id) {
        if (userService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/update")
    public RespBean updateUser(@RequestBody User user) {
        if (userService.updateById(user)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "批量删除职位")
    @DeleteMapping("/")
    public RespBean deleteUsers(Integer[] ids) {
        if (userService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "根据userId,carId获取用户车辆信息")
    @GetMapping("/getUserCar")
    public Map<String, Object> getUserCar(int userId, int carId) {
        User user = userService.getUser(userId);
        Cart cart = cartService.getCar(userId,carId);
        Stall stall=stallService.getStall(userId,carId);
        Map<String,Object> map=new HashMap<>();
        map.put("user",user);
        map.put("car",cart);
        map.put("stall",stall);
        return map;
    }

}
