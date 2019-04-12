package com.schoolsell.dao;

import com.schoolsell.entity.User;
import com.schoolsell.exception.MySqlException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
@Component
public interface UserMapper {
    int deleteByPrimaryKey(String userid) throws SQLException;

    int insert(User record) throws SQLException;

    User selectByPrimaryKey(String userid) throws SQLException;

    List<User> selectAll() throws SQLException;

    int updateByPrimaryKey(User record) throws SQLException;

    int updateuserName(User record) throws SQLException;

    int updatephone(User record) throws SQLException;

    int updateaddress(User record) throws SQLException;

}