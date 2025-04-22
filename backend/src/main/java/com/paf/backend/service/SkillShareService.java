package com.paf.backend.service;

import com.paf.backend.document.SkillSharing;
import com.paf.backend.dto.SkillShareDto;
import com.paf.backend.repository.SkillShareRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillShareService {

    @Autowired
    private SkillShareRepository repository;

    public SkillSharing createSkillSharing(SkillShareDto dto) {
        if (dto.getMedia() != null && dto.getMedia().size() > 3) {
            throw new IllegalArgumentException("Media list can contain at most 3 items.");
        }

        SkillSharing skillSharing = new SkillSharing(
                dto.getUserId(),
                dto.getMedia(),
                dto.getDescription(),
                java.time.LocalDateTime.now());

        return repository.save(skillSharing);
    }

    public List<SkillSharing> getAllSkillSharings() {
        return repository.findAll();
    }

    public List<SkillSharing> getSkillSharingsByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public Optional<SkillSharing> updateSkillSharing(String id, SkillShareDto dto) {
        Optional<SkillSharing> optional = repository.findById(id);
        if (optional.isPresent()) {
            SkillSharing existing = optional.get();
            existing.setUserId(dto.getUserId());
            existing.setMedia(dto.getMedia());
            existing.setDescription(dto.getDescription());
            existing.setDateTime(java.time.LocalDateTime.now());
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
