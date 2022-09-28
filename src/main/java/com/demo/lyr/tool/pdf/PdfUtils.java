package com.demo.lyr.tool.pdf;

/**
 * 免费的pdf工具
 */
import java.io.FileInputStream;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import java.util.Iterator;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Phrase;
public class PdfUtils {

    public static void main(String[] args) throws Exception {
        excel2Pdf();
    }

    public static void excel2Pdf() throws Exception {
        // 本地excel
        File file = new File("C:\\Users\\yelin\\Desktop\\aa.xls");

        FileInputStream inputDocument = new FileInputStream(file);
        // 将工作簿读入 HSSFWorkbook
        HSSFWorkbook book = new HSSFWorkbook(inputDocument);

//        book.getPhysicalNumberOfCells();


        // 将工作表读入 HSSFSheet
        HSSFSheet sheet = book.getSheetAt(0);

        // 遍历行
        Iterator<Row> rowIterator = sheet.iterator();
        // 此时我们将创建输出 PDF 文档对象
        Document iTextXls2Pdf = new Document();
        // 设置pdf保存路径
        FileOutputStream fileOutputStream = new FileOutputStream("Excel2PDF.pdf");
        PdfWriter.getInstance(iTextXls2Pdf,fileOutputStream );
        iTextXls2Pdf.open();
        //  我们在 Excel 工作表中有两列，因此我们创建了一个包含两列的 PDF 表
        // 注意：如果您愿意，有一些方法可以使这种动态化。
        // todo lyr 根据excel工作表的列来设置excel
        HSSFRow hssfRow = sheet.getRow(0);
        int excelColumnNum = hssfRow.getPhysicalNumberOfCells();
        PdfPTable myTable = new PdfPTable(excelColumnNum);

        // 我们将使用下面的对象将新数据动态添加到表中
        PdfPCell tableCell;
        // 循环遍历行
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                // 获取单元格
                Cell cell = cellIterator.next();
                // 识别单元格类型
                switch (cell.getCellType()) {
                    case STRING:
                        // 构建每个单元格
                        tableCell = new PdfPCell(new Phrase(cell.getStringCellValue()));
                        myTable.addCell(tableCell);
                        // todo
                        System.out.println(String.valueOf(cell.getStringCellValue()));
                        break;
                    case NUMERIC:
                        tableCell = new PdfPCell(new Phrase(String.valueOf(cell.getNumericCellValue())));
                        myTable.addCell(tableCell);
                        // todo
                        System.out.println(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case BOOLEAN:
                        tableCell = new PdfPCell(new Phrase( String.valueOf(cell.getBooleanCellValue())));
                        myTable.addCell(tableCell);
                        break;
                }
            }
        }
        // 最后将表格添加到PDF文档
        iTextXls2Pdf.add(myTable);

        // todo lyr 转为字节流

        iTextXls2Pdf.close();
        // 创建了pdf文件,关闭xls流
        inputDocument.close();
    }

}
