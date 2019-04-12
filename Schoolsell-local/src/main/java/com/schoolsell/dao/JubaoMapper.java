package com.schoolsell.dao;

import com.schoolsell.entity.Jubao;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface JubaoMapper {
    int deleteByPrimaryKey(Integer jubaoid);

    int insert(Jubao record);

    Jubao selectByPrimaryKey(Integer jubaoid);

    List<Jubao> selectAll();

    int updateByPrimaryKey(Jubao record);
}