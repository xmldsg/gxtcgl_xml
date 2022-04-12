package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.PRecord;
import com.example.server.mapper.PRecordMapper;
import com.example.server.service.IPRecordService;
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
public class PRecordServiceImpl extends ServiceImpl<PRecordMapper, PRecord> implements IPRecordService {

}
