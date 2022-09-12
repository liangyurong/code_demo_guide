package com.demo.lyr.tool.easy_excel;


public enum RespCodeEnum {

    NOT_PRODURE_INSPECT_PROGROM("1", "异常"),
    BAR_CODE_RULE_NOT_DELETE("2", "达咩");

    private String msgCode;
    private String msgDes;

    RespCodeEnum(String msgCode, String msgDes) {
        this.msgCode = msgCode;
        this.msgDes = msgDes;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public String getMsgDes() {
        return msgDes;
    }
}
