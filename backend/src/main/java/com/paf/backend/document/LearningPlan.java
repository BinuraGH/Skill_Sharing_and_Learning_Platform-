package com.paf.backend.document;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "learningPlans")
public class LearningPlan {
    
    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private String status; // In Progress, Completed
    private List<Topic> topics;

    public LearningPlan(String id, String userId, String title, String description, String status, List<Topic> topics) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.topics = topics;
    }
    public LearningPlan() {
    }
    
    public String getId() {
        return id;
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
    public String getStatus() {
        return status;
    }
    public List<Topic> getTopics() {
        return topics;
    }
    public void setId(String id) {
        this.id = id;
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
    public void setStatus(String status) {
        this.status = status;
    }
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    /*  */
}
