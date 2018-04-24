package com.open.coinnews.basic.json;

public class JsonNormalDto {

    private Integer resCode;

    private String resMsg;

    public Integer getResCode() {
        return resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public JsonNormalDto(Integer resCode, String resMsg) {
        this.resCode = resCode;
        this.resMsg = resMsg;
    }
}
