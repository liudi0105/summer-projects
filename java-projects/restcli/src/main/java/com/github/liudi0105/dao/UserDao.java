package com.github.liudi0105.dao;

import com.github.liudi0105.summer.core.ioc.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    private JdbcClient jdbcClient;

    public void listUserPaged() {
    }
}
