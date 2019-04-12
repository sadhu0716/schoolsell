package com.schoolsell.service;

import com.schoolsell.dto.OrderCommodityDto;
import com.schoolsell.entity.Ordercommodity;

import java.util.List;

public interface OrderCommodityService {

    /*
    根据订单ID查询这个订单的所有商品
    */
    public List<Ordercommodity> selectByOrderID(int orderID);

    /*
    从商品详情处进行下订单
     */
    public int insertByOne(Ordercommodity ordercommodity);

    /*
    从购物车处进行下订单的话，一个订单会有多个商品
     */
    public int insertByMany(int orderID, List<OrderCommodityDto> orderCommodityDtoList);

}
