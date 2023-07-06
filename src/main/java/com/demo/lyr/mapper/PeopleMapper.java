package com.demo.lyr.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.lyr.entity.People;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface PeopleMapper extends BaseMapper<People> {

    @DS("db2")
    Integer getIdByName(String name);

    @DS("db2")
    People getPeopleById(Integer id);

    @DS("db2")
    List<People> listPeople(String name);

    @DS("db2")
    List<Map<String, Object>> getListMap(@Param("ids") List<Integer> ids);


}
