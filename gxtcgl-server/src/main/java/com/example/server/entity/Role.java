package com.example.server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false,of = "roleName")
@Accessors(chain = true)
@TableName("tcgl_role")
@ApiModel(value="Role对象", description="")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "身份Id")
    @TableId(value = "roleId", type = IdType.AUTO)
    private Integer roleId;

    @ApiModelProperty(value = "身份名称")
    @Excel(name = "身份名称")
    @NonNull
    private String roleName;

    @ApiModelProperty(value = "用户权限")
    private String rolePower;


}
