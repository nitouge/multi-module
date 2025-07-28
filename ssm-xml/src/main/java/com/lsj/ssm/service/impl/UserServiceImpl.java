package com.lsj.ssm.service.impl;

import com.lsj.ssm.entity.User;
import com.lsj.ssm.mapper.UserMapper;
import com.lsj.ssm.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Log
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        log.info(">>> UserServiceImpl.getUser id: " + id);
        return userMapper.selectOne(id);
    }

    @Override
    public List<User> listUser() {
        log.info(">>> UserServiceImpl.listUser");
        return userMapper.findAllUser();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int addUser(User user) {
        log.info(">>> UserServiceImpl.addUser id: " + user.getId() + ", username: " + user.getUsername());
        int rows = userMapper.insertUser(user);
        int i = 1/0;
        return rows;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateUser(User user) {
        log.info(">>> UserServiceImpl.updateUser id: " + user.getId() + ", username: " + user.getUsername());
        int rows = userMapper.updateUser(user);
        int i = 1/0;
        return rows;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int deleteUser(Integer id) {
        log.info(">>> UserServiceImpl.deleteUser id: " + id);
        return userMapper.deleteUserById(id);
    }
}
