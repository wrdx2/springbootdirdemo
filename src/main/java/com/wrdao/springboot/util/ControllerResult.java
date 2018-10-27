package com.wrdao.springboot.util;

import com.wrdao.springboot.util.mybatis.pagination.PageDto;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageList;

import java.io.Serializable;

public class ControllerResult implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public static ControllerResult success(String msg, Object object) {
        ControllerResult result = new ControllerResult();
        result.setCode("success");
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static ControllerResult success(Object object) {
        return success("成功", object);
    }

    public static ControllerResult success() {
        return success(null);
    }

    public static ControllerResult error(String msg, Object object) {
        ControllerResult result = new ControllerResult();
        result.setCode("error");
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static ControllerResult PageListOf(String msg, PageList listByPageQo) {
        return ControllerResult.success(msg,PageDto.ValueOf(listByPageQo));
    }

    public static ControllerResult error(Object object) {
        return error("错误", object);
    }

    public static ControllerResult error() {
        return error(null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
