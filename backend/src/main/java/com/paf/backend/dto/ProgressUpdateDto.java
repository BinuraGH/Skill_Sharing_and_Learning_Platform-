package com.paf.backend.dto;

import java.util.List;
import java.util.Date;

public class ProgressUpdateDto {
    private String id;
    private String userId;
    private String title;
    private String caption;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private List<String> imgLink;
    private List<String> likedBy;

    public ProgressUpdateDto (){}

    public ProgressUpdateDto (String userId, String title, String caption, String status,  Date createdAt, Date updatedAt, List<String> imgLink, List<String> likedBy){
        this.userId = userId;
        this.title = title;
        this.caption = caption;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imgLink = imgLink;
        this.likedBy = likedBy;
    }

    public String getId(){
        return id;
    }

    public String getUserId(){
        return userId;
    }

    public String getTitle(){
        return title;
    }

    public String getCaption(){
        return caption;
    }

    public String getStatus(){
        return status;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public Date getUpdatedAt(){
        return updatedAt;
    }

    public List<String> getImgLink(){
        return imgLink;
    }

    public List<String> getLikedBy(){
        return likedBy;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setCaption(String caption){
        this.caption = caption;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt){
        this.updatedAt = updatedAt;
    }

    public void setImgLink(List<String> imgLink){
        this.imgLink = imgLink;
    }

    public void setLikedBy(List<String> likedBy){
        this.likedBy = likedBy;
    }
}



