package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.Cart;
import com.example.server.mapper.CartMapper;
import com.example.server.service.ICartService;
import com.example.server.utils.PageBean;
import com.example.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-16
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public PageBean getPageAllCarts(Integer currentPage, Integer size, Cart cart) {

        if (cart.getStall().getStallNum()!=null&&cart.getStall().getStallNum().length()!=0){
            String s=cart.getStall().getStallNum().substring(1);
            String s2=cart.getStall().getStallNum().substring(0,1);
            cart.getStall().setStallNum(s);
            cart.getStall().setStallNature(s2);
        }
        Page<Cart> cartPage=new Page<>(currentPage,size);
        IPage<Cart> cartIPage=cartMapper.getPageAllCarts(cartPage,cart);
        PageBean pageBean = new PageBean(cartIPage.getTotal(), cartIPage.getRecords());
        return pageBean;

    }
    @Override
    public List<Cart> getAllCarts() {
        return cartMapper.getAllCarts();
    }


}
