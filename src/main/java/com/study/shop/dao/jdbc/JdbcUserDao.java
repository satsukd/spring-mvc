package com.study.shop.dao.jdbc;

import com.study.shop.dao.UserDao;
import com.study.shop.dao.jdbc.mapper.UserRowMapper;
import com.study.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUserDao implements UserDao {
    private static final String SQL_GET_USER_BY_NAME_AND_PASSWORD = "SELECT username, password, user_role FROM app_user WHERE username = UPPER(?) AND password = UPPER(?)";
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User getUser(String userName, String password) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_NAME_AND_PASSWORD, new Object[]{userName, password}, USER_ROW_MAPPER);
    }
}
