package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.Area;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
public interface IAreaService extends IService<Area> {


    List<Area> getAllArea(String enable);


}
