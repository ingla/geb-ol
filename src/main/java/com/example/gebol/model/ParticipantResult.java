package com.example.gebol.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ParticipantResult {

    public ParticipantResult(String disciplineName, int place) {
        this.disciplineName = disciplineName;
        this.place = place;
    }

    @NotNull
    private String disciplineName;

    @NotNull
    private int place;
}
