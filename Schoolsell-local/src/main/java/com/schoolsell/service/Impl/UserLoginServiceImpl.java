package com.schoolsell.service.Impl;

import com.schoolsell.util.myEnum.LoginEnum;
import com.schoolsell.dao.UserMapper;
import com.schoolsell.entity.User;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.schoolsell.util.myEnum.SqlEnum;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserMapper userMapper;

    /**
     * 删除用户
     * @param userid
     * @return
     */
    @Transactional
    @Override
    public String deleteByPrimaryKey(String userid) {
        if(userid!=null&&!"".equals(userid)){
            try{
                if(userMapper.selectByPrimaryKey(userid)!=null){
                    userMapper.deleteByPrimaryKey(userid);
                    return SqlEnum.SQL_SUCCESS.getMessage();
                }else{
                    return SqlEnum.USER_NOT_EXIST.getMessage();
                }
            }catch (SQLException e){
                throw new MySqlException(SqlEnum.SQL_DELETE_WRONG.getMessage());
            }
        }else{
            return SqlEnum.USER_ISNULL.getMessage();
        }
    }

    /**
     * 添加用户
     * @param record
     * @return
     */
    @Transactional
    @Override
    public String insert(User record) {
        if(record.getUserid()!=null&&record.getPassword()!=null&&
                record.getRealname()!=null&&record.getIdnumber()!=null&&
                record.getPhonenumber()!=null&&record.getIdnumber()!=null&&
                record.getCredibility()!=null){
            try {
                if (userMapper.selectByPrimaryKey(record.getUserid()) == null) {
                    userMapper.insert(record);
                    return SqlEnum.SQL_SUCCESS.getMessage();
                } else {
                    return SqlEnum.USER_IS_EXIST.getMessage();
                }
            }catch (SQLException e){
                throw new MySqlException(SqlEnum.SQL_INSERT_WRONG.getMessage());
            }

        }else{
            return SqlEnum.USER_REGISTER_WRONG.getMessage();
        }
    }

    /**
     * 根据userID获取用户信息
     * @param userid
     * @return
     */
    @Override
    public User selectByPrimaryKey(String userid) {
        try{
            return userMapper.selectByPrimaryKey(userid);
        }catch (SQLException e){
            throw new MySqlException("根据用户ID查询用户异常!");
        }

    }

    /**
     * 获取所有用户信息
     * @return
     */
    @Override
    public List<User> selectAll() {
        try{
            return userMapper.selectAll();
        }catch (SQLException e){
            throw new MySqlException("查询所有用户异常！");
        }

    }

    /**
     * 更新用户所有信息
     * @param record
     * @return
     */
    @Transactional
    @Override
    public String updateByPrimaryKey(User record) {
        if(record.getUserid()!=null&&record.getPassword()!=null&&
                record.getRealname()!=null&&record.getIdnumber()!=null&&
                record.getPhonenumber()!=null&&record.getIdnumber()!=null&&
                record.getCredibility()!=null){
            try{
                if(userMapper.selectByPrimaryKey(record.getUserid())!=null){
                    userMapper.updateByPrimaryKey(record);
                    return SqlEnum.SQL_SUCCESS.getMessage();
                }else{
                    return SqlEnum.USER_NOT_EXIST.getMessage();
                }
            }catch (SQLException e){
                throw new MySqlException(SqlEnum.SQL_UPDATE_WRONG.getMessage());
            }
        }else{
            return SqlEnum.USER_UPDATE_WRONG.getMessage();
        }
    }

    /**
     * 登录
     * @param userId
     * @param password
     * @return
     */
    @Transactional
    @Override
    public String userLogin(String userId, String password) {
        if(userId!=null&&!"".equals(userId)){
            try{
                if(userMapper.selectByPrimaryKey(userId)!=null){
                    if(password.equals(userMapper.selectByPrimaryKey(userId).getPassword())){
                        return LoginEnum.LOGIN_SUCCESS.getMessage();
                    }else{
                        return LoginEnum.LOGIN_PASSWORD_WRONG.getMessage();
                    }
                }else{
                    return LoginEnum.LOGIN_USER_NOT_EXIST.getMessage();
                }

            }catch (SQLException e){
                throw new MySqlException(SqlEnum.SQL_QUERY_WRONG.getMessage());
            }
        }else{
            return LoginEnum.LOGIN_USERNAME_ISNULL.getMessage();
        }

    }

    /**
     * 更改用户信息
     * @param userID
     * @param mark
     * @param newmsg
     * @return
     * @throws SQLException
     */
    @Transactional
    public Integer updatemsg(String userID, String mark, String newmsg){
        User user=new User();
        user.setUserid(userID);
        try {
            if (mark.equals("昵称")) {
                user.setUsername(newmsg);
                userMapper.updateuserName(user);
                return 1;
            } else if (mark.equals("电话号码")) {
                String phone = userMapper.selectByPrimaryKey(userID).getPhonenumber();
                if (phone.equals(newmsg)) {
                    return -1;
                } else {
                    user.setPhonenumber(newmsg);
                    userMapper.updatephone(user);
                    return 1;
                }
            } else if (mark.equals("地址")) {
                user.setAddress(newmsg);
                userMapper.updateaddress(user);
                return 1;
            } else {
                return 0;
            }
        }catch (SQLException e) {
            throw new MySqlException("CRUD异常");
        }
    }
}
