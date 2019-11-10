package com.example.katamlek.vueboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Consultant {
    private Long id;
    private String name;
    List<Skill> skillList;
}
