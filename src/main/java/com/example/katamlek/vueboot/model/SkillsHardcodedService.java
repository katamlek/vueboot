package com.example.katamlek.vueboot.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillsHardcodedService {
    private static List<Skill> skills = new ArrayList<>();
    private static long idCounter = 0;

    static {
        skills.add(new Skill(++idCounter, "C++", Level.basic));
        skills.add(new Skill(++idCounter, "Python", Level.expert));
        skills.add(new Skill(++idCounter, "C#", Level.high));
        skills.add(new Skill(++idCounter, "Java",
                Level.medium));
    }

    public List<Skill> findAll() {
        return skills;
    }

    public Skill deleteById(long id) {
        Skill skill = findById(id);
        if (skill == null)
            return null;
        if (skills.remove(skill)) {
            return skill;
        }
        return null;
    }

    public Skill findById(long id) {
        for (Skill skill : skills) {
            if (skill.getId() == id) {
                return skill;
            }
        }
        return null;
    }

    public Skill save(Skill skill) {
        if (skill.getId() == -1 || skill.getId() == 0) {
            skill.setId(++idCounter);
            skills.add(skill);
        } else {
            deleteById(skill.getId());
            skills.add(skill);
        }
        return skill;
    }
}

