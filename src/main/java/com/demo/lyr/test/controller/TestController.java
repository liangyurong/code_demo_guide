package com.demo.lyr.test.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class TestController {


    public static void main(String[] args) {
//        List<String> list = new LinkedList<>();
//        Collections.addAll(list, "a", "b", "c");
//
//        List<String> collect = list.stream().map(base -> base + "ss").collect(Collectors.toList());
//
//
//        Set<String> set = new HashSet<>();
//        set.add("a");
//        set.add("b");
//        set.add("c");
//        List<String> collect1 = set.stream().map(base -> base + "ss").collect(Collectors.toList());

        Map<Object, String> map = new HashMap<>();
        map.put("123","111");
        map.put("456","222");

        Set<Object> set = map.keySet();
        ArrayList<Object> list = new ArrayList<>(set);

        list.forEach(System.out::println);


    }




}
