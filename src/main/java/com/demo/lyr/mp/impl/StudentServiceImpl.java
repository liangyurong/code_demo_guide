package com.demo.lyr.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.lyr.mp.entity.StudentEntity;
import com.demo.lyr.mp.entity.dto.StudentDTO;
import com.demo.lyr.mp.mapper.StudentMapper;
import com.demo.lyr.mp.service.StudentService;
import com.demo.lyr.tool.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author liangyurong
 * @date 2022/08/08
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

    @Override
    public Page<StudentEntity> getStudentList(StudentDTO dto) {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        // 时间范围
        wrapper.between(StringUtils.isNoneBlank(dto.getBirthdayStartTime(), dto.getBirthdayEndTime()), StudentEntity::getBirthday, dto.getBirthdayStartTime(), dto.getBirthdayEndTime());
        wrapper.between(StringUtils.isNoneBlank(dto.getPayStartTime(), dto.getPayEndTime()), StudentEntity::getPayTime, dto.getPayStartTime(), dto.getPayEndTime());
        // 名称
        wrapper.like(StringUtils.isNoneBlank(dto.getNickName()),StudentEntity::getNickName, dto.getNickName());
        // 分页
        Page<StudentEntity> page = new Page<>();
        if (dto.getCurrent() == null || dto.getSize() == null) {
            List<StudentEntity> list = this.list(wrapper);
            page.setRecords(list).setTotal(list.size()).setCurrent(1).setSize(list.size());
        } else {
            page = this.page(new Page<>(dto.getCurrent(), dto.getSize()), wrapper);
        }
        return page;
    }

    @Override
    public StudentEntity getStudentById(Integer id) {
        StudentEntity entity = this.getById(id);
        return entity;
    }

    @Override
    public Boolean insertStudent(StudentEntity mpStudentEntity) {
        Boolean save = this.save(mpStudentEntity);
        return save;
    }

    @Override
    public Boolean delStudent(Integer id) {
        boolean result = this.removeById(id);
        return result;
    }

    @Override
    public Boolean updateStudentEntity(StudentEntity mpStudentEntity) {
        boolean update = this.updateById(mpStudentEntity);
        return update;
    }

    @Override
    public List<StudentEntity> findByTime(String startTime, String endTime) {
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.between(StudentEntity::getBirthday, startTime, endTime);
        List<StudentEntity> list = this.list(wrapper);
        return list;
    }

    @Override
    public List<StudentEntity> findBySingleTime(String time) {
        // 字符串日期转为Date类型日期
        Date date = DateUtil.parse(time,DateUtil.DATE_FORMAT);
        // 某日期的开始时间和结束时间(缴费时间具体到时分秒)
        Date dayBegin = DateUtil.getDayBegin(date);
        Date dayEnd = DateUtil.getEndTimeOfCurrentDay(date);
        // 查询
        LambdaQueryWrapper<StudentEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.between(StudentEntity::getPayTime,dayBegin,dayEnd);
        List<StudentEntity> list = this.list(wrapper);
        return list;
    }
}

