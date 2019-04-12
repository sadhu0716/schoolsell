package com.schoolsell.service.Impl;

import com.schoolsell.dao.ShopcarMapper;
import com.schoolsell.entity.Shopcar;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.ShopCarService;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCarServiceImpl implements ShopCarService {
    @Autowired
    ShopcarMapper shopcarMapper;


    /**
     * 获取某一用户的所有未结算的购物车信息
     */
    @Override
    @Transactional
    public List<Shopcar> selectByuseridAndStatus(String userid, int isfinish) {
        List<Shopcar> list=new ArrayList<>();
        try {
            list=  shopcarMapper.selectByuserid(userid,isfinish);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySqlException("查询购物车失败");
        }
        return list;
    }

    /**
     * 添加一条购物车信息
     * @param shopcar
     * @return
     */
    @Transactional
    @Override
    public int insertShopcar(Shopcar shopcar) {
        int result=0;
        try {
            result=shopcarMapper.insert(shopcar);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySqlException("添加购物车失败");
        }
        return result;
    }
}
