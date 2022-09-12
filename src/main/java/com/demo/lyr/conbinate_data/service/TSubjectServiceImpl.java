package com.demo.lyr.conbinate_data.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.lyr.conbinate_data.mapper.TSubjectMapper;
import com.demo.lyr.conbinate_data.entity.TSubjectEntity;
import org.springframework.stereotype.Service;

@Service
public class TSubjectServiceImpl extends ServiceImpl<TSubjectMapper, TSubjectEntity> implements TSubjectService {
}
