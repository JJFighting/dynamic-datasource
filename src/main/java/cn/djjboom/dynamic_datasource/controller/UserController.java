package cn.djjboom.dynamic_datasource.controller;

import cn.djjboom.dynamic_datasource.bean.User;
import cn.djjboom.dynamic_datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author djj
 * @Date 2019/7/3
 * @Time 22:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> list(){
        return userService.findAllBySlave();
    }

    @GetMapping("/master")
    public List<User> listMaster(){
        return userService.findAllByMaster();
    }

    @RequestMapping("add")
    public User add(User user){
        userService.insert(user);
        return user;
    }
}
