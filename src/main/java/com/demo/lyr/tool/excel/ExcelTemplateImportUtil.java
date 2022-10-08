package com.demo.lyr.tool.excel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * excel 导入导出统一工具
 */
@Slf4j
public class ExcelTemplateImportUtil {


    /**
     * 导出或下载模板文件使用
     * @param response
     * @param bytes
     * @param templateName  需要包含文件后缀
     */
    public static void responseToClient(HttpServletResponse response, byte[] bytes, String templateName) {
        try {
            response.setHeader("Content-disposition", "attachment;filename=\"" + templateName +"\"; filename*=utf-8'zh_cn'"+templateName );
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            OutputStream outputStream = response.getOutputStream();
            //设置缓冲区
            int len;
            byte[] by = new byte[1024];
            //将input流写到缓冲区，然后经过outputStream返回客户端
            while ((len = byteArrayInputStream.read(by)) != -1) {
                outputStream.write(by, 0, len);
            }
            outputStream.flush();
        }catch (IOException e){
            log.error("客户端取消下载或连接断开",e);
        }catch (Exception e){
            log.error("导出/下载文件错误",e);
        }

    }

}

