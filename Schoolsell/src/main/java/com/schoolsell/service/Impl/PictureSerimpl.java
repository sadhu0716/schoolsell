package com.schoolsell.service.Impl;

import com.schoolsell.dao.CommodityMapper;
import com.schoolsell.dao.PictureMapper;
import com.schoolsell.entity.Commodity;
import com.schoolsell.entity.Picture;
import com.schoolsell.exception.MySqlException;
//import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PictureSerimpl implements PictureService {
    @Autowired
    PictureMapper pictureMapper;
    @Autowired
    CommodityMapper commodityMapper;

    /**
     * 根据商品主键cID获取商品详情图
     * @param cid
     * @return List<Picture>
     * @throws IOException
     */
    @Transactional
    @Override
    public List<String> selectPictureByCid(Integer cid) throws IOException {
        List<String> pictures = new ArrayList<String>();
        try {
            List<Picture> pictureList = pictureMapper.selectByCid(cid);
            System.out.println("得到的图片路径" + pictureList);
            for (Picture p : pictureList) {
                System.out.println("单个路径" + p.getPicture());
                String pic = p.getPicture();
                int startLength = pic.lastIndexOf("/") + 1;         //剪切起始位置
                int lastLength = pic.length();                        //剪切最后位置
                System.out.println("路径剪切起始位置：" + startLength);
                pic = pic.substring(startLength, lastLength);
                System.out.println("路径最后位置:" + lastLength);
                System.out.println("图片为:" + pic);
                pictures.add(pic);
            }
        } catch (SQLException e) {
            throw new MySqlException("查询商品图片异常!");
        }
        return pictures;
    }

    /**
     * 获取头条商品（三条）
     * 商品状态:  -1:未审核   0:已审核（未通过）  1:已审核(已通过)  2:已审核（设为头条）
     * @return 三条头条商品的cID和路径组成的集合
     * @throws SQLException
     */
    @Transactional
    public Map<String, Object> select() {
        List<Object> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        try {
            List<Commodity> commodityList = commodityMapper.selectAll();        //获取所有商品
            for (Commodity commodity : commodityList) {                         //循环遍历每一条商品
                if (commodity.getIschecked() == 2) {                                        //获取状态为2的商品
                    List<Picture> pictureList = pictureMapper.selectByCid((commodity.getCid()));  //获取这件商品的商品详情图
                    String p = pictureList.get(0).getPicture();                 //首张详情图片的路径
                    Integer cID = pictureList.get(0).getCid();                  //图片所属的cID
                    String pathname = p.substring(p.lastIndexOf("/") + 1, p.length());        //切割图片路径，获取图片名称
                    System.out.println(pathname);

                    //将这件商品的路径和ID添加进List集合中
                    list.add(pathname);             //下标为0,2,4的是商品名称
                    list.add(cID);                  //下标为1,3,5的是商品ID
                }
            }
        } catch (SQLException e) {
            throw new MySqlException("查询头条商品异常");
        }
        System.out.println("头条轮播图:" + list);
        map.put("result", list);
        return map;
    }

}
