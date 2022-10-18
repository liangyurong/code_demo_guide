package com.demo.lyr.tool.excel_poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * poi工具类
 * @author liangyurong
 * @date 2022-10-17
 */
public class PoiUtils_lyr {

    public static void main(String[] args) throws Exception {
        readFromLocalXlsx();
    }

    private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";
    private static final String PATH = "src/main/resources/temp/";

    // =========================== 03 =======================================
    /**
     * 读取本地temp文件夹的xls文件
     * @throws Exception
     */
    public static void readFromLocalXls() throws Exception{
        // 获取文件流
        FileInputStream fis = new FileInputStream("E:\\IdeaProject\\code_demo_guide\\src\\main\\resources\\temp\\03.xls");
        // 创建工作簿
        Workbook workbook = new HSSFWorkbook(fis);
        // 获取工作表 （对应excel的sheet）
        Sheet sheet = workbook.getSheetAt(0);
        // 得到行
        Row row = sheet.getRow(0);
        // 得到列
        Cell cell = row.getCell(0);
        // 6、关闭流
        fis.close();
    }

    /**
     * 将03excel写到本地temp文件夹
     * @throws Exception
     */
    public static void writeToLocalXls() throws Exception{
        // 1、创建03工作簿
        Workbook workbook = new HSSFWorkbook();
        // 2、创建工作表 （对应excel的sheet）
        Sheet sheet = workbook.createSheet("sheet1");
        // 3、创建一行
        Row row = sheet.createRow(0);
        // 4、创建一个单元格，并填充内容(在此可扩展单元格内容)
        Cell cell = row.createCell(0);
        cell.setCellValue("单元格内容");
        // 5、生成表（生成IO流）
        FileOutputStream fos = new FileOutputStream(PATH + "03" + XLS);
        workbook.write(fos);
        // 6、关闭流
        fos.close();
    }

    /**
     * 将大数据的03excel写到本地temp文件夹
     * remark: 最多写65536行数据，超出报异常
     * @throws Exception
     */
    public static void writeBigDataToLocalXls() throws Exception{
        // 1、创建03工作簿
        Workbook workbook = new HSSFWorkbook();
        // 2、创建工作表 （对应excel的sheet）
        Sheet sheet = workbook.createSheet("sheet1");
        // 3、写入内容(最多写65536行数据，超出报异常)
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            // 创建一行
            Row row = sheet.createRow(rowNum);
            // 创建单元格，并填充内容(在此可扩展单元格内容)
            for(int cellNUm = 0 ;cellNUm < 10 ; cellNUm++){
                Cell cell = row.createCell(cellNUm);
                cell.setCellValue(cellNUm);
            }
        }
        // 4、生成表（生成IO流）
        FileOutputStream fos = new FileOutputStream(PATH + "BigData" + XLS);
        workbook.write(fos);
        // 5、关闭流
        fos.close();
    }

    // =========================== 07 =======================================

    /**
     * 读取本地temp文件夹的xlsx文件
     * @throws Exception
     */
    public static void readFromLocalXlsx() throws Exception{
        // 获取文件流
        FileInputStream fis = new FileInputStream("E:\\IdeaProject\\code_demo_guide\\src\\main\\resources\\temp\\07.xlsx");
        // 创建工作簿
        Workbook workbook = new XSSFWorkbook(fis);
        // 获取工作表 （对应excel的sheet）
        Sheet sheet = workbook.getSheetAt(0);
        // 得到行
        Row row = sheet.getRow(0);
        // 得到列
        Cell cell = row.getCell(0);
        System.out.println(cell.getStringCellValue());
        // 6、关闭流
        fis.close();
    }

    /**
     * 将07excel写到本地temp文件夹
     * @throws Exception
     */
    public static void writeToLocalXlsx() throws Exception{
        // 1、创建07工作簿
        Workbook workbook = new XSSFWorkbook();
        // 2、创建工作表 （对应excel的sheet）
        Sheet sheet = workbook.createSheet("sheet1");
        // 3、创建一行
        Row row = sheet.createRow(0);
        // 4、创建一个单元格，并填充内容(在此可扩展单元格内容)
        Cell cell = row.createCell(0);
        cell.setCellValue("单元格内容");
        // 5、生成表（生成IO流）
        FileOutputStream fos = new FileOutputStream(PATH + "test07" + XLSX);
        workbook.write(fos);
        // 6、关闭流
        fos.close();
    }

    /**
     * 将大数据的07excel写到本地temp文件夹
     * remark: 较慢
     * @throws Exception
     */
    public static void writeBigDataToLocalXlsx() throws Exception{
        // 1、创建07工作簿
        Workbook workbook = new XSSFWorkbook();
        // 2、创建工作表 （对应excel的sheet）
        Sheet sheet = workbook.createSheet("sheet1");
        // 3、写入内容
        for (int rowNum = 0; rowNum < 100000; rowNum++) {
            // 创建一行
            Row row = sheet.createRow(rowNum);
            // 创建单元格，并填充内容(在此可扩展单元格内容)
            for(int cellNUm = 0 ;cellNUm < 10 ; cellNUm++){
                Cell cell = row.createCell(cellNUm);
                cell.setCellValue(cellNUm);
            }
        }
        // 4、生成表（生成IO流）
        FileOutputStream fos = new FileOutputStream(PATH + "test07Bigdata" + XLSX);
        workbook.write(fos);
        // 5、关闭流
        fos.close();
    }

    /**
     * 将大数据的07excel写到本地temp文件夹
     * remark: 耗内存，但相对快，需要删除临时文件
     * @throws Exception
     */
    public static void writeBigDataToLocalXlsxFast() throws Exception{
        // 1、创建07工作簿
        Workbook workbook = new SXSSFWorkbook();
        // 2、创建工作表 （对应excel的sheet）
        Sheet sheet = workbook.createSheet("sheet1");
        // 3、写入内容
        for (int rowNum = 0; rowNum < 100000; rowNum++) {
            // 创建一行
            Row row = sheet.createRow(rowNum);
            // 创建单元格，并填充内容(在此可扩展单元格内容)
            for(int cellNUm = 0 ;cellNUm < 10 ; cellNUm++){
                Cell cell = row.createCell(cellNUm);
                cell.setCellValue(cellNUm);
            }
        }
        // 4、生成表（生成IO流）
        FileOutputStream fos = new FileOutputStream(PATH + "test07BigdataSuper" + XLSX);
        workbook.write(fos);
        // 5、关闭流
        fos.close();
        // 6、清除临时文件
        ((SXSSFWorkbook)workbook).dispose();

    }

    // ============ hutool操作excel =================


}
