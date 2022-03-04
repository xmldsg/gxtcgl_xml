package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.Stall;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-21
 */
public interface IStallService extends IService<Stall> {


    PageBean getStallByAreaId(Integer currentPage, Integer size, Integer areaId,HttpServletRequest request);

    RespBean maxStallNum(Integer areaId);

    int countStall(Integer areaId);
}
