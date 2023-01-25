package com.github.kardzhaliyski.blogwebapp.mappers;

import com.github.kardzhaliyski.blogwebapp.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getByUsername(String username);

    @Insert("INSERT INTO users(username, first_name, last_name, email, password, salt) VALUES (#{username}, #{firstName}, #{lastName}, #{email}, #{password}, #{salt})")
    void insert(User user);
}
