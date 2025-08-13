package com.lsj.ssm.controller;

import com.lsj.ssm.common.Result;
import com.lsj.ssm.config.Address;
import com.lsj.ssm.dto.UserQueryDTO;
import com.lsj.ssm.entity.User;
import com.lsj.ssm.service.UserService;
import com.lsj.ssm.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

    private final UserService userService;

    private final Address address;

    public UserController(UserService userService, Address address) {
        this.userService = userService;
        this.address = address;
    }

    @GetMapping("/{id}")
    public UserInfoVO getUser(@PathVariable Integer id) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController#getUser【处理请求线程】: {}", threadName);
        User user = userService.getUser(id);
        UserInfoVO userInfoVO = UserInfoVO.builder().build()
                .setUser(user)
                .setAddress(address);
        log.warn(">>> 查询id: {} 对应的用户: {}", id, userInfoVO);
        return userInfoVO;
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public UserInfoVO getById(@RequestParam Integer id) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController#getUser【处理请求线程】: {}", threadName);
        User user = userService.getUser(id);
        UserInfoVO userInfoVO = UserInfoVO.builder().build()
                .setUser(user)
                .setAddress(address);
        log.warn(">>> 查询id: {} 对应的用户: {}", id, userInfoVO);
        return userInfoVO;
    }

    @PostMapping(value = "/getOne", consumes = "application/json"  , produces = {"application/json", "application/xml"} )
    public UserInfoVO getOne(@RequestBody @Validated UserQueryDTO userQueryDTO) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController#getUser【处理请求线程】: {}", threadName);
        User user = userService.getUser(userQueryDTO.getId());
        UserInfoVO userInfoVO = UserInfoVO.builder().build()
                .setUser(user)
                .setAddress(address);
        log.warn(">>> 查询id: {} 对应的用户: {}", userQueryDTO.getId(), userInfoVO);
        return userInfoVO;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        String threadName = Thread.currentThread().getName();
        log.info("UserController#getAllUsers【处理请求线程】: {}", threadName);
        return userService.listUser();
    }

    @PostMapping("/add")
    public Result<Void> addUser(@RequestBody User user) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController#addUser【处理请求线程】: {}", threadName);
        int rows = userService.addUser(user);
        return toResult(rows);
    }

    @PutMapping("/update")
    public Result<Void> updateUser(@RequestBody User user) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController#updateUser【处理请求线程】: {}", threadName);
        int rows = userService.updateUser(user);
        return toResult(rows);
    }

    @PutMapping("/updateUserException")
    public Result<Void> updateUserException(@RequestBody User user) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController#updateUserException【处理请求线程】: {}", threadName);
        int rows = userService.updateUserException(user);
        return toResult(rows);
    }

    @PutMapping("/updateUserCatchException")
    public Result<Void> updateUserCatchException(@RequestBody User user) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController#updateUserCatchException【处理请求线程】: {}", threadName);
        int rows = userService.updateUserCatchException(user);
        return toResult(rows);
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteUser(@PathVariable Integer id) {
        String threadName = Thread.currentThread().getName();
        log.info("UserController#deleteUser【处理请求线程】: {}", threadName);
        int rows = userService.deleteUser(id);
        return toResult(rows);
    }

}
