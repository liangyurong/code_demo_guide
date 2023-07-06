package com.demo.lyr.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.lyr.entity.People;
import com.demo.lyr.mapper.PeopleMapper;
import com.demo.lyr.service.PeopleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements PeopleService {

    @DS("db2")
    @Override
    public List<People> getList() {
        return this.list();
    }
}