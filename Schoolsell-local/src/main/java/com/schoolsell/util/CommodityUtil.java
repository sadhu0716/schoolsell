package com.schoolsell.util;

import com.schoolsell.entity.Commodity;
import com.schoolsell.entity.other.PictureCount;

import java.util.List;

/**
 * 商品工具类
 */
public class CommodityUtil {

    /**
     * 获取首页页面的多张图片
     * 使用页面不同的方式来区分不同页面当前显示图片的位置
     * 而不是一概使用这个方法，每次清零，每次重新调用。这样容易出现在前端2秒空隙间（已到底部），又有request请求，而又可以查询图片
     * 从而前端图片重复的情况
     *
     * @param commodityList
     * @return
     */
    public static List<Commodity> getListPicture(List<Commodity> commodityList, String page) {
        int startPicture = 0;
        if (page.equals("首页")) {                          //根据当前页面的不同（首页，大类所有商品页面，小类所有商品页面）
            //调用不同的图片
            //获取当前页面（加载更多）开始第一张的图片位置
            startPicture = PictureCount.getGetPictureCount();
        } else if (page.equals("大类")) {
            startPicture = PictureCount.getBigkindCount();
        }
        List<Commodity> commodities;
        if (startPicture == 1) {
            startPicture = 0;
            PictureCount.setGetPictureCount(0);
        }
        System.out.println("当前图片位置:" + startPicture);


        //最后一张离现在的startPictrue的个数距离
        int interval = CommodityUtil.getListOfCommodityLength(commodityList) - startPicture;
        if (interval >= 8) {                                                             //不是末尾页,剩余数量大于8
            commodities = commodityList.subList(startPicture, startPicture + 8);        //截止到startPicture+8但不取startPicture+8的值
            if (page.equals("首页")) {
                PictureCount.setGetPictureCount(startPicture + 8);
            } else if (page.equals("大类")) {
                PictureCount.setBigkindCount(startPicture + 8);
            }
        } else {
                commodities = commodityList.subList(startPicture, startPicture + (interval));
            }
        return commodities;
    }

    /**
     * 获取数据库中返回的所有图片的数量长度
     * List<Commodity>的长度
     *
     * @return
     */
    public static int getListOfCommodityLength(List<Commodity> commodityList) {
        int i = 0;
        for (Commodity commodity : commodityList) {
            i++;
        }
        return i;
    }

}
