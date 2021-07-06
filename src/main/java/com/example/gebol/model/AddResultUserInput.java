package com.example.gebol.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddResultUserInput {

    public AddResultUserInput(Long disciplineId, int participantCount) {
        this.disciplineId = disciplineId;
        this.participantCount = participantCount;
    }

    @NotNull
    private Long disciplineId;

    @NotNull
    @Min(value = 4, message = "Antall deltagere må være minst 4")
    private int participantCount;
}