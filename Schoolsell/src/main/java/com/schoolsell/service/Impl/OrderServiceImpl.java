package com.schoolsell.service.Impl;

import com.schoolsell.dao.OrderMapper;
import com.schoolsell.entity.Order;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.OrderService;
import com.schoolsell.util.myEnum.SqlEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;



    @Transactional
    @Override
    public String insert(Order order) {
        try{
            orderMapper.insert(order);
            return SqlEnum.SQL_SUCCESS.getMessage();
        }catch(SQLException e){
            throw new MySqlException("订单添加异常!!");
        }

    }


    @Transactional
    @Override
    public Order selectByOrderID(int orderID) {
        try{
           Order order=orderMapper.selectByPrimaryKey(orderID);
            return order;
        }catch (SQLException e){
            throw new MySqlException("根据订单ID查询单个订单异常!!");
        }

    }

    @Transactional
    @Override
    public List<Order> selectAllByBuyerID(String buyerID) {
        try{
            List<Order> orderList=orderMapper.selectBybuyerID(buyerID);
            return orderList;
        }catch (SQLException e){
            throw new MySqlException("根据买家ID查询订单异常!!");
        }

    }

    @Transactional
    @Override
    public List<Order> selectAllBySellerID(String sellerID) {
        try{
            List<Order> orderList=orderMapper.selectBySellerID(sellerID);
            return orderList;
        }catch (SQLException e){
            throw new MySqlException("根据卖家ID查询订单异常!!!");
        }
    }
}
