package com.demo.lyr.mp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.lyr.mp.entity.StudentEntity;
import com.demo.lyr.mp.entity.dto.StudentDTO;
import com.demo.lyr.mp.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Mp接口
 * @author liangyurong
 * @date 2022-08-09
 */
@Slf4j
@RestController
@RequestMapping("/mp")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 多条件分页查询
     * http://localhost:9999/api/mp/list
     * http://localhost:9999/api/mp/list?current=2&size=2
     * @param dto 查询条件
     * @return 列表
     */
    @GetMapping("/list")
    public Page<StudentEntity> webTemplateList(StudentDTO dto) {
        Page<StudentEntity> studentList = studentService.getStudentList(dto);
        return studentList;
    }

    /**
     * 通过id查询
     *
     * @param id 主键id
     * @return
     */
    @GetMapping("/select/{id}")
    public StudentEntity selectById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    /**
     * 新增
     *
     * @param entity 实体类
     * @return
     */
    @PostMapping("/insert")
    public Boolean AddStudent(@RequestBody StudentEntity entity) {
        Boolean save = studentService.insertStudent(entity);
        return save;
    }

    /**
     * 通过id删除
     *
     * @param id 主键id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Integer id) {
        Boolean bool = studentService.delStudent(id);
        return bool;
    }

    /**
     * 修改
     *
     * @param entity 实体
     * @return
     */
    @PutMapping("/update")
    public Boolean update(@RequestBody StudentEntity entity) {
        Boolean update = studentService.updateStudentEntity(entity);
        return update;
    }

    /**
     * 获取某时间范围过生日的学生列表
     * http://localhost:9999/api/mp/findByTime?startTime=2022-08-27&endTime=2022-08-29
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @GetMapping("/findByTime")
    public List<StudentEntity> getListBySingleTime(String startTime,String endTime) {
        List<StudentEntity> list = studentService.findByTime(startTime,endTime);
        return list;
    }

    /**
     * 获取某天缴费的学生列表
     * http://localhost:9999/api/mp/findBySingleTime?time=2022-08-29 (只能传yyyy-mm-dd的形式)
     * @param time 单个时间
     * @return
     */
    @GetMapping("/findBySingleTime")
    public List<StudentEntity> getListBySingleTime(@RequestParam(name = "time")String time) {
        List<StudentEntity> list = studentService.findBySingleTime(time);
        return list;
    }

}
