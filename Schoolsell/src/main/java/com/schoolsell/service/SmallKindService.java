package com.schoolsell.service;

import com.schoolsell.entity.Smallkind;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface SmallKindService {
    public List<Smallkind> selectAll() throws SQLException;

    public int updateByPrimaryKey(Smallkind record) throws SQLException;

    public List<Smallkind> kindquery(Integer bigKindID) throws SQLException;

    public Smallkind selectByPrimaryKey(Integer smallKindID) throws SQLException;

    public int deleteByPrimaryKey(Integer smallKindID) throws SQLException;

    public int insert(Smallkind record) throws SQLException;
}
