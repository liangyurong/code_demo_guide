package com.demo.lyr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.lyr.entity.People;
import java.util.List;

public interface PeopleService extends IService<People> {

    List<People> getList();

}
