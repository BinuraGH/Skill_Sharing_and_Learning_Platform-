package com.paf.backend.dto;

import java.util.List;


public class LearningPlanDto {
    private String userId;
    private String title;
    private String description;
    private List<Topic> topics;

    

    public LearningPlanDto(String userId, String title, String description, List<Topic> topics) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.topics = topics;
    }

    public LearningPlanDto() {
    }
    public String getUserId() {
        return userId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public List<Topic> getTopics() {
        return topics;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    
}
