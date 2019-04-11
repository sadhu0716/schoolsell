package com.schoolsell.controller;

import com.schoolsell.entity.Commodity;
import com.schoolsell.service.Impl.CommodityServiceImpl;
import com.schoolsell.util.CommodityUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/schoolsell")
public class SearchController {

    @Autowired
    CommodityServiceImpl commodityService;

    /**
     * 模糊查询商品
     * @param searchDetail
     * @return
     */
    @RequestMapping(value = "/searchDetail")
    @ResponseBody
    public JSONArray searchCommodityByName(String searchDetail){
        List<Commodity> commodityList=commodityService.selectByCName(searchDetail);
        List<Commodity> commodities=CommodityUtil.getListPicture(commodityList,"大类");
        JSONArray jsonObject=JSONArray.fromObject(commodities);

        System.out.println("controller层模糊查询的List个数为:"+commodityList.size()+"\n"+"具体为:");
        for(Commodity commodity:commodityList){
            System.out.println(commodity.getCname());
        }
        System.out.println("传送至前台的json数据为:"+jsonObject.toString());

        return jsonObject;
    }


}
