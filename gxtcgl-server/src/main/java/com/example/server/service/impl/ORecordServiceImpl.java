package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.entity.ORecord;
import com.example.server.mapper.ORecordMapper;
import com.example.server.service.IORecordService;
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
public class ORecordServiceImpl extends ServiceImpl<ORecordMapper, ORecord> implements IORecordService {

}
