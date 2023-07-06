package com.demo.lyr.controller;

import com.demo.lyr.entity.People;
import com.demo.lyr.entity.Student;
import com.demo.lyr.mapper.PeopleMapper;
import com.demo.lyr.service.PeopleService;
import com.demo.lyr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AopController {

    public static void main(String[] args) {
        String filePath = "http://192.168.64.130:8899/group1/M00/00/00/wKhAgmR7VLOAVGLWAINzr8v9TE0992.gif";
        URI uri = URI.create(filePath);
        String group = uri.getPath().split("/")[1];
        String path = uri.getPath().substring(group.length() + 2);

        System.out.println("group = " + group);
        System.out.println("path = " + path);
    }

    @Autowired
    private StudentService studentService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PeopleMapper peopleMapper;

    @GetMapping("/listP")
    public Integer peopleList(){
        return peopleMapper.getIdByName("1");
    }

    @GetMapping("/listP1")
    public People listP1(){
        return peopleMapper.getPeopleById(1);
    }

    @GetMapping("/listP2")
    public List<People> listP2(){
        return peopleMapper.listPeople("1");
    }

    @GetMapping("/listP3")
    public List<Map<String, Object>> listP3(){
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        return peopleMapper.getListMap(ids);
    }

    @GetMapping("/listS")
    public List<Student> studentList(){
        return studentService.getList();
    }

}
