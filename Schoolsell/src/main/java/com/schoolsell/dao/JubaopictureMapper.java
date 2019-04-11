package com.schoolsell.dao;

import com.schoolsell.entity.Jubaopicture;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JubaopictureMapper {
    int deleteByPrimaryKey(Integer jubaopictureid);

    int insert(Jubaopicture record);

    Jubaopicture selectByPrimaryKey(Integer jubaopictureid);

    List<Jubaopicture> selectAll();

    int updateByPrimaryKey(Jubaopicture record);
}