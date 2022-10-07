package com.demo.lyr.tool;

import com.demo.lyr.contant.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * IO流工具类
 * @author liangyurong
 * @date 2022-10-01 02:19
 */
public class IOUtils {

    /**
     * 字节数组转File
     * todo 关于路径还需要优化
     * @param bytes 字节数组
     */
    public static File byteArrayToFile(byte[] bytes) throws IOException {
        // 路径
        String dirPath = PathUtils.getAbsolutePath(this.getClass()) + File.separator + "xlsTemp";
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
     * @param catalogName 目录名称
     */
    public static void createCatalog(String catalogName) {
        // 在target目录下创建temp文件夹
        String dirPath = PathUtils.getAbsolutePath(this.getClass()) + File.separator + catalogName;
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            // 创建目录
            dirFile.mkdirs();
        }
    }

    /**
     * 删除某个目录的某个文件
     * @param catalogName 目录名
     * @param fileNameList 文件名列表 （带后缀吗？）
     * @return
     */
    public static boolean deleteFilesInCatalog(String catalogName, List<String> fileNameList){
        return true;
    }

    /**
     * 删除某个目录的所有文件
     * @param catalogName 目录名
     * @return
     */
    public static boolean deleteAllFileInCatalog(String catalogName){
        return true;
    }

}
