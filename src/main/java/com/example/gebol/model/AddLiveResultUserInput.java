package com.example.gebol.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddLiveResultUserInput extends AddResultUserInput {

    public AddLiveResultUserInput(Long disciplineId, int participantCount) {
        super(disciplineId, participantCount);
    }

    @NotNull
    private int level;

    public String toString() {
        return "AddLiveResultUserInput(level=" + level +
                ", participantCount=" + getParticipantCount() +
                ", disciplineId=" + getDisciplineId();
    }
}