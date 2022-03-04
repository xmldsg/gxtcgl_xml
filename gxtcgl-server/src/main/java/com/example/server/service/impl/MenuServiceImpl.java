package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.Menu;
import com.example.server.entity.User;
import com.example.server.mapper.MenuMapper;
import com.example.server.service.IMenuService;
import com.example.server.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;



    @Override
    public List<Menu> getMenusWithRole() {
        Integer roleId = UserUtils.getCurrentUser().getRoleId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //从redis获取数据
        List<Menu> menus=(List<Menu>) valueOperations.get("menu_"+roleId);
        //如果为空，去数据库获取
        if (CollectionUtils.isEmpty(menus)){
            menus=menuMapper.getMenusWithRole(roleId);
            //将数据存入redis
            valueOperations.set("menu_"+roleId,menus);
        }
        return menus;
    }
}
