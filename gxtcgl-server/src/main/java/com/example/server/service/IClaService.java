package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.Cla;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xueminglu
 * @since 2022-03-29
 */
public interface IClaService extends IService<Cla> {

    List<Cla> getAllCla();
}
