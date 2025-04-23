package com.paf.backend.service;

import com.google.firebase.cloud.StorageClient;
import com.paf.backend.document.SkillSharing;
import com.paf.backend.repository.SkillShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillShareService {

    @Autowired
    private SkillShareRepository repository;

    public SkillSharing createSkillSharing(String userId, String description, List<MultipartFile> files)
            throws Exception {
        if (files != null && files.size() > 3) {
            throw new IllegalArgumentException("You can upload up to 3 media files.");
        }

        List<String> mediaUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            StorageClient.getInstance().bucket().create(fileName, file.getBytes(), file.getContentType());
            String mediaUrl = "https://firebasestorage.googleapis.com/v0/b/" +
                    StorageClient.getInstance().bucket().getName() +
                    "/o/" + fileName.replaceAll("/", "%2F") + "?alt=media";
            mediaUrls.add(mediaUrl);
        }

        SkillSharing skillSharing = new SkillSharing(userId, mediaUrls, description, LocalDateTime.now());
        return repository.save(skillSharing);
    }

    public List<SkillSharing> getAllSkillSharings() {
        return repository.findAll();
    }

    public List<SkillSharing> getSkillSharingsByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public Optional<SkillSharing> updateSkillSharing(String id, String description) {
        Optional<SkillSharing> optional = repository.findById(id);
        if (optional.isPresent()) {
            SkillSharing existing = optional.get();
            existing.setDescription(description);
            existing.setDateTime(LocalDateTime.now());
            return Optional.of(repository.save(existing));
        }
        return Optional.empty();
    }

    public boolean deleteSkillSharing(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
