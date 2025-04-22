package com.paf.backend.service;

import com.paf.backend.document.Follow;
import com.paf.backend.document.LearningPlan;
import com.paf.backend.document.Topic;
import com.paf.backend.dto.LearningPlanDto;
import com.paf.backend.dto.LearningPlanWithProgressDto;
import com.paf.backend.repository.FollowRepository;
import com.paf.backend.repository.LearningPlanRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LearningPlanService {

    private final LearningPlanRepository repository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;
    private final FollowRepository followRepository;


    public LearningPlan createPlan(LearningPlanDto dto) {
        LearningPlan plan = modelMapper.map(dto, LearningPlan.class);
        plan.setId(null); // MongoDB will auto-generate the ID
        plan.setStatus("In Progress");
        return repository.save(plan);
    }

    public List<LearningPlan> getAllPlans() {
        return repository.findAll();
    }

    public List<LearningPlan> getPlansByUser(String userId) {
        return repository.findByUserId(userId);
    }

    public Optional<LearningPlan> getPlan(String id) {
        return repository.findById(id);
    }

    public LearningPlan updatePlan(String id, LearningPlanDto dto, String requestingUserId) {
        LearningPlan plan = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan not found"));

        if (!plan.getUserId().equals(requestingUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized to update this plan");
        }

        modelMapper.map(dto, plan);
        return repository.save(plan);
    }

    public void deletePlan(String id, String requestingUserId) {
        LearningPlan plan = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan not found"));

        if (!plan.getUserId().equals(requestingUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized to delete this plan");
        }

        repository.deleteById(id);
    }

    public LearningPlan markTopicCompleted(String planId, int index) {
        LearningPlan plan = repository.findById(planId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan not found"));

        if (index < 0 || index >= plan.getTopics().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid topic index");
        }

        plan.getTopics().get(index).setCompleted(true);

        boolean allDone = plan.getTopics().stream().allMatch(Topic::isCompleted);
        if (allDone && !"Completed".equals(plan.getStatus())) {
            plan.setStatus("Completed");
            notificationService.notifyPlanCompletion(plan.getUserId(), plan.getTitle());
        }

        return repository.save(plan);
    }

    public LearningPlanWithProgressDto getPlanWithProgress(String id) {
        LearningPlan plan = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan not found"));

        long completed = plan.getTopics().stream().filter(Topic::isCompleted).count();
        int progress = plan.getTopics().isEmpty() ? 0 :
                (int) ((double) completed / plan.getTopics().size() * 100);

        LearningPlanWithProgressDto dto = modelMapper.map(plan, LearningPlanWithProgressDto.class);
        dto.setProgressPercentage(progress);
        return dto;
    }
    public List<LearningPlan> getPlansByPaymentStatus(boolean isPaid) {
        return repository.findByIsPaid(isPaid);
    }
    public List<LearningPlan> getPlansByFollowedUsers(String userId) {
        List<Follow> follows = followRepository.findByFollowerId(userId);
        List<String> followedUserIds = follows.stream()
            .map(Follow::getFollowedId)
            .toList();
    
        if (followedUserIds.isEmpty()) {
            return List.of(); // Return empty list if user follows no one
        }
        return repository.findByUserIdIn(followedUserIds);
    }  
}
