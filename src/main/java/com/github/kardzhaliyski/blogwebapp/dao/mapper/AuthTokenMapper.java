package com.github.kardzhaliyski.blogwebapp.dao.mapper;

import com.github.kardzhaliyski.blogwebapp.model.AuthToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthTokenMapper {
    @Select("SELECT * FROM tokens WHERE token = #{token}")
    public AuthToken getBy(String token);

    @Insert("INSERT INTO tokens(token, username, created_date, expiration_date) VALUES (#{token}, #{uname}, #{createdDate}, #{expirationDate})")
    public void addToken(AuthToken token);
}
