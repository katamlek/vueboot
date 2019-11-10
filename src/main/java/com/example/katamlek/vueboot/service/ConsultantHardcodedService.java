package com.example.katamlek.vueboot.service;

import com.example.katamlek.vueboot.model.Consultant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultantHardcodedService {
    private static List<Consultant> consultants = new ArrayList<>();
    private static long idCounter = 0;

    static {
        consultants.add(new Consultant(++idCounter, "Andrei"));
        consultants.add(new Consultant(++idCounter, "Titus"));
        consultants.add(new Consultant(++idCounter, "Julian"));
        consultants.add(new Consultant(++idCounter, "Kasia"));
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
}
