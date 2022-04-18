package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.AreaRole;
import com.example.server.mapper.AreaRoleMapper;
import com.example.server.service.IAreaRoleService;
import com.example.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xueminglu
 * @since 2022-04-12
 */
@Service
public class AreaRoleServiceImpl extends ServiceImpl<AreaRoleMapper, AreaRole> implements IAreaRoleService {

    @Autowired
    private AreaRoleMapper areaRoleMapper;

    @Override
    public RespBean updateCarTypeArea(Integer carTypeId, Integer[] aIds) {
        areaRoleMapper.delete(new QueryWrapper<AreaRole>().eq("carTypeId",carTypeId));
        if (null==aIds||aIds.length==0){
            return RespBean.success("更新成功");
        }
        Integer result = areaRoleMapper.insertRecord(carTypeId, aIds);
        if (result==aIds.length){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
