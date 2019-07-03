package cn.djjboom.dynamic_datasource.mapper;

import cn.djjboom.dynamic_datasource.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author djj
 * @Date 2019/7/3
 * @Time 22:51
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();

    @Insert("insert user (id,name) values (#{id},#{name})")
    void insert(User user);
}
