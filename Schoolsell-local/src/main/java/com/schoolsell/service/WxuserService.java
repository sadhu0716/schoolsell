package com.schoolsell.service;

import com.schoolsell.entity.Wxuser;

public interface WxuserService {

    int add(Wxuser wxuser);

    int delete(Integer wxID);

    Wxuser selectByPrimaryKey(Integer wxID);

    Wxuser selectByUserID(String userID);

    Wxuser selectByOpenID(String openID);

    int updateByPrimaryKey(Integer wxID);

    int updateByUserID(String userID);

}
