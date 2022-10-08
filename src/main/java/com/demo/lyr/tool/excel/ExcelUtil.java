package com.demo.lyr.tool.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: excel导出工具类
 * @Author: zhengfu
 * @Date: 2020/12/5
 */
@Slf4j
public class ExcelUtil {

    public static final String SHEET_NAME = "sheet1";

    public static final String XLS = ".xls";

    public static final String XLSX = ".xlsx";

    public static final String XLSM = ".xlsm";

    /**
     * excel文件允许导入的格式
     */
    public static final List<String> EXCEL_PATTERNS = Arrays.asList("xlsx", "xls", "xlsm");

    /**
     * 设值
     *
     * @param row
     * @param titles
     * @param style
     */
    public static void setValue(XSSFRow row, String[] titles, XSSFCellStyle style) {
        int i = 0;
        for (String title : titles) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(style);
            i++;
        }
    }

    /**
     * 设置单元格边框
     */
    public static void setBoarder(XSSFCellStyle style) {
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
    }

    /**
     * Excel模板导出
     *
     * @param titles
     * @param response
     * @throws Exception
     */
    public static void exportTitles(String[] titles, HttpServletResponse response) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        String fileName = URLEncoder.encode(SHEET_NAME, StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + XLSX +"\"; filename*=utf-8'zh_cn'"+fileName + XLSX );
        response.setContentType("application/octet-stream;charset=UTF-8");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(SHEET_NAME);
        XSSFRow row = sheet.createRow(0);

        // 创建一个单元格样式
        XSSFCellStyle style = workbook.createCellStyle();

        // 填充色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(226, 239, 218)));
        //表格
        setBoarder(style);
        setValue(row, titles, style);
        style.setAlignment(HorizontalAlignment.CENTER);

        //设置列宽
        for (int i = 0; i < titles.length; i++) {
            sheet.setColumnWidth(i, 7 * 2 * 256);
        }
        //内容样式
        XSSFCellStyle contentStyle = workbook.createCellStyle();
        contentStyle.cloneStyleFrom(style);
        contentStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 255)));

        // 第七步，将文件输出到客户端浏览器
        try {
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("excel导出异常", e);
        }
    }

    /**
     * Excel模板导出
     *
     * @param content
     * @param response
     * @throws Exception
     */
    public static void exportWithContent(List<List<String>> content, HttpServletResponse response) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        String fileName = URLEncoder.encode(SHEET_NAME, StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + XLSX +"\"; filename*=utf-8'zh_cn'"+fileName + XLSX );
        response.setContentType("application/octet-stream;charset=UTF-8");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(SHEET_NAME);

        // 创建一个单元格样式
        XSSFCellStyle style = workbook.createCellStyle();
        // 填充色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(226, 239, 218)));
        setBoarder(style);
        style.setAlignment(HorizontalAlignment.CENTER);

        //内容样式
        XSSFCellStyle contentStyle = workbook.createCellStyle();
        contentStyle.cloneStyleFrom(style);
        contentStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 255)));

        //设置列宽
        for (int i = 0; i < content.get(0).size(); i++) {
            sheet.setColumnWidth(i, 7 * 2 * 256);
        }
        for (int i = 0; i < content.size(); i++) {
            XSSFRow row = sheet.createRow(i);
            //表格
            List<String> strings = content.get(i);
            for (int j = 0; j < strings.size(); j++) {
                String s = strings.get(j);
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(s);
                cell.setCellStyle(style);
            }
        }
        // 第七步，将文件输出到客户端浏览器
        try {
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("excel导出异常", e);
        }
    }

    /**
     * Excel模板导入
     *
     * @param file
     * @return
     */
    public static List<List<String>> excelImport(MultipartFile file)  {
        List<List<String>> stringList = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        if (file.isEmpty() || StringUtils.isEmpty(fileName)) {
            return stringList;
        }
        Workbook workbook =null;
        try {
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            // throw new ResponseException(RespCodeEnum.IMPORT_FILE_FORMAT_WRONG);
        }
        Sheet modelSheet = workbook.getSheet(SHEET_NAME);
        short lastCellNum = modelSheet.getRow(0).getLastCellNum();
        //遍历行数据
        for (int i = 0; i < modelSheet.getPhysicalNumberOfRows(); i++) {
            Row row = modelSheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> strings = new ArrayList<>();
            //遍历单元格
            for (int j = 0; j < lastCellNum; j++) {
                if (row.getCell(j) == null) {
                    strings.add("");
                } else {
                    row.getCell(j).setCellType(CellType.STRING);
                    strings.add(row.getCell(j).getStringCellValue().trim());
                }
            }
            stringList.add(strings);
        }
        return stringList;
    }

    /**
     * 通过exce判断文件是不是excel
     *
     * @param fileName
     * @return
     */
    public static boolean isExcelFile(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return false;
        }
        return EXCEL_PATTERNS.contains(FileUtil.getExtensionName(fileName));
    }

    /**
     * 解析文件返回workbook
     * @param file 不能为空
     * @return
     */
    public static Workbook getWorkbook( File file ) {
        try {
            return WorkbookFactory.create(file);
        } catch (Exception e) {
            log.error("解析excel文件错误",e);
            throw new ResponseException(RespCodeEnum.NOT_PRODURE_INSPECT_PROGROM);
        }
    }


    /**
     * 解析文件流返回workbook
     * @param inputStream 不能为空
     * @return
     */
    public static Workbook getWorkbook(InputStream inputStream) {
        try {
            return WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            log.error("解析excel文件错误",e);
            throw new ResponseException(RespCodeEnum.NOT_PRODURE_INSPECT_PROGROM);
        }
    }
    /**
     * 以字符串格式获取一个单元格的数据
     *
     * @param cell
     * @return
     */
    public static String getCellStringValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    /**
     * 以字符串格式获取一个单元格的数据
     *
     * @param cell
     * @return
     */
    public static String getSimpleStringCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        String value = null;
        try {
            value = cell.getStringCellValue().trim();
        } catch (Exception e) {
            String str = null;
            if (CellType.FORMULA.equals(cell.getCellType())) {
                str = cell.getCellFormula();
            }
            log.error("单元格获取字符内容异常,行标:{},列标:{},公式内容:{}", cell.getRowIndex(), cell.getColumnIndex(), str, e);
        }
        return value;
    }

    /**
     * 以数字格式获取一个单元格的数据
     *
     * @param cell
     * @return
     */
