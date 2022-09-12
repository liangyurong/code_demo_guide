package com.demo.lyr.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.lyr.mp.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author  liangyurong
 * @date 2022/08/09
 */
@Mapper
@Component
public interface StudentMapper extends BaseMapper<StudentEntity> {

}
