package cn.djjboom.dynamic_datasource.service;

import cn.djjboom.dynamic_datasource.bean.User;

import java.util.List;

/**
 * @author djj
 * @Date 2019/7/3
 * @Time 22:54
 */
public interface UserService {
    void insert(User user);
    List<User> findAllBySlave();
    List<User> findAllByMaster();
}