//    public static Double getNumericCellValue(Cell cell) {
//        if (cell == null) {
//            return null;
//        }
//        cell.setCellType(CellType.NUMERIC);
//        return cell.getNumericCellValue();
//    }

    /**
     * 以数字格式获取一个单元格的数据
     *
     * @param cell
     * @return
     */
    public static Double getSimpleNumericCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        Double value = null;
        try {
            value = cell.getNumericCellValue();
        } catch (Exception e) {
            String str = null;
            if (CellType.FORMULA.equals(cell.getCellType())) {
                str = cell.getCellFormula();
            }
            log.error("单元格获取数字内容异常,行标:{},列标:{},公式内容:{}", cell.getRowIndex(), cell.getColumnIndex(), str, e);
        }
        return value;
    }


    /**
     * 判断行是否为空
     *
     * @param row
     * @return
     */
    public static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && !cell.getCellType().equals(CellType.BLANK)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 清除sheet得所有数据
     *
     * @param sheet
     */
    public static void clearSheet(XSSFSheet sheet) {
        for (Row r : sheet) {
            for (Cell c : r) {
                c.setBlank();
            }
        }
    }


    public static void checkImportExcelFile(MultipartFile file) {
        if (file == null) {
            throw new ResponseException(RespCodeEnum.NOT_PRODURE_INSPECT_PROGROM);
        }
        String filename = file.getOriginalFilename();
        if (StringUtils.isBlank(filename)) {
            throw new ResponseException(RespCodeEnum.NOT_PRODURE_INSPECT_PROGROM);
        }
        if (!EXCEL_PATTERNS.contains(filename.substring(filename.lastIndexOf(".") + 1))) {
            throw new ResponseException(RespCodeEnum.NOT_PRODURE_INSPECT_PROGROM);
        }
    }

    public static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType().equals(CellType.STRING)) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType().equals(CellType.NUMERIC)) {
            //获取到科学计数值
            return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString().trim();
        }
        return null;
    }

    public static String getStringCellValue(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType().equals(CellType.STRING)) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType().equals(CellType.NUMERIC)) {
            //获取到科学计数值
            return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString().trim();
        } else if (cell.getCellType().equals(CellType.FORMULA)) {
            CellValue evaluate = formulaEvaluator.evaluate(cell);
            if (evaluate.getCellType().equals(CellType.NUMERIC)) {
                return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString().trim();
            }
            return evaluate.formatAsString().replace("\"", "").trim();
        }
        return null;
    }

    public static Date getDateCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (DateUtil.isCellDateFormatted(cell)) {
            double value = cell.getNumericCellValue();
            return DateUtil.getJavaDate(value);
        }
        return null;
    }

    public static Date getDateCellValue(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null) {
            return null;
        }
        if (formulaEvaluator.evaluateFormulaCell(cell).equals(CellType.BLANK) || formulaEvaluator.evaluateFormulaCell(cell).equals(CellType.ERROR)) {
            return null;
        }
        if (!formulaEvaluator.evaluateFormulaCell(cell).equals(CellType.STRING) && DateUtil.isCellDateFormatted(cell)) {
            double value = cell.getNumericCellValue();
            return DateUtil.getJavaDate(value);
        }
        return null;
    }

    /**
     * 获取整形数据
     * @param cell
     * @param formulaEvaluator
     * @return
     */
    public static Integer getNumericCellValue(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType().equals(CellType.NUMERIC)
                || formulaEvaluator.evaluateFormulaCell(cell).equals(CellType.NUMERIC)) {
            //获取到科学计数值
            return BigDecimal.valueOf(cell.getNumericCellValue()).intValue();
        }else if (cell.getCellType().equals(CellType.FORMULA)) {
            CellValue evaluate = formulaEvaluator.evaluate(cell);
            if (evaluate.getCellType().equals(CellType.NUMERIC)) {
                return BigDecimal.valueOf(cell.getNumericCellValue()).intValue();
            }
            if (evaluate.getCellType().equals(CellType.STRING)&& !StringUtils.isBlank(cell.getStringCellValue())) {
                if(NumberUtils.isCreatable(cell.getStringCellValue())){
                    return BigDecimal.valueOf(Double.parseDouble(cell.getStringCellValue())).intValue();
                }
            }
        }
        return null;
    }


    /**
     * 获取double类型数据
     * @param cell
     * @param formulaEvaluator
     * @return
     */
    public static Double getNumericCellValue1(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType().equals(CellType.NUMERIC)
                || formulaEvaluator.evaluateFormulaCell(cell).equals(CellType.NUMERIC)) {
            //获取到科学计数值
            return BigDecimal.valueOf(cell.getNumericCellValue()).doubleValue();
        }else if (cell.getCellType().equals(CellType.FORMULA)) {
            CellValue evaluate = formulaEvaluator.evaluate(cell);
            if (evaluate.getCellType().equals(CellType.NUMERIC)) {
                return BigDecimal.valueOf(cell.getNumericCellValue()).doubleValue();
            }
            if (evaluate.getCellType().equals(CellType.STRING) && !StringUtils.isBlank(cell.getStringCellValue())) {
                if(NumberUtils.isCreatable(cell.getStringCellValue())){
                    return BigDecimal.valueOf(Double.parseDouble(cell.getStringCellValue())).doubleValue();
                }
            }
        }
        return null;
    }


    /**
     * 关闭表格
     *
     * @param workbook
     */
    public static void closeWorkBook(Workbook workbook) {
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }

    /**
     * 存在公式空行判断
     *
     * @param row
     * @param formulaEvaluator
     * @return
     */
    public static boolean isRowEmpty(XSSFRow row, FormulaEvaluator formulaEvaluator) {
        if (isRowEmpty(row)) {
            return true;
        }
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && !cell.getCellType().equals(CellType.BLANK)) {
                if (cell.getCellType().equals(CellType.FORMULA)) {
                    CellValue evaluate = formulaEvaluator.evaluate(cell);
                    if (StringUtils.isNotBlank(evaluate.formatAsString().replace("\"", ""))) {
                        return false;
                    }
                }
            }
        }
        return true;

    }


    /**
     * 复制单元格
     *
     * @param sourceCell
     * @param targetCell
     */
    public static void copyCell(Cell sourceCell, Cell targetCell) {
        if (sourceCell == null) {
            return;
        }
        // 不同数据类型处理
        CellType cellTypeEnum = sourceCell.getCellType();
        switch (cellTypeEnum) {
            case STRING:
                targetCell.setCellValue(sourceCell.getStringCellValue());
                break;
            case NUMERIC:
                targetCell.setCellValue(sourceCell.getNumericCellValue());
                break;
            case FORMULA:
                targetCell.setCellFormula(sourceCell.getCellFormula());
                break;
            case BOOLEAN:
                targetCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case ERROR:
                targetCell.setCellValue(sourceCell.getErrorCellValue());
                break;
            default:
                targetCell.setBlank();
                break;
        }
    }


    /**
     * 是/否 中文字符串转换
     * @param str
     * @return
     */
    public static Boolean convertBoolean(String str,Boolean defaultValue) {
        if (StringUtils.isNotBlank(str) && "是".equals(str)) {
            return true;
        }
        if (StringUtils.isNotBlank(str) && "否".equals(str)) {
            return false;
        }
        return defaultValue;
    }

    /**
     * 清空excel指定的行数 * 列数范围
     * @param templateResourceSheet
     * @param rowNum
     * @param columnNum
     */
    public static void clearSheet(Sheet templateResourceSheet, Integer rowNum, Integer columnNum) {
        for(int i = 0; i <rowNum ; i++) {
            for(int j = 0; j <columnNum ; j++){
                Row row = templateResourceSheet.getRow(i);
                if(row != null){
                    Cell cell = row.getCell(j);
                    if(cell != null){
                        cell.setBlank();
                    }
                }
            }
        }
    }
}


