package com.demo.lyr.conbinate_data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.lyr.conbinate_data.dto.TDTO;
import com.demo.lyr.conbinate_data.entity.TStudentEntity;

import java.util.List;

public interface TStudentService extends IService<TStudentEntity> {

    List<TStudentEntity> deal(TDTO dto);
}
