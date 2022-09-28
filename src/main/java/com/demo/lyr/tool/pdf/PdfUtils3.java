package com.demo.lyr.tool.pdf;

import com.aspose.cells.License;
import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.Workbook;
import sun.misc.BASE64Decoder;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
public class PdfUtils3 {

    public static void main(String[] args){
        String sourceFilePath = "C:\\Users\\yurong333\\Desktop\\包装指令.xlsm";
        String desFilePath = "C:\\Users\\yurong333\\Desktop\\aa.pdf";
        excel2pdf(sourceFilePath, desFilePath);
    }

    /**
     * 获取license 去除水印
     *
     * @return
     */
    public static boolean getLicense() {
        try {
            String license = "PExpY2Vuc2U+DQogIDxEYXRhPg0KICAgIDxQcm9kdWN0cz4NCiAgICAgIDxQcm9kdWN0PkFzcG9zZS5Ub3RhbCBmb3IgSmF2YTwvUHJvZHVjdD4NCiAgICAgIDxQcm9kdWN0PkFzcG9zZS5Xb3JkcyBmb3IgSmF2YTwvUHJvZHVjdD4NCiAgICA8L1Byb2R1Y3RzPg0KICAgIDxFZGl0aW9uVHlwZT5FbnRlcnByaXNlPC9FZGl0aW9uVHlwZT4NCiAgICA8U3Vic2NyaXB0aW9uRXhwaXJ5PjIwOTkxMjMxPC9TdWJzY3JpcHRpb25FeHBpcnk+DQogICAgPExpY2Vuc2VFeHBpcnk+MjA5OTEyMzE8L0xpY2Vuc2VFeHBpcnk+DQogICAgPFNlcmlhbE51bWJlcj44YmZlMTk4Yy03ZjBjLTRlZjgtOGZmMC1hY2MzMjM3YmYwZDc8L1NlcmlhbE51bWJlcj4NCiAgPC9EYXRhPg0KICA8U2lnbmF0dXJlPnNOTExLR01VZEYwcjhPMWtLaWxXQUdkZ2ZzMkJ2SmIvMlhwOHA1aXVEVmZaWG1ocHBvK2QwUmFuMVA5VEtkalY0QUJ3QWdLWHhKM2pjUVRxRS8ySVJmcXduUGY4aXROOGFGWmxWM1RKUFllRDN5V0U3SVQ1NUd6NkVpalVwQzdhS2Vvb2hUYjR3MmZwb3g1OHdXb0YzU05wNnNLNmpEZmlBVUdFSFlKOXBqVT08L1NpZ25hdHVyZT4NCjwvTGljZW5zZT4=";
            InputStream is = BaseToInputStream(license);
            License aposeLic = new License();
            aposeLic.setLicense(is);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * base64转inputStream
     *
     * @param base64string
     * @return
     */
    private static InputStream BaseToInputStream(String base64string) {
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64string);
            stream = new ByteArrayInputStream(bytes1);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return stream;
    }

    /**
     * excel 转为pdf 输出。
     *
     * @param sourceFilePath excel文件路径
     * @param desFilePath   pdf 输出文件目录
     */
    public static void excel2pdf(String sourceFilePath, String desFilePath) {
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return;
        }
        try {
            Workbook wb = new Workbook(sourceFilePath);// 原始excel路径
            FileOutputStream fileOS = new FileOutputStream(desFilePath);
            PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
            pdfSaveOptions.setOnePagePerSheet(true);//把内容放在一张PDF 页面上；
            wb.save(fileOS, pdfSaveOptions);
            fileOS.flush();
            fileOS.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
