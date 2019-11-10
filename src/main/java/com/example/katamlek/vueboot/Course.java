package com.example.katamlek.vueboot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    private Long id;
    private String username;
    private String description;
}
