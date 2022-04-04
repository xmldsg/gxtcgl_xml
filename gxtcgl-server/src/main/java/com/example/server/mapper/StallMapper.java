package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.entity.Stall;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
@Mapper
public interface StallMapper extends BaseMapper<Stall> {

    int countStall(Integer areaId);

    int ytStall(int areaId);
}
