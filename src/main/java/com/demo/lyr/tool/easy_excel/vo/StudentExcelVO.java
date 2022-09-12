package com.demo.lyr.tool.easy_excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义需要导出的excel列名
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentExcelVO {
    /**
     * 需要添加这个注解
     */
    @ExcelProperty("年龄")
    private int age;
    @ExcelProperty("名字")
    private String name;
}
