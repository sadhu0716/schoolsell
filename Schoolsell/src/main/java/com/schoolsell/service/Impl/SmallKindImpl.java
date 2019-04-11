package com.schoolsell.service.Impl;

import com.schoolsell.dao.SmallkindMapper;
import com.schoolsell.entity.Smallkind;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.SmallKindService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
@Service
public class SmallKindImpl implements SmallKindService {
    @Autowired
    private SmallkindMapper smallkindMapper;

    /**
     * 删除一条小类
     * @param smallKindID
     * @return
     * @throws MySqlException
     */
    @Override
    public int deleteByPrimaryKey(Integer smallKindID) throws MySqlException {
        try{
            return smallkindMapper.deleteByPrimaryKey(smallKindID);
        }catch (SQLException e){
            throw new MySqlException("删除小类信息异常!");
        }
    }

    /**
     * 添加一条小类
     * @param record
     * @return
     * @throws MySqlException
     */
    @Override
    public int insert(Smallkind record) throws MySqlException {
        try{
            return smallkindMapper.insert(record);
        }catch (SQLException e){
            throw new MySqlException("添加小类信息异常!");
        }
    }

    /**
     * 查询具体小类信息
     * @param smallKindID
     * @return
     * @throws MySqlException
     */
    @Override
    public Smallkind selectByPrimaryKey(Integer smallKindID) throws MySqlException {
        try{
            return smallkindMapper.selectByPrimaryKey(smallKindID);
        }catch (SQLException e){
            throw new MySqlException("查询小类信息异常!");
        }
    }

    /**
     * 查询所有小类
     * @return
     * @throws MySqlException
     */
    @Transactional
    @Override
    public List<Smallkind> selectAll() throws MySqlException {
        try {
            List<Smallkind> smallkindList=  smallkindMapper.selectAll();
            return smallkindList;
        }catch (SQLException e){
            throw new MySqlException("CRUD异常");
        }

    }

    /**
     * 获取大类ID下的所有小类
     * @param bigKindID
     * @return
     */
    @Transactional
    public List<Smallkind> selectBybigKindID(int bigKindID){
        try{
            return smallkindMapper.selectBybigKindID(bigKindID);
        }catch (SQLException e){
            throw new MySqlException("查询大类下的所有小类时异常！");
        }
    }

    /**
     * 更新一条小类信息
     * @param record
     * @return
     * @throws MySqlException
     */
    @Override
    public int updateByPrimaryKey(Smallkind record) throws MySqlException {
        try{
            return smallkindMapper.updateByPrimaryKey(record);
        }catch (SQLException e){
            throw new MySqlException("更新小类信息异常!");
        }
    }

    /**
     * 获取一个大类下的所有小类
     * @param bigKindID
     * @return
     * @throws MySqlException
     */
    @Transactional
    @Override
    public List<Smallkind> kindquery(Integer bigKindID) throws MySqlException {
        try {
            List<Smallkind> smallkindList= smallkindMapper.selectBybigKindID(bigKindID);
            return smallkindList;
        }catch (SQLException e){
            throw  new MySqlException("CRUD异常");
        }
    }
}
