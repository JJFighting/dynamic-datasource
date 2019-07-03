package cn.djjboom.dynamic_datasource.service.impl;

import cn.djjboom.dynamic_datasource.annotation.RoutingDataSource;
import cn.djjboom.dynamic_datasource.bean.User;
import cn.djjboom.dynamic_datasource.datasource.DataSourceName;
import cn.djjboom.dynamic_datasource.mapper.UserMapper;
import cn.djjboom.dynamic_datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author djj
 * @Date 2019/7/3
 * @Time 22:55
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RoutingDataSource(value = DataSourceName.MASTER)
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    @RoutingDataSource(DataSourceName.MASTER)
    public List<User> findAllByMaster() {
        return userMapper.findAll();
    }

    @Override
    @RoutingDataSource(DataSourceName.SLAVE)
    public List<User> findAllBySlave() {
        return userMapper.findAll();
    }
}
