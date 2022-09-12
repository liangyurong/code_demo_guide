package com.demo.lyr.conbinate_data.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.lyr.conbinate_data.mapper.TGradeMapper;
import com.demo.lyr.conbinate_data.entity.TGradeEntity;
import com.demo.lyr.conbinate_data.service.TGradeService;
import org.springframework.stereotype.Service;

@Service
public class TGradeServiceImpl extends ServiceImpl<TGradeMapper, TGradeEntity> implements TGradeService {
}
