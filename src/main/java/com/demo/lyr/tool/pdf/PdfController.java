package com.demo.lyr.tool.pdf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PdfController {

    @GetMapping("/getPdf")
    public void getPdf(HttpServletResponse response) throws Exception{
        PdfUtils1.saveToPdfStream(response);
    }

    @GetMapping("/getHtml")
    public byte[] getHtml(){
        // 获取html的流
        byte[] bytes = PdfUtils2.excel2Html();
        // 返回给客户端
        return bytes;
    }

}
