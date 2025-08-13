package com.lsj.ssm.service;

import com.lsj.ssm.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserService {

    User getUser(Integer id);

    List<User> listUser();

    int addUser(User user);

    int updateUser(User user);

    int updateUserException(User user);

    int updateUserCatchException(User user);

    int deleteUser(Integer id);
}
