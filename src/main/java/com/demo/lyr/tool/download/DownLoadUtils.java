package com.demo.lyr.tool.download;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 下载工具类
 * @author liangyurong
 * @date 2022-08-09
 */
public class DownLoadUtils {

    public static byte[] downloadDefaultTemplate() throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("src/main/resources/template/dd.zip");
        if (!resource.exists()) {
            throw new Exception("文件不存在!");
        }
        InputStream bis = null;
        try {
            bis = new BufferedInputStream(resource.getInputStream());
            byte[] buffer = new byte[bis.available()];
            bis.read(buffer);
            return buffer;
        } catch (IOException ex) {
            System.out.println("文件获取失败："+ex.toString());
        } finally {
            IOUtils.closeQuietly(bis);
        }
        throw new Exception("下载失败");
    }

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
            // 设置缓冲区
            int len;
            byte[] by = new byte[1024];
            // 将input流写到缓冲区，然后经过outputStream返回客户端
            while ((len = byteArrayInputStream.read(by)) != -1) {
                outputStream.write(by, 0, len);
            }
            outputStream.flush();
        }catch (IOException e){
            System.out.println("客户端取消下载或连接断开: "+e);
        }catch (Exception e){
            System.out.println("导出/下载文件错误: "+e);
        }

    }

}
