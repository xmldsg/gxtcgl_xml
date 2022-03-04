package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.entity.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
@Mapper
public interface AreaMapper extends BaseMapper<Area> {

    List<Area> getAllArea(String enable);
}
