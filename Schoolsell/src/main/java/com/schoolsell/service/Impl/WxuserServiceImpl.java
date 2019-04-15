package com.schoolsell.service.Impl;

import com.schoolsell.dao.WxuserMapper;
import com.schoolsell.entity.Wxuser;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.WxuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class WxuserServiceImpl implements WxuserService {
    @Autowired
    private WxuserMapper wxuserMapper;

    @Transactional
    @Override
    public int add(Wxuser wxuser) {
        try{
            return wxuserMapper.add(wxuser);
        }catch (SQLException e){
            throw new MySqlException("添加微信用户异常！！！");
        }
    }

    @Transactional
    @Override
    public int delete(Integer wxID) {
        try{
            return wxuserMapper.delete(wxID);
        }catch (SQLException e){
            throw new MySqlException("删除微信用户异常！！！");
        }
    }

    @Transactional
    @Override
    public Wxuser selectByPrimaryKey(Integer wxID) {
        try{
            return wxuserMapper.selectByPrimaryKey(wxID);
        }catch (SQLException e){
            throw new MySqlException("根据微信用户表主键ID查询微信用户异常！！！");
        }
    }

    @Transactional
    @Override
    public Wxuser selectByUserID(String userID) {
        try{
            return wxuserMapper.selectByUserID(userID);
        }catch (SQLException e){
            throw new MySqlException("根据微信用户ID查询微信用户异常！！！");
        }
    }

    @Transactional
    @Override
    public Wxuser selectByOpenID(String openID) {
        try{
            return wxuserMapper.selectByOpenID(openID);
        }catch (SQLException e){
            throw new MySqlException("根据微信用户openID查询微信用户异常！！！");
        }
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(Integer wxID) {
        try{
            return wxuserMapper.updateByPrimaryKey(wxID);
        }catch (SQLException e){
            throw new MySqlException("根据微信用户表主键ID更新表异常！！！");
        }
    }

    @Transactional
    @Override
    public int updateByUserID(String userID) {
        try{
            return wxuserMapper.updateByUserID(userID);
        }catch (SQLException e){
            throw new MySqlException("根据微信用户ID更新表异常！！！");
        }
    }
}
