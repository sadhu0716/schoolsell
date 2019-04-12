package com.schoolsell.dao;

import com.schoolsell.entity.Picture;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
@Component
public interface PictureMapper {
    int deleteByPrimaryKey(Integer pictureid)throws SQLException;

    int insert(Picture record)throws SQLException;

    Picture selectByPrimaryKey(Integer pictureid)throws SQLException;

    List<Picture> selectAll()throws SQLException;

    int updateByPrimaryKey(Picture record)throws SQLException;

    List<Picture> selectByCid(Integer cid)throws SQLException;
}