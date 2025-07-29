package com.lsj.ssm.service.impl;

import com.lsj.ssm.entity.User;
import com.lsj.ssm.mapper.UserMapper;
import com.lsj.ssm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService, BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    public UserServiceImpl() {
        log.info("===============> UserServiceImpl构造方法执行");
    }

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        log.info(">>> UserServiceImpl#getUser id: {}", id);
        return userMapper.selectOne(id);
    }

    @Override
    public List<User> listUser() {
        log.info(">>> UserServiceImpl#listUser");
        return userMapper.findAllUser();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int addUser(User user) {
        log.info(">>> UserServiceImpl#addUser id: {}, username: {}", user.getId(), user.getUsername());
        return userMapper.insertUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateUser(User user) {
        log.info(">>> UserServiceImpl#updateUser id: {}, username: {}", user.getId(), user.getUsername());
        return userMapper.updateUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateUserException(User user) {
        log.info(">>> UserServiceImpl#updateUserException id: {}, username: {}", user.getId(), user.getUsername());
        int rows = userMapper.updateUser(user);
        int i = 1 / 0;
        return rows;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateUserCatchException(User user) {
        log.info(">>> UserServiceImpl#updateUserCatchException id: {}, username: {}", user.getId(), user.getUsername());
        int rows = userMapper.updateUser(user);
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            log.error("更新用户：{} 信息失败", user.getId(), e);
        }
        return rows;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int deleteUser(Integer id) {
        log.info(">>> UserServiceImpl#deleteUser id: {}", id);
        return userMapper.deleteUserById(id);
    }

    @PostConstruct
    public void init() {
        log.info("===============> @PostConstruct#init被调用");
    }

    @PreDestroy
    public void cleanup() {
        log.info("===============> @PreDestroy#cleanup被调用");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("===============> BeanFactoryAware#setBeanFactory方法被调用");
    }

    @Override
    public void setBeanName(String name) {
        log.info("===============> BeanNameAware#setBeanName方法被调用，Bean name:{}", name);
    }

    @Override
    public void destroy() throws Exception {
        log.info("===============> DisposableBean#destroy方法被调用");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("===============> InitializingBean#afterPropertiesSet方法被调用");
    }
}
