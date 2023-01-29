package com.github.kardzhaliyski.blogwebapp.mappers;

import com.github.kardzhaliyski.blogwebapp.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE username = #{username}")
    Optional<User> getByUsername(String username);

    @Insert("INSERT INTO users(username, first_name, last_name, email, password) VALUES (#{username}, #{firstName}, #{lastName}, #{email}, #{password})")
    void insert(User user);
}
