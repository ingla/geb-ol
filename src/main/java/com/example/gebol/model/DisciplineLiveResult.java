package com.example.gebol.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

/**
 * ParticipantName can be blank.
 * Score will be -1 if no score is set.
 */
@Data
@NoArgsConstructor
public class DisciplineLiveResult {

    public DisciplineLiveResult(String participantName, int level, int place, int score) {
        this.participantName = participantName;
        this.level = level;
        this.place = place;
        this.score = score;
    }

    @NotNull
    private String participantName;

    @NotNull
    private int level;

    @NotNull
    private int place;

    @NotNull
    private int score;
}