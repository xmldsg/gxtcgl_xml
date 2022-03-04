package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-16
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    IPage<Cart> getPageAllCarts(Page<Cart> cartPage, @Param("cart")Cart cart);

    List<Cart> getAllCarts();
}
