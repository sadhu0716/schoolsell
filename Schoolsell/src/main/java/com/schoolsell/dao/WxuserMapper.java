package com.schoolsell.dao;

import com.schoolsell.entity.Wxuser;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public interface WxuserMapper {

    int add(Wxuser wxuser) throws SQLException;

    int delete(Integer wxID)throws SQLException;

    Wxuser selectByPrimaryKey(Integer wxID)throws SQLException;

    Wxuser selectByUserID(String userID)throws SQLException;

    Wxuser selectByOpenID(String openID) throws SQLException;

    int updateByPrimaryKey(Integer wxID)throws SQLException;

    int updateByUserID(String userID)throws SQLException;


}
