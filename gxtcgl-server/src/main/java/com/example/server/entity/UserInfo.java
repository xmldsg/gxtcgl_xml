package com.example.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

/**
 * <p>用户实体类</p>
 *
 * @author : xueminglu
 * @date : 2022-01-04 15:18
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tcgl_user")
@ApiModel(value = "User对象", description = "")
public class UserInfo implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Id")
    private Integer id;

    @ApiModelProperty(value = "用户Id")
    @Excel(name = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    @Excel(name = "姓名")
    private String name;


    @ApiModelProperty(value = "用户头像")
    private String userFace;

    @ApiModelProperty(value = "手机号码")
    @Excel(name = "手机号",width = 40)
    private String phone;

    @ApiModelProperty(value = "角色Id")
    private Integer roleId;

    @ApiModelProperty(value = "邮箱")
    @Excel(name = "邮箱",width = 40)
    private String mailbox;


    @ApiModelProperty(value = "角色")
    @TableField(exist = false)
    @ExcelEntity(name = "身份名称")
    private Role role;

    @ApiModelProperty(value = "车辆")
    @TableField(exist = false)
    private List<Cart> cartList;

}
