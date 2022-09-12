package com.demo.lyr.conbinate_data.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.lyr.conbinate_data.dto.TDTO;
import com.demo.lyr.conbinate_data.mapper.TStudentMapper;
import com.demo.lyr.conbinate_data.entity.TStudentEntity;
import com.demo.lyr.conbinate_data.service.TStudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TStudentServiceImpl extends ServiceImpl<TStudentMapper, TStudentEntity> implements TStudentService {
    @Override
    public List<TStudentEntity> deal(TDTO dto) {
        return null;
    }
}
