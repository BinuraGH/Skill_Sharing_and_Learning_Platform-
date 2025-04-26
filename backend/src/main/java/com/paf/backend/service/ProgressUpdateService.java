package com.paf.backend.service;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.paf.backend.document.ProgressUpdate;
import com.paf.backend.dto.ProgressUpdateDto;
import com.paf.backend.repository.ProgressUpdateRepository;

@Service
public class ProgressUpdateService {

    @Autowired
    private ProgressUpdateRepository repository;

    // ✅ Create new progress update
    public ProgressUpdate createProgressUpdate(ProgressUpdateDto dto){
        ProgressUpdate progress = new ProgressUpdate (
            dto.getUserId(),
            dto.getTitle(),
            dto.getCaption(),
            "In Progress",  // default status
            dto.getCreatedAt(),
            dto.getUpdatedAt(),
            dto.getImgLink(),
            dto.getLikedBy()
        );

        ProgressUpdate saved = repository.save(progress);
        System.out.println("Inserted Progress Update with ID: " + saved.getId());
        return saved;
    }

    // ✅ Get all progress updates for a user
    public List<ProgressUpdate> getProgressUpdatesByUser (String userId){
        return repository.findByUserId(userId);
    }

    // ✅ Get a single progress update by ID
    public Optional<ProgressUpdate> getProgressUpdate (String id){
        return repository.findById(id);
    }

    // ✅ Update a progress update by ID
     public ResponseEntity<?> updatePostById(String id,ProgressUpdate post){
        Optional<ProgressUpdate> existingPost =  repository.findById(id);
        if(existingPost.isPresent()){
            ProgressUpdate updatePost = existingPost.get();
            if(post.getCaption() != null) {
                updatePost.setCaption(post.getCaption());
            }
            if(post.getImgLink() != null) {
                updatePost.setImgLink(post.getImgLink());
            }
            updatePost.setUpdatedAt(new Date(System.currentTimeMillis()));
            return new ResponseEntity<>(repository.save(updatePost), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Post Update Error",HttpStatus.NOT_FOUND);
        }
    }
    
    
 

    // ✅ Delete a progress update
        public boolean deleteProgressUpdate(String id) {
        Optional<ProgressUpdate> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
