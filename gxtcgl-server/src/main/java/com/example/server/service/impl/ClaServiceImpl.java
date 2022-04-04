package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.Area;
import com.example.server.entity.Cla;
import com.example.server.entity.Stall;
import com.example.server.mapper.ClaMapper;
import com.example.server.mapper.StallMapper;
import com.example.server.service.IClaService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xueminglu
 * @since 2022-03-29
 */
@Service
public class ClaServiceImpl extends ServiceImpl<ClaMapper, Cla> implements IClaService {

    @Autowired
    private ClaMapper claMapper;
    @Autowired
    private StallMapper stallMapper;

    @Override
    public List<Cla> getAllCla() {
        List<Cla> allCla = claMapper.getAllCla();
        allCla.forEach(cls->{
            List<Area> areaList = cls.getChildren();
            areaList.forEach(area -> {
                List<Stall> stallList=area.getStalls();
                if (stallList!=null){
                    area.setCount(stallList.size());
                    area.setYt(stallMapper.ytStall(area.getAreaId()));
                }
            });
        });
        return allCla;
    }
}
