package com.example.katamlek.vueboot.resource;

import com.example.katamlek.vueboot.model.Skill;
import com.example.katamlek.vueboot.service.SkillsHardcodedService;
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
    private SkillsHardcodedService skillsHardcodedService;

    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return skillsHardcodedService.findAll();
    }

    @GetMapping("/skills/{id}")
    public Skill getSkill(@PathVariable long id) {
        return skillsHardcodedService.findById(id);
    }

    @DeleteMapping("/skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable long id) {
        Skill skill = skillsHardcodedService.deleteById(id);
        if (skill != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/skills/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable long id,
                                               @RequestBody Skill skill) {
        Skill skillUpdated = skillsHardcodedService.save(skill);

        return new ResponseEntity<Skill>(skillUpdated, HttpStatus.OK);
    }

    @PostMapping("/skills")
    public ResponseEntity<Void> createSkill(@RequestBody Skill skill) {

        Skill createdSkill = skillsHardcodedService.save(skill);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdSkill.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}

