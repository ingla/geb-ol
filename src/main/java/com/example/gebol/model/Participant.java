package com.example.gebol.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class Participant {

    public Participant(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;

    @NotBlank
    private String name;
}
