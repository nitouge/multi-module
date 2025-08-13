package com.lsj.ssm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserQueryDTO implements Serializable {

    @NotNull(message = "用户id不能为空")
    private Integer id;
}
