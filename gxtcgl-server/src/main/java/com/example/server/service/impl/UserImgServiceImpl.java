package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.UserImg;
import com.example.server.mapper.UserImgMapper;
import com.example.server.service.IUserImgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xueminglu
 * @since 2021-12-23
 */
@Service
public class UserImgServiceImpl extends ServiceImpl<UserImgMapper, UserImg> implements IUserImgService {

}
