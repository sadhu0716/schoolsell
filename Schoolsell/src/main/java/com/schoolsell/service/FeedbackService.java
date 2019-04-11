package com.schoolsell.service;

import com.schoolsell.entity.Feedback;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface FeedbackService {
    int deleteByPrimaryKey(Integer feedbackID);

    int insert(Feedback record);

    Feedback selectByPrimaryKey(Integer feedbackID);

    List<Feedback> selectAll();

    int updateByPrimaryKey(Feedback record);

    List<String> selectQuery(String feedbackID);

    Integer insert(String feedbackerID, String description, Date date, List<String> list);

}
