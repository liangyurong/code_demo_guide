package com.demo.lyr.tool.hutool;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hutool")
public class WebUtils {

    @GetMapping("/get")
    public String forTest(@RequestParam("id")String id, @RequestParam("name")String name){
        return "id="+id+" name="+name;
    }

    /**
     * 使用HttpUtil调用上面的test接口
     */
    @GetMapping("/test/get")
    public String testget1(){
        String url = "http://localhost:8888/hutool/get";
        // 请求参数可以使用map封装，参数名需要对应
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","yurong333");
        return HttpUtil.get(url, map);
    }

    @PostMapping("/post")
    public String testPost(@RequestParam("id")String id,@RequestParam("name")String name){
        return "id="+id+" name="+name;
    }

    // 普通表单
    @GetMapping("/test/post")
    public String testpost(){

        String url = "http://localhost:8888/hutool/hutoolPost";
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","yurong333");

        String result = HttpRequest.post(url)
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .form(map)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        return result;
    }

    @PostMapping("hutoolPost1")
    public String testPost(@RequestBody JSONObject json){
        return "id="+json.getString("id")+" name="+ json.getString("name");
    }

    // 请求是json
    @GetMapping("testPost1")
    public String testpost1(){

        // 请求路径
        String url = "http://localhost:8888/hutool/hutoolPost1";

        // map封装请求参数
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","yurong123");

        // 将map转为json形式的字符串
        String json = JSONObject.toJSONString(map);

        String result = HttpRequest.post(url)
                // .header("","") // 添加请求头
                .body(json)
                .timeout(20000)
                .execute().body();
        return result;

    }

    // 获取客户端ip
    @GetMapping("/getClientIp")
    public String getClientIp(HttpServletRequest request){

        String clientIP = ServletUtil.getClientIP(request);
        return clientIP;
    }


}
