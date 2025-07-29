package com.lsj.ssm.vo;

import com.lsj.ssm.config.Address;
import com.lsj.ssm.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class UserInfoVO {

    private User user;

    private Address address;

}
