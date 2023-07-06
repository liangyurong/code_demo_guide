package com.demo.lyr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.lyr.entity.Student;
import java.util.List;

public interface StudentService extends IService<Student> {

    List<Student> getList();
}
