package com.hlogg.hloggbe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserRepo() {



    }
}
