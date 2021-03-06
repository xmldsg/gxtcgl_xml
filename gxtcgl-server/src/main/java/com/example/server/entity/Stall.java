package com.example.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tcgl_stall")
@ApiModel(value="Stall对象", description="")
public class Stall implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "车位Id")
    @TableId(value = "stallId", type = IdType.AUTO)
    private Integer stallId;

    @ApiModelProperty(value = "车位编号")
    @Excel(name = "车位编号")
    private String stallNum;

    @ApiModelProperty(value = "车位状态")
    private String stallState;

    @ApiModelProperty(value = "用户Id")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer userId;

    @ApiModelProperty(value = "区域Id")
    private Integer areaId;

    @ApiModelProperty(value = "车Id")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer carId;

    @ApiModelProperty(value = "车位类型")
    private String stallNature;

    @ApiModelProperty(value = "停车时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String kssj;

    @ApiModelProperty(value = "停车结束时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String jssj;

    @ApiModelProperty(value = "区域信息")
    @TableField(exist = false)
    private Area area;

    @ApiModelProperty(value = "用户信息")
    @TableField(exist = false)
    private User user;

    @ApiModelProperty(value = "车辆信息")
    @TableField(exist = false)
    private Cart car;

}
