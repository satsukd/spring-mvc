package com.study.shop.dao.jdbc.mapper;

import com.study.shop.entity.User;
import com.study.shop.security.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {
    public User mapRow(ResultSet resultSet) throws SQLException {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String user_role = resultSet.getString("user_role");

        User user = new User(username, password, UserRole.valueOf(user_role));
        return user;
    }
}
