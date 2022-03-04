package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.Area;
import com.example.server.mapper.AreaMapper;
import com.example.server.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> getAllArea(String enable) {
        List<Area> allArea = areaMapper.getAllArea(enable);
        return allArea;
    }


}
