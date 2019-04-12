package com.schoolsell.controller;

import com.schoolsell.entity.Order;
import com.schoolsell.entity.Ordercommodity;
import com.schoolsell.service.Impl.OrderCommodityServiceImpl;
import com.schoolsell.service.Impl.OrderServiceImpl;
import com.schoolsell.service.Impl.UserLoginServiceImpl;
import com.schoolsell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/schoolsell")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private OrderCommodityServiceImpl orderCommodityService;

    @Autowired
    private UserLoginServiceImpl userLoginService;

    @RequestMapping("/getOrderByBuyerID")
    @ResponseBody
    public Map<String,Object> getOrderByBuyerID(String buyerID){
        Map<String,Object> order_map=new HashMap<>();
        List<Order> orderList=orderServiceImpl.selectAllByBuyerID(buyerID);
        order_map.put("orderresult",orderList);
        List<List<Ordercommodity>> orderlist=new ArrayList<>();
        for(Order order:orderList){
         List<Ordercommodity> ordercommodityList=orderCommodityService.selectByOrderID(order.getOrderid());
            orderlist.add(ordercommodityList);
        }
        order_map.put("oredercommodity",orderlist);
        return order_map;
    }

    /*
    从商品详情处直接购买
     */
    @RequestMapping("/orderSuccess")
    @ResponseBody
    public Map<String,Object> insertOrder(String orderDate, String buyerID, String sellerID, String address, String buyerPhone,
                                          String buyerName, String cid, String amount, String cName, String cPrice){
        Map<String,Object> map=new HashMap<>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Timestamp orderdate=Timestamp.valueOf(orderDate);
        Date orderdate=new Date();
        BigDecimal cprice=new BigDecimal(cPrice);
        try{
            orderdate=simpleDateFormat.parse(orderDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        Timestamp order_date=new Timestamp(orderdate.getTime());
        int cID=Integer.parseInt(cid);
        int Amount=Integer.parseInt(amount);
        int state=-1;
        boolean isOnline=false;
        String sellerPhone=userLoginService.selectByPrimaryKey(sellerID).getPhonenumber();
        String sellerName=userLoginService.selectByPrimaryKey(sellerID).getUsername();
        Order order=new Order(order_date,state,isOnline,buyerID,sellerID,address,buyerPhone,sellerPhone,buyerName,sellerName);
        orderServiceImpl.insert(order);
        int orderID=order.getOrderid();
        Ordercommodity ordercommodity=new Ordercommodity(orderID,cID,Amount,cprice,cName,false);
        orderCommodityService.insertByOne(ordercommodity);
        map.put("orderID",orderID);
        return map;
    }


}
