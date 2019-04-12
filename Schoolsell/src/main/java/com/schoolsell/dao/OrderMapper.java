package com.schoolsell.dao;

import com.schoolsell.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
@Component
public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderid) throws SQLException;

    int insert(Order record) throws SQLException;

    Order selectByPrimaryKey(Integer orderid) throws SQLException;

    List<Order> selectAll() throws SQLException;

    int updateByPrimaryKey(Order record) throws SQLException;

    List<Order> selectBybuyerID(@Param("buyerID") String buyerID) throws SQLException;

    List<Order> selectBySellerID(@Param("sellerID") String sellerID) throws SQLException;

}