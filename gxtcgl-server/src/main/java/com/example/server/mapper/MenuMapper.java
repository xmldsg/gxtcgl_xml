package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-19
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {


    /**
     * 根据用户角色获取菜单
     * @param roleId
     * @return
     */
    List<Menu> getMenusWithRole(Integer roleId);
}
