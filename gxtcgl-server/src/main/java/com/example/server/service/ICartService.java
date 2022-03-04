package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.entity.Cart;
import com.example.server.utils.PageBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-16
 */
public interface ICartService extends IService<Cart> {

    PageBean getPageAllCarts(Integer currentPage, Integer size, Cart cart);

    List<Cart> getAllCarts();
}
