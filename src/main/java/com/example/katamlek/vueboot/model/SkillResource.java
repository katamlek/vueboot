package com.example.katamlek.vueboot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class SkillResource {
    @Autowired
    private SkillsHardcodedService skillManagementService;

    @GetMapping("/instructors/{username}/skills")
    public List<Skill> getAllSkills(@PathVariable String username) {
        return skillManagementService.findAll();
    }

    @GetMapping("/instructors/{username}/skills/{id}")
    public Skill getSkill(@PathVariable String username, @PathVariable long id) {
        return skillManagementService.findById(id);
    }

    @DeleteMapping("/instructors/{username}/skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable String username, @PathVariable long id) {
        Skill skill = skillManagementService.deleteById(id);
        if (skill != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/instructors/{username}/skills/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable String username, @PathVariable long id,
                                               @RequestBody Skill skill) {
        Skill skillUpdated = skillManagementService.save(skill);

        return new ResponseEntity<Skill>(skillUpdated, HttpStatus.OK);
    }

    @PostMapping("/instructors/{username}/skills")
    public ResponseEntity<Void> createSkill(@PathVariable String username, @RequestBody Skill skill) {

        Skill createdSkill = skillManagementService.save(skill);

        // Location
        // Get current resource url
        /// {id}
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdSkill.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}

