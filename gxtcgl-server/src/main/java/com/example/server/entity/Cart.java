package com.example.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tcgl_cart")
@ApiModel(value="Cart对象", description="")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "车辆Id")
    @TableId(value = "carId", type = IdType.AUTO)
    private Integer carId;

    @ApiModelProperty(value = "车辆类型ID")
    private Integer tId;

    @ApiModelProperty(value = "用户Id")
    private Integer userId;

    @ApiModelProperty(value = "车牌号")
    @Excel(name = "车牌号")
    private String plateNum;

    @ApiModelProperty(value = "车辆状态")
    @Excel(name = "车辆状态")
    private String carState;

    @ApiModelProperty(value = "用户信息")
    @TableField(exist = false)
    @ExcelEntity(name = "车主姓名")
    private User user;

    @ApiModelProperty(value = "车辆类型")
    @TableField(exist = false)
    @ExcelEntity(name = "车辆类型")
    private CarType carType;


    @ApiModelProperty(value = "车位信息")
    @TableField(exist = false)
    @ExcelEntity(name = "车位信息")
    private Stall stall;

    @ApiModelProperty(value = "分页数量")
    @TableField(exist = false)
    private Integer size;

    @ApiModelProperty(value = "当前页")
    @TableField(exist = false)
    private Integer currentPage;


}
