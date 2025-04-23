package com.paf.backend.dto;

import java.util.Date;

import com.paf.backend.document.Data;

@Data
public class CommentDTO {
    private String id;
    private String text;
    private String userId;
    private String username;
    private String profileImage;
    private String postId;
    private Date createdAt;
    private Date updatedAt;
}
