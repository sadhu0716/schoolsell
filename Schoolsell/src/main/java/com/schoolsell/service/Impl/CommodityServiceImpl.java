package com.schoolsell.service.Impl;

import com.schoolsell.dao.OrderMapper;
import com.schoolsell.dao.OrdercommodityMapper;
import com.schoolsell.entity.Order;
import com.schoolsell.entity.Ordercommodity;
import com.schoolsell.util.myEnum.SqlEnum;
import com.schoolsell.dao.CommodityMapper;
import com.schoolsell.dao.PictureMapper;
import com.schoolsell.entity.Commodity;
import com.schoolsell.entity.Picture;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    private Commodity commodity;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrdercommodityMapper ordercommodityMapper;

    /**
     * 上架商品（一）
     * 添加商品具体信息和缩略图
     * @param commodity
     * @return
     */
    @Transactional
    @Override
    public String insert(Commodity commodity) {
//        Commodity commodity1=commodityMapper.selectByUserIDWithCName(commodity.getThumbnail());
//        if(commodity1.getCid()==null) {                  //是否已存在这件商品，null为不存在进入if
            try {
                commodityMapper.insert(commodity);
//                int cID=commodity.getCid();                 //获取刚插入的商品的cid
//                String str_cid=String.valueOf(cID);
//                Commodity commodity2=new Commodity();           //此commodity用户更新缩略图名称
//                commodity2.setCid(cID);
//                commodity2.setThumbnail(str_cid);
//                commodityMapper.updateThumbnailByCommodity(commodity2);
                return SqlEnum.SQL_SUCCESS.getMessage();
            } catch (SQLException e) {
                throw new MySqlException("商品添加异常！");
            }
//        }else {
//            return SqlEnum.COMMODITY_EXIST.getMessage();
//        }
    }

    /**
     * 更新商品的缩略图的名称
     * @param cID
     * @param thumbnail
     * @return
     */
    @Transactional
    public int updateThumbnailByCommodity(int cID,String thumbnail){
        Commodity commodity=new Commodity();
        commodity.setCid(cID);
        commodity.setThumbnail(thumbnail);
        try{                                                                    //此处异常，无法进入try
            commodityMapper.updateThumbnailByCommodity(commodity);
            System.out.println("缩略图更新中。。。");
            return 1;
        }catch (SQLException e){
            throw new MySqlException("商品缩略图更新异常!");
        }
    }

    /**
     * 下架一件商品
     * @param cid
     * @return
     */
    @Transactional
    @Override
    public int deleteByPrimaryKey(Integer cid) {
        try{
            return commodityMapper.deleteByPrimaryKey(cid);
        }catch (SQLException e){
            throw new MySqlException("根据cid下架商品异常!");
        }
    }

    /**
     * 根据主键cID查询某一件商品的具体信息
     * @param cid
     * @return
     */
    @Transactional
    @Override
    public Commodity selectByPrimaryKey(Integer cid) {
        Commodity commodity= null;
        try {
            commodity = commodityMapper.selectByPrimaryKey(cid);

        } catch (SQLException e) {
            throw  new MySqlException("查询商品信息异常");
        }

        return commodity;
    }

    /**
     * 根据缩略图名称查找具体商品
     * 说明：
     *  1.开始时并不知道可以直接在插入时返回自增主键值
     *  2.所以通过唯一的缩略图对应唯一的主键的方式来获取一条具体的商品信息，此时的缩略图相当于主键
     *  3.后来发现可以通过更改Mapper.xml文件来获取自增主键值
     *  4.故而后来这个方法就弃用了
     * @param picturePath
     * @return 查找到的商品
     */
    @Transactional
    @Override
    public Commodity selectByThumbnail(String picturePath) {
        try{
            return commodityMapper.selectByUserIDWithCName(picturePath);
        }catch (SQLException e){
            throw new MySqlException("根据picturePath查询商品异常!");
        }
    }

    /**
     * 查询所有属于小类的商品
     * @param KName
     * @return
     */
    @Transactional
    public List<Commodity> selectByKName(String KName){
        try{
            return commodityMapper.selectByKName(KName);
        }catch (SQLException e){
            throw new MySqlException("根据小类名称查询所有属于小类商品时异常！");
        }
    }

    /**
     * 模糊查询
     * @return
     */
    @Transactional
    public List<Commodity> selectByCName(String searchDetail){
        String searchText=new StringBuilder("%").append(searchDetail).append("%").toString();
        try{
            List<Commodity> commodities=commodityMapper.selectByCName(searchText);
            System.out.println("在service层获取的模糊查询的List个数为:"+commodities.size()+"\n"+"具体为:");
            for(Commodity commodity:commodities){
                System.out.println(commodity.getCname());
            }
            return commodities;
        }catch (SQLException e){
            throw new MySqlException("模糊查询异常!");
        }
    }

    /**
     * 查询所有商品信息
     * @return List<Commodity>
     */
    @Transactional
    @Override
    public List<Commodity> selectAll() {
        try{
            return commodityMapper.selectAll();
        }catch (SQLException e){
            throw new MySqlException("查询所有commodity异常!");
        }
    }

    /**
     * 更新商品信息（商品数量，商品价格）
     * @param cID 商品表主键（自增）
     * @param cCount 商品数量
     * @param cPrice 商品价格
     * @return String（SqlEnum类的某一属性）
     */
    @Transactional
    public String updateMsg(Integer cID,Integer cCount,BigDecimal cPrice)  {
        try {
            if(cID!=null&&cCount!=null&&cPrice!=null){
                commodity.setCid(cID);
                commodity.setCcount(cCount);
                commodity.setCprice(cPrice);
                commodityMapper.updatemsg(commodity);
                return SqlEnum.SQL_SUCCESS.getMessage();
            }else{
                return SqlEnum.SQL_UPDATE_WRONG.getMessage();
            }
        }catch (SQLException e){
            throw  new MySqlException("CRUD异常");
        }
    }

    /**
     * 查询卖家的所有已上架商品
     * @param sellerID
     * @return List<Commodity>
     */
    @Transactional
    public List<Commodity> selectByUserID(String sellerID)  {
        try {
            List<Commodity> commodity=commodityMapper.selectByUserID(sellerID);

            return commodity;
        }catch (SQLException e){
            throw new MySqlException("根据sellerID查询商品异常");
        }

    }

    /**
     * 获取一个订单内的所有商品信息
     * @param buyerID
     * @return List<Commodity>
     */
    @Transactional
    public List<Object> selectOrderCommodity(String buyerID) {
        List<Object> list =new ArrayList<>();
        try{
            List<Order> orderList= orderMapper.selectBybuyerID(buyerID);
            for(Order order:orderList){
                List<Ordercommodity> orderList1=ordercommodityMapper.selectByorderID(order.getOrderid());
//               list.add(orderList1);
                for(Ordercommodity ordercommodity:orderList1){
                    List<Commodity> commodity=commodityMapper.selectBycID(ordercommodity.getCid());
                    list.add(order.getOrderid());
                    list.add(commodity);

                }
            }
        }catch (SQLException e){
            throw  new MySqlException("查询订单商品异常");
        }
        return list;
    }

    /**
     * 更新商品信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(Commodity record) {
//        return 0;
        try{
            return commodityMapper.updateByPrimaryKey(record);
        }catch (SQLException e){
            throw new MySqlException("更新商品信息异常!");
        }
    }

    /**
     * 添加商品详情图
     * @param picture
     * @return
     */
    @Transactional
    public String insert_Detail(Picture picture){
        try{
            pictureMapper.insert(picture);
            return SqlEnum.SQL_SUCCESS.getMessage();
        }catch (SQLException e){
            throw new MySqlException("商品详情图添加异常!");
        }
    }


    /**
     *更新商品
     * @param picture
     * @param CName
     * @param userID
     * @return
     */
//    @Transactional
//    @Override
//    public String updateMorePictureWithCName(String picture, String CName, String userID) {
//        try{
//            String OldPicPath=commodityMapper.selectByCName(CName,userID).getPicture()+"/*/";
//            String newPicPath=OldPicPath+picture;
//            commodityMapper.updatePicWithUserIDAndCName(CName,userID,newPicPath);
//            return sqlEnum.SQL_SUCCESS.getMessage();
//        }catch (mySqlException e){
//            throw new mySqlException("图片更新异常!");
//        }
//    }



}
