package com.atguigu.common.result;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(2012, "服务异常"),
    DATA_ERROR(204, "数据异常"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限");
    @Setter
    @Getter
    private Integer code;
    @Setter
    @Getter
    private String message;
    private ResultCodeEnum(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
