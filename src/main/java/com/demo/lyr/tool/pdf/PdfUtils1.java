package com.demo.lyr.tool.pdf;

import com.spire.xls.Worksheet;
import com.spire.xls.collections.WorksheetsCollection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.spire.xls.Workbook;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
        引入依赖即可，不需要直接下载jar包
        <dependency>
            <groupId>e-iceblue</groupId>
            <artifactId>spire.xls</artifactId>
            <version>4.9.0</version>
        </dependency>
        <repository>
            <id>com.e-iceblue</id>
            <name>e-iceblue</name>
            <url>http://repo.e-iceblue.com/nexus/content/groups/public/</url>
        </repository>
 */

/**
 * 收费的pdf工具，不建议使用，有水印
 * @author liangyurong
 * @date 2022-09-21 19:31
 */
@Api(tags = "PDF工具")
public class PdfUtils1 {

    public static void main(String[] args) {
        PdfUtils1.excelToPdf();
    }

    @ApiOperation(value = "excel转pdf", notes = "excel转pdf")
    public static void excelToPdf(){
        //创建一个Workbook实例并加载Excel文件
        Workbook workbook = new Workbook();
        workbook.loadFromFile("C:\\Users\\yurong333\\Desktop\\包装指令.xlsm");
        //设置转换后的PDF页面高宽适应工作表的内容大小
        workbook.getConverterSetting().setSheetFitToPage(true);
        //将生成的文档保存到指定路径
        // workbook.saveToFile("C:\\Users\\yurong333\\Desktop\\包装指令.pdf", FileFormat.PDF);

        // 获取excel的sheet数量
        WorksheetsCollection worksheetNum = workbook.getWorksheets();
        System.out.println("sheet的数量="+worksheetNum);

        // 将excel的第二个sheet列打印为pdf
        Worksheet worksheet = workbook.getWorksheets().get(1);
         worksheet.saveToPdf("C:\\Users\\yurong333\\Desktop\\包装指令sheet第二列.pdf");

    }

    public static void saveToPdfStream(HttpServletResponse response) throws IOException {
        //创建一个Workbook实例并加载Excel文件
        Workbook workbook = new Workbook();
        workbook.loadFromFile("C:\\Users\\yurong333\\Desktop\\包装指令.xlsm");
        //设置转换后的PDF页面高宽适应工作表的内容大小
        workbook.getConverterSetting().setSheetFitToPage(true);
        // 将excel的第1个sheet列打印为pdf
        Worksheet worksheet = workbook.getWorksheets().get(0);
        // worksheet.saveToPdf("C:\\Users\\yurong333\\Desktop\\包装指令sheet第二列.pdf");
        ServletOutputStream outputStream = response.getOutputStream();
        worksheet.saveToPdfStream(outputStream);
    }


}
