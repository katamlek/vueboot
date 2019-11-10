package com.example.katamlek.vueboot.resource;

import com.example.katamlek.vueboot.model.Consultant;
import com.example.katamlek.vueboot.service.ConsultantHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200", "http://localhost:8081"})
@RestController
public class ConsultantResource {
    @Autowired
    private ConsultantHardcodedService consultantHardcodedService;

    @GetMapping("/consultants")
    public List<Consultant> getAllConsultants() {
        return consultantHardcodedService.findAll();
    }

    @GetMapping("/instructors/{id}")
    public Consultant getConsultant(@PathVariable long id) {
        return consultantHardcodedService.findById(id);
    }

    @DeleteMapping("/consultants/{id}")
    public ResponseEntity<Void> deleteConsultant(@PathVariable long id) {
        Consultant consultant = consultantHardcodedService.deleteById(id);
        if (consultant != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/consultants/{id}")
    public ResponseEntity<Consultant> updateConsultant(@PathVariable long id,
                                                       @RequestBody Consultant consultant) {
        Consultant consultantUpdated = consultantHardcodedService.save(consultant);

        return new ResponseEntity<Consultant>(consultantUpdated, HttpStatus.OK);
    }

    @PostMapping("/consultants")
    public ResponseEntity<Void> createConsultant(@RequestBody Consultant consultant) {

        Consultant createdConsultant = consultantHardcodedService.save(consultant);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdConsultant.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
