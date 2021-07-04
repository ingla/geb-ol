package com.example.gebol.model.persistent;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * ParticipantId will be -1 if not set.
 * Score will be -1 if not set.
 */
@Data
@NoArgsConstructor
public class LiveResult {

    public LiveResult(Long disciplineId, Long participantId, int level, int place, int score) {
        this.disciplineId = disciplineId;
        this.participantId = participantId;
        this.level = level;
        this.place = place;
        this.score = score;
    }

    @NotNull
    private Long disciplineId;

    @NotNull
    private Long participantId;

    @NotNull
    private Integer level;

    @NotNull
    private Integer place;

    @NotNull
    private Integer score = -1;
}
