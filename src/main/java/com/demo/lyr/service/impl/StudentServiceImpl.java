package com.demo.lyr.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.lyr.entity.Student;
import com.demo.lyr.mapper.StudentMapper;
import com.demo.lyr.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @DS("db1")
    @Override
    public List<Student> getList() {

        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();


        return this.list();
    }
}
