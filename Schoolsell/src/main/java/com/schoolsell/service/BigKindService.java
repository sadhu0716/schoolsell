package com.schoolsell.service;

import com.schoolsell.entity.Bigkind;
import com.schoolsell.exception.MySqlException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BigKindService {

    public int deleteByPrimaryKey(Integer bigKindID) throws MySqlException;

    public int insert(Bigkind record) throws MySqlException;

    public Bigkind selectByPrimaryKey(Integer bigKindID) throws MySqlException;

    public List<Bigkind> selectAll() throws MySqlException;

    public int updateByPrimaryKey(Bigkind record) throws MySqlException;

}
