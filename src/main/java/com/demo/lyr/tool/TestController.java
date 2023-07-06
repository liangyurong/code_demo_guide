package com.demo.lyr.tool;

import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {
    /**
     * 导出数据到excel
     */
    @GetMapping("/export/excel")
    public void exportExcel(HttpServletResponse response) throws Exception {

        new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {

        // 等同String str = new String("aaa")
        String str = "aaa"; // str直接存储在栈内存中的常量池
        new TestController().print(str); 				//  print方法的str = aaabbb
        System.out.println("main方法的str = "+str);  //  main方法的str = aaa
    }

    public void print(String str){
        str += "bbb";
        System.out.println("print方法的str = "+str);
    }

}
