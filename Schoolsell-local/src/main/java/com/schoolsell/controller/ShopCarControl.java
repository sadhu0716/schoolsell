package com.schoolsell.controller;

import com.schoolsell.entity.Commodity;
import com.schoolsell.entity.Shopcar;
import com.schoolsell.service.Impl.ShopCarServiceImpl;
import com.schoolsell.service.Impl.CommodityServiceImpl;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/schoolsell")
public class ShopCarControl {
    @Autowired
    ShopCarServiceImpl shopCarService;
    @Autowired
    CommodityServiceImpl commodityService;

    //传入用户id，返回该用户购物车信息
    @RequestMapping("/getShopCar")
    @ResponseBody
    public Object getShopCar(String userID) {
        List<Shopcar> list = shopCarService.selectByuseridAndStatus(userID, 0);
        System.out.println("用户的所有购物车未结算商品:" + list);
        List<Map> mapList = new ArrayList<>();
        for (Shopcar shopcar : list) {
            Map<String, Object> map = new HashMap<>();
            System.out.println("单个购物车商品的cid:" + shopcar.getCid());
            Commodity commodity = commodityService.selectByPrimaryKey(shopcar.getCid());
            System.out.println("购物车:" + commodity);
            System.out.println("开始图片:" + commodity.getThumbnail());
            int startLength = commodity.getThumbnail().lastIndexOf("/") + 1;
            String pictureName = commodity.getThumbnail().substring(startLength, commodity.getThumbnail().length());       //获取缩略图名称
            map.put("title", commodity.getCname());
            map.put("image", pictureName);
            map.put("num", shopcar.getAmount());
            map.put("price", commodity.getCprice());
            map.put("cid", shopcar.getCid());
            mapList.add(map);
        }
        JSON json = JSONArray.fromObject(mapList);
        return json;
    }

    //增添购物车信息
    @ResponseBody
    @RequestMapping("/addShopCar")
    public String addShopCar(String userid, Integer cid, Integer amount) {
//        Boolean ischoose=false;
//        Boolean isfinish=false;
        Shopcar shopcar = new Shopcar(userid, cid, amount, 0);
        shopCarService.insertShopcar(shopcar);
        return "操作成功";
    }
}
