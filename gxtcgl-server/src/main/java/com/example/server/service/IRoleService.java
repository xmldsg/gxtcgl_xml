package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.Role;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据用户Id获取角色列表
     * @return
     */
    Role getRoles(Integer id);

}
