package com.demo.lyr.tool.excel_easyexcel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 自定义模块间接口调用失败时主动抛出的异常信息
 * @author: lyr
 * @time: 2020/6/4
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String code;

    private RespCodeEnum respCodeEnum;

    private ResponseData responseData;
    /**
     * 无参构造方法
     */
    public ResponseException() {
        super();
    }

    /**
     * 有参构造方法
     *
     * @param message
     */
    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(ResponseData responseData) {
        this.responseData = responseData;
    }
    /**
     * 有参构造方法
     *
     * @param respCodeEnum
     */
    public ResponseException(RespCodeEnum respCodeEnum) {
        super(respCodeEnum.getMsgDes());
        this.code = respCodeEnum.getMsgCode();
        this.respCodeEnum = respCodeEnum;
    }

    public RespCodeEnum getRespCodeEnum() {
        return respCodeEnum;
    }
}

