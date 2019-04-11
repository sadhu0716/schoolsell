package com.schoolsell.service.Impl;

import com.schoolsell.dao.UserMapper;
import com.schoolsell.entity.Feedback;
import com.schoolsell.exception.MySqlException;
import com.schoolsell.service.FeedbackService;
import com.schoolsell.dao.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    Feedback feedback;

    @Override
    public int deleteByPrimaryKey(Integer feedbackID) {
        return 0;
    }

    @Override
    public int insert(Feedback record) {
        return 0;
    }

    @Override
    public Feedback selectByPrimaryKey(Integer feedbackID) {
        return null;
    }

    @Override
    public List<Feedback> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Feedback record) {
        return 0;
    }

    /**
     * 查找用户姓名与电话
     * @param feedbackID
     * @return
     */
    @Transactional
    @Override
    public List<String> selectQuery(String feedbackID) {
        List<String> list=new ArrayList<>();
        try {
            String feedbackerName = userMapper.selectByPrimaryKey(feedbackID).getUsername();
            String feedbackerphone = userMapper.selectByPrimaryKey(feedbackID).getPhonenumber();
            list.add(feedbackerName);
            list.add(feedbackerphone);
            return list;
        }catch (SQLException e) {
            throw new MySqlException("CRUD异常");
        }
    }

    /**
     * 添加一条反馈
     * @param feedbackerID
     * @param description
     * @param date
     * @param list
     * @return
     */
    @Transactional
    @Override
    public Integer insert(String feedbackerID, String description, Date date, List<String> list) {
        try {
            feedback.setFeedbackdate(date);
            feedback.setDescription(description);
            feedback.setFeedbackerid(feedbackerID);
//            feedback.setFeedbackername(list.get(0));
//            feedback.setFeedbackerphone(list.get(1));
            int count=feedbackMapper.insert(feedback);
            return count;
        }catch (MySqlException e){
            throw  new MySqlException("CRUD异常");
        }
    }

}
