package com.schoolsell.dao;

import com.schoolsell.entity.Bigkind;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BigkindMapper {
    int deleteByPrimaryKey(Integer bigkindid);

    int insert(Bigkind record);

    Bigkind selectByPrimaryKey(Integer bigkindid);

    List<Bigkind> selectAll();

    int updateByPrimaryKey(Bigkind record);
}