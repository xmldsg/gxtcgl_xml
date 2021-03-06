package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.entity.AreaRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xueminglu
 * @since 2022-04-12
 */
@Mapper
public interface AreaRoleMapper extends BaseMapper<AreaRole> {

    Integer insertRecord(@Param("carTypeId") Integer carTypeId, @Param("aIds") Integer[] aIds);
}
