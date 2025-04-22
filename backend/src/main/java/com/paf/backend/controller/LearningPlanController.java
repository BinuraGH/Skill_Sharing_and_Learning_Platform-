package com.paf.backend.controller;

import com.paf.backend.document.LearningPlan;
import com.paf.backend.dto.LearningPlanDto;
import com.paf.backend.dto.LearningPlanWithProgressDto;
// import com.paf.backend.dto.LearningPlanWithProgressDto;
import com.paf.backend.service.LearningPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LearningPlanController {

    private final LearningPlanService service;

    //Create a new learning plan
    @PostMapping
    public LearningPlan createPlan(@RequestBody LearningPlanDto learndto) {
        return service.createPlan(learndto);
    }

    //Get all plans (public browse)
    @GetMapping
    public List<LearningPlan> getAllPlans() {
        return service.getAllPlans();
    }

    //Get plans by user ID
    @GetMapping("/user/{userId}")
    public List<LearningPlan> getPlansByUser(@PathVariable String userId) {
        return service.getPlansByUser(userId);
    }

    //Get a single plan by ID
    @GetMapping("/{id}")
    public LearningPlan getPlan(@PathVariable String id) {
        return service.getPlan(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan not found"));
    }

    //Update a plan by ID
    @PutMapping("/{id}")
    public LearningPlan updatePlan(@PathVariable String id,
                                   @RequestBody LearningPlanDto dto,
                                   @RequestParam String requestingUserId) {
        return service.updatePlan(id, dto, requestingUserId);
    }

    //Delete a plan by ID
    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable String id,
                           @RequestParam String requestingUserId) {
        service.deletePlan(id, requestingUserId);
    }

    //Mark topic as completed (index-based)
    @PatchMapping("/{planId}/topics/{index}/complete")
    public LearningPlan markTopicAsComplete(@PathVariable String planId,
                                            @PathVariable int index) {
        return service.markTopicCompleted(planId, index);
    }

    @GetMapping("/{id}/progress")
    public LearningPlanWithProgressDto getPlanWithProgress(@PathVariable String id) {
        return service.getPlanWithProgress(id);
    }
    // NEW: Filter plans by isPaid (true = paid, false = free)
    @GetMapping("/filter")
    public List<LearningPlan> filterPlans(@RequestParam(required = false) Boolean isPaid) {
        if (isPaid == null) {
            return service.getAllPlans(); // default: return all plans
        }
        return service.getPlansByPaymentStatus(isPaid);
    }   
    @GetMapping("/followed/{userId}")
    public List<LearningPlan> getPlansByFollowedUsers(@PathVariable String userId) {
        return service.getPlansByFollowedUsers(userId);
    }
    
}

