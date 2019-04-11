package com.schoolsell.dao;

import com.schoolsell.entity.Feedback;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer feedbackid);

    int insert(Feedback record);

    Feedback selectByPrimaryKey(Integer feedbackid);

    List<Feedback> selectAll();

    int updateByPrimaryKey(Feedback record);
}