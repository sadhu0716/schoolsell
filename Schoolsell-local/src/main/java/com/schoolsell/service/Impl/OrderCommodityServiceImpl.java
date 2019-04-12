package com.schoolsell.service.Impl;

import com.schoolsell.dao.OrdercommodityMapper;
import com.schoolsell.dto.OrderCommodityDto;
import com.schoolsell.entity.Ordercommodity;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.OrderCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
@Service
public class OrderCommodityServiceImpl implements OrderCommodityService {
    @Autowired
    private OrdercommodityMapper ordercommodityMapper;

    @Transactional
    @Override
    public List<Ordercommodity> selectByOrderID(int orderID) {
        try{
            List<Ordercommodity> ordercommodityList=ordercommodityMapper.selectByorderID(orderID);
            return ordercommodityList;
        }catch (SQLException e){
            throw new MySqlException("根据订单ID查询所有属于这个订单的商品出现异常!!!!");
        }
    }

    @Transactional
    @Override
    public int insertByOne(Ordercommodity ordercommodity) {
        try{
           return ordercommodityMapper.insert(ordercommodity);
        }catch (SQLException e){
            throw new MySqlException("单个添加订单商品时出现异常!!!");
        }
    }

    @Transactional
    @Override
    public int insertByMany(int orderID, List<OrderCommodityDto> orderCommodityDtoList) {
        try{
            for(OrderCommodityDto orderCommodityDto:orderCommodityDtoList){         //同一个订单，循环插入多个商品部分信息（即DTO中的）
                ordercommodityMapper.insert(new Ordercommodity(orderID,orderCommodityDto.getcID(),orderCommodityDto.getAmount(),
                        orderCommodityDto.getcPrice(),orderCommodityDto.getcName(),orderCommodityDto.isBargain()));
            }
            return 0;
        }catch (SQLException e){
            throw new MySqlException("添加多个订单商品时出现异常！！！");
        }
    }
}
