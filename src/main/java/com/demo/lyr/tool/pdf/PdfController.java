package com.demo.lyr.tool.pdf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PdfController {

    @GetMapping("/getPdf")
    public void getPdf(HttpServletResponse response) throws Exception{
        PdfUtils.saveToPdfStream(response);
    }

}
