package com.example.katamlek.vueboot.service;

import com.example.katamlek.vueboot.model.Consultant;
import com.example.katamlek.vueboot.model.Level;
import com.example.katamlek.vueboot.model.Skill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultantHardcodedService {
    private static List<Consultant> consultants = new ArrayList<>();
    private static List<Skill> skills = new ArrayList<>();
    private static long idCounter = 0;
    private static long isSkill = 0;

    static {
        skills.add(new Skill(++isSkill, "abc", Level.basic));
        consultants.add(new Consultant(++idCounter, "Andrei", skills));
        consultants.add(new Consultant(++idCounter, "Titus", skills));
        consultants.add(new Consultant(++idCounter, "Julian", skills));
        consultants.add(new Consultant(++idCounter, "Kasia", skills));
    }

    public List<Consultant> findAll() {
        return consultants;
    }

    public Consultant deleteById(long id) {
        Consultant consultant = findById(id);
        if (consultant == null)
            return null;
        if (consultants.remove(consultant)) {
            return consultant;
        }
        return null;
    }

    public Consultant findById(long id) {
        for (Consultant consultant : consultants) {
            if (consultant.getId() == id) {
                return consultant;
            }
        }
        return null;
    }

    public Consultant save(Consultant consultant) {
        if (consultant.getId() == -1 || consultant.getId() == 0) {
            consultant.setId(++idCounter);
            consultants.add(consultant);
        } else {
            deleteById(consultant.getId());
            consultants.add(consultant);
        }
        return consultant;
    }

    public List<Skill> findConsultantSkills(Long id) {
        Consultant consultant = findById(id);
        return consultant.getSkillList();
    }
}
