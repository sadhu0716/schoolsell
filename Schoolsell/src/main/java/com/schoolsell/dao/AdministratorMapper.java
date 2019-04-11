package com.schoolsell.dao;

import com.schoolsell.entity.Administrator;
import java.util.List;

public interface AdministratorMapper {
    int deleteByPrimaryKey(String name);

    int insert(Administrator record);

    Administrator selectByPrimaryKey(String name);

    List<Administrator> selectAll();

    int updateByPrimaryKey(Administrator record);
}