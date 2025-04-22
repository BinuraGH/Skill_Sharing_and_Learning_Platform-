package com.paf.backend.controller;

import com.paf.backend.document.SkillSharing;
import com.paf.backend.dto.SkillShareDto;
import com.paf.backend.service.SkillShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skill-sharing")
@CrossOrigin(origins = "*")
public class SkillSharingController {

    @Autowired
    private SkillShareService service;

    @PostMapping
    public ResponseEntity<SkillSharing> create(@RequestBody SkillShareDto dto) {
        SkillSharing created = service.createSkillSharing(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<SkillSharing>> getAll() {
        return ResponseEntity.ok(service.getAllSkillSharings());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillSharing>> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(service.getSkillSharingsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillSharing> update(@PathVariable String id, @RequestBody SkillShareDto dto) {
        Optional<SkillSharing> updated = service.updateSkillSharing(id, dto);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = service.deleteSkillSharing(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
