package com.example.server.entity;

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
import java.util.List;

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
@TableName("tcgl_area")
@ApiModel(value="Area对象", description="")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域id")
    @TableId(value = "areaId", type = IdType.AUTO)
    private Integer areaId;

    @ApiModelProperty(value = "区域地址")
    private String address;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @ApiModelProperty(value = "车位数量")
    private Integer stallCount;

    @ApiModelProperty(value = "区域编号")
    private String areaNum;

    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    private Integer parent;

    @ApiModelProperty(value = "已设车位数量")
    @TableField(exist = false)
    private Integer count;

    @ApiModelProperty(value = "已停车位数量")
    @TableField(exist = false)
    private Integer yt;

    @TableField(exist = false)
    private List<Stall> stalls;



}
