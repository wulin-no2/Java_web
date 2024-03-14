package com.lina.mapper;

import com.lina.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    // check all the userInfo
    @Select("SELECT * from user")
    public List<User> list();
}
