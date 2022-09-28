//package com.demo.lyr.tool.pdf;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import com.spire.xls.FileFormat;
//import com.spire.xls.Workbook;
///**
// * 收费的pdf工具，不建议使用，有水印
// * @author liangyurong
// * @date 2022-09-21 19:31
// */
//@Api(tags = "PDF工具")
//public class PdfUtils1 {
//
//    public static void main(String[] args) {
//        PdfUtils1.excelToPdf();
//    }
//
//    @ApiOperation(value = "excel转pdf", notes = "excel转pdf")
//    public static void excelToPdf(){
//        //创建一个Workbook实例并加载Excel文件
//        Workbook workbook = new Workbook();
//        workbook.loadFromFile("C:\\Users\\yelin\\Desktop\\批生产指令.xlsm");
//        //设置转换后的PDF页面高宽适应工作表的内容大小
//        workbook.getConverterSetting().setSheetFitToPage(true);
//        //将生成的文档保存到指定路径
//        workbook.saveToFile("C:\\Users\\yelin\\Desktop\\demo.pdf", FileFormat.PDF);
//    }
//
//}
