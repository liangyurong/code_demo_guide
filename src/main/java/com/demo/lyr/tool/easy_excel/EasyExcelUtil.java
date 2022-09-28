package com.demo.lyr.tool.easy_excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author xiong
 * @Date 2021/9/9 18:04
 */
@Slf4j
@Component
public class EasyExcelUtil {

    // 示例
    public static void main(String[] args) {

        // response,文件名称，sheet名称，需要导出的数据，
        // EasyExcelUtil.export(response, "生产工单-报工记录", "报工记录-原始数据", dataList, ReporterRecordExcelVO.class);
    }

    /**
     * 写出一个excel文件到本地
     * 将类型所有加了 @ExcelProperty 注解的属性全部写出
     *
     * @param fileName  文件名 不要后缀
     * @param sheetName sheet名
     * @param data      写出的数据
     * @param clazz     要写出数据类的Class类型对象
     * @param <T>       写出的数据类型
     */
    public static <T> void writeExcel(String fileName, String sheetName, List<T> data, Class<T> clazz) {
        writeExcel(null, fileName, sheetName, data, clazz);
    }


    /**
     * 按照指定的属性名进行写出一个excel
     *
     * @param attrName  指定的属性名 必须与数据类型的属性名一致
     * @param fileName  文件名 不要后缀
     * @param sheetName sheet名
     * @param data      要写出的数据
     * @param clazz     要写出数据类的Class类型对象
     * @param <T>       要写出的数据类型
     */
    private static <T> void writeExcel(Set<String> attrName, String fileName, String sheetName, List<T> data, Class<T> clazz) {
        fileName = StringUtils.isBlank(fileName) ? "test" : fileName;
        sheetName = StringUtils.isBlank(sheetName) ? "sheet0" : sheetName;
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            write(fos, attrName, sheetName, data, clazz);
        } catch (Exception exception) {
            log.error("解析异常", exception);

        }


    }

    /**
     * 读取指定格式的excel文档
     *
     * @param fileName 文件名
     * @param clazz    数据类型的class对象
     * @param <T>      数据类型
     * @return
     */
    public static <T> List<T> readExcel(String fileName, Class<T> clazz) {
        return readExcel(fileName, clazz, null);
    }

    /**
     * 取 指定格式的 excel文档
     * 注意一旦传入自定义监听器，则返回的list为空，数据需要在自定义监听器里面获取
     *
     * @param fileName     文件名
     * @param clazz        数据类型的class对象
     * @param readListener 自定义监听器
     * @param <T>          数据类型
     * @return
     */
    private static <T> List<T> readExcel(String fileName, Class<T> clazz, ReadListener<T> readListener) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            return read(fis, clazz);
        } catch (Exception exception) {
            log.error("解析异常", exception);
        }
        return null;
    }


    /**
     * 导出一个excel
     * 导出excel所有数据
     *
     * @param response
     * @param fileName  文件名 最好为英文，不要后缀名
     * @param sheetName sheet名
     * @param data      要写出的数据
     * @param clazz     要写出数据类的Class类型对象
     * @param <T>       要写出的数据类型
     */
    public static <T> void export(HttpServletResponse response, String fileName, String sheetName, List<T> data, Class<T> clazz) throws IOException{
        export(response, null, fileName, sheetName, data, clazz);
    }

    /**
     * 按照指定的属性名进行写出一个excel
     *
     * @param response
     * @param attrName  指定的属性名 必须与数据类型的属性名一致
     * @param fileName  文件名 最好为英文，不要后缀名
     * @param sheetName sheet名
     * @param data      要写出的数据
     * @param clazz     要写出数据类的Class类型对象
     * @param <T>       要写出的数据类型
     */
    public static <T> void export(HttpServletResponse response, Set<String> attrName, String fileName, String sheetName, List<T> data, Class<T> clazz) throws IOException{
        fileName = StringUtils.isBlank(fileName) ? "test" : fileName;
        sheetName = StringUtils.isBlank(sheetName) ? "sheet0" : sheetName;
        try (OutputStream os = response.getOutputStream()) {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
            response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + ExcelUtil.XLSX +"\"; filename*=utf-8'zh_cn'"+fileName + ExcelUtil.XLSX );
            write(os, attrName, sheetName, data, clazz);
        } catch (IOException e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            log.error("解析异常", e);
        }
    }

    public static <T>  List<T> analysisExcel(MultipartFile multipartFile, Class<T> clazz) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            List<T> data = new LinkedList<>();
            BatchPageReadListener<T> readListener = new BatchPageReadListener<T>(data::addAll);
            EasyExcelFactory.read(inputStream, clazz, readListener).sheet().doRead();
            return data;
        } catch (IOException e) {
            log.error("解析异常", e);
        }
        return new ArrayList<>();
    }

    private static <T> void write(OutputStream os, Set<String> attrName, String sheetName, List<T> data, Class<T> clazz) {
        ExcelWriterBuilder write = EasyExcel.write(os, clazz);
        // 如果没有指定要写出那些属性数据，则写出全部
        if (!CollectionUtils.isEmpty(attrName)) {
            write.includeColumnFiledNames(attrName);
        }
        write.registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet(sheetName).doWrite(data);
    }


    private static <T> List<T> read(InputStream in, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        EasyExcel.read(in, clazz, new AnalysisEventListener<T>() {
            @Override
            public void invoke(T data, AnalysisContext context) {
                list.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                log.info("解析完成");
            }
        }).sheet().doRead();
        return list;
    }


}
