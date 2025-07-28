package com.lsj.ssm.controller;

import com.lsj.ssm.common.Result;

public class BaseController {

    protected <T> Result<T> toResult(int rowsAffected, T data) {
        return rowsAffected > 0 ? Result.success(data) : Result.fail("操作失败");
    }

    protected Result<Void> toResult(int rowsAffected) {
        return rowsAffected > 0 ? Result.success(null) : Result.fail("操作失败");
    }
}
