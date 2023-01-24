package com.github.kardzhaliyski.blogwebapp.dao;

import com.github.kardzhaliyski.blogwebapp.dao.mapper.UserMapper;
import com.github.kardzhaliyski.blogwebapp.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;

public class UsersDao {
    private SqlSessionFactory sessionFactory;

    public UsersDao() {
        try {
            sessionFactory = SessionFactoryInstance.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByUsername(String username) {
        try (SqlSession session = sessionFactory.openSession(true)){
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getByUsername(username);
        }
    }

    public void addUser(User user) {
        try (SqlSession session = sessionFactory.openSession(true)){
            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.insert(user);
        }
    }
}
