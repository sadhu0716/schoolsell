package com.schoolsell.service;

import com.schoolsell.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommodityService {

    /**
     * 添加商品
     * @param commodity
     * @return
     */
    String insert(Commodity commodity);

    /**
     * 下架商品
     * @param cid
     * @return
     */
    int deleteByPrimaryKey(Integer cid);

    /**
     * 根据商品ID查询商品
     * @param cid
     * @return
     */
    Commodity selectByPrimaryKey(Integer cid);

    /**
     * 根据登录用户ID和商品名称查询商品
     * @return
     */
    Commodity selectByThumbnail(String picturePath);

    /**
     * 查询所有商品
     * @return 商品列表集合
     */
    List<Commodity> selectAll();

    /**
     * 更新商品信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Commodity record);


//    /**
//     * 上传多张图片
//     * 更新数据库图片地址
//     * @param picture
//     * @param CName
//     * @param userID
//     * @return
//     */
//    public String updateMorePictureWithCName(String picture,String CName,String userID);

}
