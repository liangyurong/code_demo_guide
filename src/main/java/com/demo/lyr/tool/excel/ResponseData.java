package com.demo.lyr.tool.excel;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 前后通讯数据格式
 *
 * @author lyr
 */
@Data
@NoArgsConstructor
public class ResponseData implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SUCCESS_CODE = "200";
    public static final String IOT_SUCCESS_CODE = "0";
    public static final String FAIL_CODE = "300";

    /**
     * 状态码
     * 200：操作成 300：操作失败 400：未登录
     */
    private String code;
    /**
     * 消息提示
     * 默认操作成功
     */
    private String message;
    /**
     * 当前服务器时间
     * 格式yyyy-MM-dd HH:mm:ss
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time = new Date();
    /**
     * 业务数据
     */
    private Object data;


    /**
     * 构造操作成功数据包   *
     *
     * @param data    业务数据
     * @param message 提示消息
     * @return
     */
    public static ResponseData success(Object data, String message) {
        ResponseData responseData = new ResponseData();
        responseData.setData(data);
        responseData.setCode(SUCCESS_CODE);
        responseData.setMessage(message);
        return responseData;
    }

    /**
     * 构造操作成功数据包
     *
     * @param data
     * @return
     */
    public static ResponseData success(Object data) {
        ResponseData responseData = new ResponseData();
        responseData.setData(data);
        responseData.setCode(SUCCESS_CODE);
        responseData.setMessage("操作成功");
        return responseData;
    }

    /**
     * 构造操作成功数据包
     *
     * @param messsage
     * @return
     */
    public static ResponseData success(String messsage) {
        ResponseData responseData = new ResponseData();
        responseData.setData(null);
        responseData.setCode(SUCCESS_CODE);
        responseData.setMessage(messsage);
        return responseData;
    }

    /**
     * 构造操作成功数据包
     *
     * @return
     */
    public static ResponseData success() {
        ResponseData responseData = new ResponseData();
        responseData.setData(null);
        responseData.setCode(SUCCESS_CODE);
        responseData.setMessage("操作成功");
        return responseData;
    }


    /**
     * 构造操作失败数据包
     *
     * @param fieldErrors
     * @return
     */
    public static ResponseData fail(List<FieldError> fieldErrors) {
        FieldError fieldError = fieldErrors.get(0);
        String errorMsg = Optional.ofNullable(fieldError).map(u -> fieldError.getDefaultMessage()).orElse("数据格式不合法");
        return fail(errorMsg);
    }


    /**
     * 构造操作失败数据包
     *
     * @return
     */
    public static ResponseData fail(String message) {
        ResponseData responseData = new ResponseData();
        responseData.setData(null);
        responseData.setCode(FAIL_CODE);
        responseData.setMessage(message);
        return responseData;
    }

    /**
     * 构造操作失败数据包
     *
     * @return
     */
    public static ResponseData fail() {
        ResponseData responseData = new ResponseData();
        responseData.setData(null);
        responseData.setCode(FAIL_CODE);
        responseData.setMessage("操作失败");
        return responseData;
    }


    /**
     * 构造失败数据包
     *
     * @param data
     * @return
     */
    public static ResponseData fail(Object data) {
        ResponseData responseData = new ResponseData();
        responseData.setData(data);
        responseData.setCode(FAIL_CODE);
        responseData.setMessage("操作失败");
        return responseData;
    }


    public static ResponseData fail(RespCodeEnum respCode) {
        ResponseData responseData = new ResponseData();
        responseData.setData(null);
        responseData.setCode(respCode.getMsgCode());
        responseData.setMessage(respCode.getMsgDes());
        return responseData;
    }

    public static ResponseData fail(RespCodeEnum respCode,Object data) {
        ResponseData responseData = new ResponseData();
        responseData.setData(data);
        responseData.setCode(respCode.getMsgCode());
        responseData.setMessage(respCode.getMsgDes());
        return responseData;
    }

}
