package com.schoolsell.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Component
public class Feedback implements Serializable {
    private Integer feedbackid;

    private Date feedbackdate;

    private String feedbackerid;

    private String description;

    private static final long serialVersionUID = 1L;

    public Integer getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(Integer feedbackid) {
        this.feedbackid = feedbackid;
    }

    public Date getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(Date feedbackdate) {
        this.feedbackdate = feedbackdate;
    }

    public String getFeedbackerid() {
        return feedbackerid;
    }

    public void setFeedbackerid(String feedbackerid) {
        this.feedbackerid = feedbackerid == null ? null : feedbackerid.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", feedbackid=").append(feedbackid);
        sb.append(", feedbackdate=").append(feedbackdate);
        sb.append(", feedbackerid=").append(feedbackerid);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}