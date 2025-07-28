package com.lsj.ssm.controller;

import com.lsj.ssm.common.Result;
import com.lsj.ssm.entity.User;
import com.lsj.ssm.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("user")
@Log
public class UserController extends BaseController {

    // @Autowired
    // private UserService userService;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        String threadName = Thread.currentThread().getName();
        System.out.println("【处理请求线程】" + threadName);
        log.info("UserController getUser 【处理请求线程】: " + threadName);
        return userService.getUser(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        String threadName = Thread.currentThread().getName();
        log.info("UserController getAllUsers 【处理请求线程】: " + threadName);
        return userService.listUser();
    }

    @PostMapping("/add")
    public Result<Void> addUser(@RequestBody User user) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController addUser 【处理请求线程】: " + threadName);
        int rows = userService.addUser(user);
        return toResult(rows);
    }

    @PutMapping("/update")
    public Result<Void> updateUser(@RequestBody User user) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController updateUser 【处理请求线程】: " + threadName);
        int rows = userService.updateUser(user);
        return toResult(rows);
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteUser(@PathVariable Integer id) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController deleteUser 【处理请求线程】: " + threadName);
        int rows = userService.deleteUser(id);
        return toResult(rows);
    }


}
