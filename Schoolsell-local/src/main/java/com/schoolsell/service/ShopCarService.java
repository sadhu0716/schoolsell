package com.schoolsell.service;

import com.schoolsell.entity.Shopcar;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ShopCarService {
    //根据用户userid来查询LIstanbul<Shopcar>,和状态isChoose，isFinish
    List<Shopcar> selectByuseridAndStatus(String userid, int isfinish);

    /**
     * 插入购物车记录
     * @param shopcar
     * @return
     */
    int insertShopcar(Shopcar shopcar);
}
