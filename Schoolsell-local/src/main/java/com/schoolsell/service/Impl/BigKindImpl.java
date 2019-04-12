package com.schoolsell.service.Impl;

import com.schoolsell.dao.BigkindMapper;
import com.schoolsell.dao.CommodityMapper;
import com.schoolsell.dao.SmallkindMapper;
import com.schoolsell.entity.Bigkind;
import com.schoolsell.entity.Commodity;
import com.schoolsell.entity.Smallkind;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.BigKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.添加大类
 * 2.根据大类ID删除大类
 * 3.根据大类ID查询单个大类信息
 * 4.查询所有大类信息，返回List<Bigkind>
 * 5.根据大类ID查询大类下的所有商品，返回List<Commodity>
 * 6.根据大类ID更新大类信息
 */

@Service
public class BigKindImpl implements BigKindService {
    @Autowired
    private BigkindMapper bigkindMapper;
    @Autowired
    public CommodityMapper commodityMapper;
    @Autowired
    public SmallkindMapper smallkindMapper;

    /**
     * 根据大类主键bigKindID删除大类
     *
     * @param bigKindID
     * @return
     * @throws MySqlException
     */
    @Override
    public int deleteByPrimaryKey(Integer bigKindID) throws MySqlException {
        return 0;
    }

    /**
     * 添加大类
     *
     * @param record
     * @return
     * @throws MySqlException
     */
    @Override
    public int insert(Bigkind record) throws MySqlException {
        return 0;
    }

    /**
     * 根据大类ID获取大类下的所有商品
     *
     * @param bigkindID
     * @return
     */
    @Transactional
    public List<Commodity> selecCommodityByBigkindID(int bigkindID) {
        List<Commodity> commodityList = new ArrayList<>();
        try {
            List<Smallkind> smallkinds = smallkindMapper.selectBybigKindID(bigkindID);      //获取此大类下所有小类
            for (Smallkind smallkind : smallkinds) {
                String smallkindName = smallkind.getSmallkindname();
                //获取此小类下的所有商品
                List<Commodity> commodities_smallkindName = commodityMapper.selectByKName(smallkindName);
                for (Commodity commodity : commodities_smallkindName) {             //for循环遍历这个小类下的所有商品
                    commodityList.add(commodity);                   //arrayList集合添加这个小类下的商品
                }
            }
            return commodityList;
        } catch (SQLException e) {
            throw new MySqlException("根据大类ID获取所有大类下的商品异常！");
        }
    }

    /**
     * 根据大类主键bigKindID查询大类
     *
     * @param bigKindID
     * @return 一条BigKind类型数据
     * @throws MySqlException
     */
    @Transactional
    @Override
    public Bigkind selectByPrimaryKey(Integer bigKindID) throws MySqlException {
        try {
            return bigkindMapper.selectByPrimaryKey(bigKindID);
        } catch (MySqlException e) {
            throw new MySqlException("根据bigkindID查询大类异常!");
        }
    }

    /**
     * 查询所有大类
     *
     * @return List<Bigkind>
     * @throws MySqlException
     */
    @Override
    public List<Bigkind> selectAll() throws MySqlException {
        try {
            List<Bigkind> bigkindList = bigkindMapper.selectAll();
            return bigkindList;
        } catch (MySqlException e) {
            throw new MySqlException("CRUD异常");
        }
    }

    /**
     * 根据大类ID更新大类信息
     *
     * @param record
     * @return
     * @throws MySqlException
     */
    @Override
    public int updateByPrimaryKey(Bigkind record) throws MySqlException {
        return 0;
    }
}
