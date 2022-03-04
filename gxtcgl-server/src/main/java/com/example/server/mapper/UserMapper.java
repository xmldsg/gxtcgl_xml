package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.entity.Role;
import com.example.server.entity.User;
import com.example.server.entity.UserInfo;
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
public interface UserMapper extends BaseMapper<User> {



    List<User> getAllAdmin(Integer id,@Param("keywords") String keywords);

    IPage<UserInfo> getUserPage(Page<UserInfo> page, @Param("userInfo") UserInfo userInfo);

    List<UserInfo> getUserList();
}
