package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.Stall;
import com.example.server.mapper.StallMapper;
import com.example.server.service.IStallService;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
@Service
public class StallServiceImpl extends ServiceImpl<StallMapper, Stall> implements IStallService {

    @Autowired
    private StallMapper stallMapper;


    @Override
    public PageBean getStallByAreaId(Integer currentPage, Integer size, Integer areaId, HttpServletRequest request) {
        Page<Stall> stallPage = new Page<>(currentPage, size);

        IPage<Stall> stallIPage = null;
        if (areaId != null) {
            stallIPage = stallMapper.selectPage(stallPage, new QueryWrapper<Stall>().eq("areaId", areaId).orderBy(true, true, "stallNum"));
        } else {
            if (request.getSession().getAttribute("oneArea") != null) {
                int oneArea = (int) request.getSession().getAttribute("oneArea");
                stallIPage = stallMapper.selectPage(stallPage, new QueryWrapper<Stall>().eq("areaId", oneArea).orderBy(true, true, "stallNum"));

            } else {
                Stall stall = stallMapper.selectOne(new QueryWrapper<Stall>().select("areaId").orderByDesc("areaId").last("limit 1"));
                stallIPage = stallMapper.selectPage(stallPage, new QueryWrapper<Stall>().eq("areaId", stall.getAreaId()).orderBy(true, true, "stallNum"));

            }
        }
        PageBean respPageBean = new PageBean(stallIPage.getTotal(), stallIPage.getRecords());
        return respPageBean;
    }

    @Override
    public RespBean maxStallNum(Integer areaId) {
        List<Map<String, Object>> list = stallMapper.selectMaps(new QueryWrapper<Stall>().select("max(stallNum)").eq("areaId", areaId));
        int format = 100;

        if (list.size() != 0 && list.get(0) != null && list.get(0).get("max(stallNum)") != null) {

            int i = (int) list.get(0).get("max(stallNum)");
            format = Integer.parseInt(String.format("%d", i + 1));


        }
        return RespBean.success(null, format);
    }

    @Override
    public int countStall(Integer areaId) {
        return stallMapper.countStall(areaId);
    }

    @Override
    public Stall getStall(int userId, int carId) {
        return stallMapper.selectOne(new QueryWrapper<Stall>().eq("userId",userId).eq("carId",carId));
    }

    @Override
    public List<Stall> ligetYTChest() {
        return stallMapper.ligetYTChest();
    }
}
