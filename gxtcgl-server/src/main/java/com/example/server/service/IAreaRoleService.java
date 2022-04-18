package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.AreaRole;
import com.example.server.utils.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xueminglu
 * @since 2022-04-12
 */
public interface IAreaRoleService extends IService<AreaRole> {

    RespBean updateCarTypeArea(Integer carTypeId, Integer[] aIds);

}
