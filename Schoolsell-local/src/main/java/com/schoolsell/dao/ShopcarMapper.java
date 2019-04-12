package com.schoolsell.dao;

import com.schoolsell.entity.Shopcar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public interface ShopcarMapper {
    int deleteByPrimaryKey(Integer shopcarid)throws SQLException;

    int insert(Shopcar record)throws SQLException;

    Shopcar selectByPrimaryKey(Integer shopcarid)throws SQLException;

    List<Shopcar> selectAll()throws SQLException;

    int updateByPrimaryKey(Shopcar record)throws SQLException;

    //根据用户userid来查询LIstanbul<Shopcar>,和状态isChoose，isFinish
    List<Shopcar> selectByuserid(@Param("userid") String userid, @Param("isfinish") int isfinish)throws SQLException;
}