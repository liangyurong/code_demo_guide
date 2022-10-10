package com.demo.lyr.tool.io;

import com.demo.lyr.tool.PathUtils;
import com.itextpdf.io.exceptions.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class IOUtilsController {

    @GetMapping("tt")
    public void get(){

    }

    /**
     * 删除某个目录
     */
    @GetMapping("/deleteDirectory")
    public void deleteDirectory(){
//        String catalogPath = "D:\\羽绒333\\code_demo_guide\\target\\classes\\temp";
        String catalogPath = PathUtils.getAbsolutePath(this.getClass()) + File.separator + "temp";
        IOUtils_LYR.deleteDirectory(catalogPath);
    }

    /**
     * 删除某个目录和目录的所有文件
     */
    @GetMapping("/deleteAllFileInCatalog")
    public void deleteAllFileInCatalog(){
        // String catalogPath = "D:\\羽绒333\\code_demo_guide\\target\\classes\\temp";
        String catalogPath = PathUtils.getAbsolutePath(this.getClass()) + File.separator + "temp";
        boolean b = IOUtils_LYR.deleteAllFileInDirectory(catalogPath);
        System.out.println("删除与否："+b);
    }

    /**
     * 获取客户端上传文件的文件名和后缀
     * postman测试：body - form-data ， key填写multipartFile，类型设置为File    value则选择本地xlsm文件
     * @param multipartFile 客户端上传的文件
     * @return
     */
    @PostMapping("/getFileNameFromClient")
    public String testGetFileNameFromClient(@RequestBody MultipartFile multipartFile){
        if(null == multipartFile){
            throw new IOException("没有上传文件");
        }
        return  IOUtils_LYR.getFileNameFromClient(multipartFile);
    }

}
