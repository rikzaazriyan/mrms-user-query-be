package com.rikza.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import com.rikza.model.User;

public interface UserDao {
    @SqlQuery("SELECT * FROM mrms_user")
    @RegisterFieldMapper(User.class)
    List<User> readAllUsers();
}
