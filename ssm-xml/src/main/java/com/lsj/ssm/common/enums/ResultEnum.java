package com.lsj.ssm.common.enums;

import com.lsj.ssm.common.IResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResultEnum implements IResult {

    SUCCESS(200, "操作成功"),

    VALIDATE_FAILED(400, "参数错误"),

    COMMON_FAILED(500, "系统错误"),

    FORBIDDEN(2004, "没有权限访问资源");

    private final Integer code;

    private final String message;

}


