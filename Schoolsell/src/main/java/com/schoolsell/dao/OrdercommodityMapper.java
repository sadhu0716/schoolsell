package com.schoolsell.dao;

import com.schoolsell.entity.Commodity;
import com.schoolsell.entity.Ordercommodity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
@Component
public interface OrdercommodityMapper {
    int deleteByPrimaryKey(Integer ocid) throws SQLException;

    int insert(Ordercommodity record) throws SQLException;

    Ordercommodity selectByPrimaryKey(Integer ocid) throws SQLException;

    List<Ordercommodity> selectAll() throws SQLException;

    int updateByPrimaryKey(Ordercommodity record) throws SQLException;

    List<Ordercommodity> selectByorderID(@Param("orderID") int orderID) throws SQLException;

}