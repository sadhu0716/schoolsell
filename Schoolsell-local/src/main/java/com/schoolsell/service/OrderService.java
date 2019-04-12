package com.schoolsell.service;

import com.schoolsell.entity.Order;

import java.util.List;

public interface OrderService {

    /*
    添加订单
     */
    String insert(Order order);

    /*
    通过订单ID来查询订单
     */
    Order selectByOrderID(int orderID);

    /*
    通过购买者ID来查询他的所有购买订单
     */
    List<Order> selectAllByBuyerID(String buyerID);

    /*
    通过卖家ID来查询他的所有售出订单
     */
    List<Order> selectAllBySellerID(String sellerID);

}
