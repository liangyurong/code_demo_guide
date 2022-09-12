package com.demo.lyr.mp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.lyr.mp.entity.StudentEntity;
import com.demo.lyr.mp.entity.dto.StudentDTO;
import java.util.List;

/**
 * @author liangyurong
 * @date 2022-08-29
 */
public interface StudentService extends IService<StudentEntity> {

    Page<StudentEntity> getStudentList(StudentDTO mpStudentDTO);

    StudentEntity getStudentById(Integer id);

    Boolean insertStudent(StudentEntity mpStudentEntity);

    Boolean delStudent(Integer id);

    Boolean updateStudentEntity(StudentEntity mpStudentEntity);

    List<StudentEntity> findByTime(String startTime, String endTime);

    List<StudentEntity> findBySingleTime(String time);
}
