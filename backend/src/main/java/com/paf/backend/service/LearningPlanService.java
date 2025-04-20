package com.paf.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


import com.paf.backend.document.LearningPlan;
import com.paf.backend.dto.LearningPlanDto;
import com.paf.backend.repository.LearningPlanRepository;

public class LearningPlanService {
    @Autowired
    private LearningPlanRepository repository;

    public LearningPlan createPlan(LearningPlanDto dto) {
        LearningPlan plan = new LearningPlan(
                dto.getUserId(),
                dto.getTitle(),
                dto.getDescription(),
                "In Progress",  // default status
                dto.getTopics()
        );
        return repository.save(plan);
    }

    public List<LearningPlan> getPlansByUser(String userId) {
        return repository.findByUserId(userId);
    }

    public Optional<LearningPlan> getPlan(String id) {
        return repository.findById(id);
    }

    public LearningPlan updatePlan(String id, LearningPlanDto dto) {
        Optional<LearningPlan> optionalPlan = repository.findById(id);
        if (optionalPlan.isPresent()) {
            LearningPlan plan = optionalPlan.get();
            plan.setTitle(dto.getTitle());
            plan.setDescription(dto.getDescription());
            plan.setTopics(dto.getTopics());
            return repository.save(plan);
        }
        return null;
    }

    public void deletePlan(String id) {
        repository.deleteById(id);
    }
}

