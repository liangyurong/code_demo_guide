package com.demo.lyr.tool.io;

import com.demo.lyr.contant.Constant;
import com.demo.lyr.tool.PathUtils;
import com.demo.lyr.tool.excel.ExcelTemplateImportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * IO流工具类
 * @author liangyurong
 * @date 2022-10-01 02:19
 */
@Slf4j
public class IOUtils_LYR {

    /**
     * File转字节数组
     * remark: 文件多大会导致异常？需要异步处理吗？同步处理在什么情况下会出现问题？
     */
    public static byte[] fileToByteArray(File file) throws FileNotFoundException {
        if(!file.exists()){
            throw new FileNotFoundException("文件没找到");
        }
        return new byte[1024];
    }


    /**
     * 字节数组转File
     * remark: 需要指定某个文件路径
     * @param bytes 字节数组
     */
    public static File byteArrayToFile(byte[] bytes) throws IOException {
        // 路径 todo 哪里的路径，是该class文件下的路径吗
        String dirPath = PathUtils.getAbsolutePath(IOUtils_LYR.class) + File.separator + "xlsTemp";
        // 路径+文件名称
        String temFilePath = dirPath + UUID.randomUUID() + Constant.XLSM;
        // 写入字节
        Files.write(Paths.get(temFilePath), bytes);
        // 创建file
        File file = new File(temFilePath);
        return file;
    }

    /**
     * 创建新目录
     * remark: 如果目录已存在，先删除目录和目录里面的所有文件
     * @param catalogName 目录名称
     */
    public static void createCatalog(String catalogName) {
        try {
            // 在target目录下创建temp文件夹
            String dirPath = PathUtils.getAbsolutePath(IOUtils_LYR.class) + File.separator + catalogName;
            File dirFile = new File(dirPath);
            if (dirFile.exists()) {
                // 如果已存在, 删除该目录下所有文件
                FileUtils.deleteDirectory(dirFile);
            }
            boolean isMkdir = dirFile.mkdirs();
        } catch (IOException e) {
            log.error("创建一个空临时文件夹失败, {}", e.getMessage());
            // throw new ResponseException("创建一个空临时文件夹失败");
        }

    }

    /**
     * 删除某个目录的某个文件 todo
     * @param catalogName 目录名
     * @param fileNameList 文件名列表 （带后缀吗？）
     * @return
     */
    public static boolean deleteFilesInCatalog(String catalogName, List<String> fileNameList){
        return true;
    }

    /**
     * 删除目录的所有文件，但不删除目录
     * @param catalogName 目录名称
     * @return
     */
    public static void deleteAllFile(String catalogName){
        File dirFile = new File(PathUtils.getAbsolutePath(IOUtils_LYR.class) + File.separator + catalogName);
        if (dirFile.exists()) {
            try {
                FileUtils.cleanDirectory(dirFile);
            } catch (IOException e) {
                log.info("删除临时文件夹失败");
            }
        }
    }

    /**
     *
     * 删除某个目录和目录的所有文件
     * remark: import org.apache.commons.io.FileUtils;
     * @param catalogPath 目录路径，比如 D:\羽绒333\code_demo_guide\src\main\resources\template
     * @return
     */
    public static boolean deleteAllFileInDirectory(String catalogPath){
        File file = new File(catalogPath);
        return FileUtils.deleteQuietly(file);
    }

    /**
     * 删除文件夹
     * @param catalogPath 文件夹路径
     */
    public static void deleteDirectory(String catalogPath) {
        File file = new File(catalogPath);
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取客户端上传文件的文件名称和后缀
     * example： 测试.xls  生产.txt
     */
    public static String getFileNameFromClient(MultipartFile multipartFile){
        if(null == multipartFile){
            log.info("没有上传文件！");
            return "";
        }
        // 方法2：return multipartFile.getOriginalFilename()
        return FilenameUtils.getName(multipartFile.getOriginalFilename());
    }

    /**
     * 获取客户端上传的文件的后缀名,包含小数点
     * example: .xls  .txt
     * @param multipartFile 客户端上传的文件
     * @return
     */
    public static String getSuffixNameFromClient(MultipartFile multipartFile){
        if(null == multipartFile){
            log.info("没有上传文件！");
            // todo 是否应该抛出异常？
            return "";
        }
        return multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
    }

    /**
     * 获取本地文件名和后缀
     */
    public static String getFileNameFromLocal(String filePath){
        return "";
    }

    /**
     * 下载template下的默认excel文件，并返回给客户端
     * remark : 编译之后，template文件夹的路径：target/classes/template
     */
    public static void downloadDefaultExcelTemplate(HttpServletResponse response){
        // 文件名 (需要固定文件名)
        String fileName = "领发料单.xlsm";
        String templateName = "";
        byte[] fileByteBuffer = new byte[1024];

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        org.springframework.core.io.Resource resource = resolver.getResource("classpath:template" + File.separator + fileName);
        if (!resource.exists()) {
            // 生产环境中应该抛出异常
            // throw new ResponseException(RespCodeEnum.TEMPLATE_NOT_EXIST);
            log.info("模板不存在");
        }
        try (InputStream inputStream = resource.getInputStream()) {
            // 最后返回
            templateName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
            fileByteBuffer = org.apache.commons.io.IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            // throw new ResponseException(RespCodeEnum.FILE_DOWNLOAD_FAILED);
            log.info("文件获取失败");
        }
        ExcelTemplateImportUtil.responseToClient(response, fileByteBuffer, templateName);
    }

}
