package com.lsj.ssm.service;

import com.lsj.ssm.entity.User;

import java.util.List;


public interface UserService {

    User getUser(Integer id);

    List<User> listUser();

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);
}
