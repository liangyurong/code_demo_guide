package com.demo.lyr.tool.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.itextpdf.html2pdf.HtmlConverter;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import com.itextpdf.html2pdf.ConverterProperties;

public class PdfUtils2 {


    /**
     * excel转pdf流
     * @param response
     * @return
     */
    public static byte[] excel2Pdf(HttpServletResponse response){
        byte[] pdfBytes = null;
        try {
            ByteArrayOutputStream pdfOutStream = new ByteArrayOutputStream();
            String fileName = "C:\\Users\\yelin\\Desktop\\aa.xls";
            InputStream inputStream = new FileInputStream(fileName);

            // convert xls stream to html - w3 document
            org.w3c.dom.Document w3Document = ExcelToHtmlConverter.process(inputStream);

            // convert above w3 document to html input stream
            ByteArrayOutputStream htmlOutputStream = new ByteArrayOutputStream();
            Source xmlSource = new DOMSource(w3Document);
            Result outputTarget = new StreamResult(htmlOutputStream);
            TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
            InputStream htmlInputStream = new ByteArrayInputStream(htmlOutputStream.toByteArray());

            //convert html inputstream to pdf out stream
            ConverterProperties converterProperties = new ConverterProperties();
            HtmlConverter.convertToPdf(htmlInputStream, pdfOutStream, converterProperties);

            pdfBytes = pdfOutStream.toByteArray();

            //write to physical pdf file
            OutputStream out = new FileOutputStream(fileName.substring(0, fileName.indexOf(".")) + ".pdf");
            out.write(pdfBytes);
            out.close();
        } catch (Exception e) {
            // log exception details and throw custom exception
        }finally {
            // 返回pdf字节流
            // todo lyr
            System.out.println("返回pdf流啊==========");
            if(pdfBytes!=null){
                System.out.println("字节流长度======"+pdfBytes.length);
            }else {
                System.out.println("字节流长度======000000000");
            }
            return pdfBytes;
        }
    }

    /**
     * excel流转html流
     * @return
     */
    public static byte[] excel2Html(){
       return null;
    }

    /**
     * html流转pdf流
     * @return
     */
    public static byte[] html2Pdf(){
        return null;
    }
}
