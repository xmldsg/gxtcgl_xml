package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.entity.Cla;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xueminglu
 * @since 2022-03-29
 */
@Mapper
public interface ClaMapper extends BaseMapper<Cla> {

    List<Cla> getAllCla();
}
