package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-19
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据角色获取menu列表
     * @return
     */
    List<Menu> getMenusWithRole();
}
