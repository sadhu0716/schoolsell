package com.schoolsell.dao;

import com.schoolsell.entity.Smallkind;
import com.schoolsell.exception.MySqlException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
@Component
public interface SmallkindMapper {
    int deleteByPrimaryKey(Integer smallkindid) throws SQLException;

    int insert(Smallkind record) throws SQLException;

    Smallkind selectByPrimaryKey(Integer smallkindid) throws SQLException;

    List<Smallkind> selectAll() throws SQLException;

    int updateByPrimaryKey(Smallkind record) throws SQLException;

    List<Smallkind> selectBybigKindID(@Param("bigKindID") int bigKindID) throws SQLException;
}