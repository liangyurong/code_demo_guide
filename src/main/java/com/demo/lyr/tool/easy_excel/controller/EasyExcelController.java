package com.demo.lyr.tool.easy_excel.controller;


import com.demo.lyr.tool.easy_excel.EasyExcelUtil;
import com.demo.lyr.tool.easy_excel.vo.StudentExcelVO;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class EasyExcelController {

    /**
     * 客户端导出数据到excel
     * @param response
     */
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response){
        List<StudentExcelVO> list = new ArrayList<>();
        list.add(new StudentExcelVO(1,"小1"));
        list.add(new StudentExcelVO(2,"小2"));
        list.add(new StudentExcelVO(3,"小3"));
        if (!CollectionUtils.isEmpty(list)) {
            try {
                EasyExcelUtil.export(response,"test","原始数据",list,StudentExcelVO.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
