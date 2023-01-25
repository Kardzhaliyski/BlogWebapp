package com.github.kardzhaliyski.blogwebapp.mappers;

import com.github.kardzhaliyski.blogwebapp.models.AuthToken;
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
