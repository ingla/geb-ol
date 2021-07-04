package com.example.gebol.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

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

    @Nullable
    private Long participantId;

    @NotNull
    private Integer level;

    @NotNull
    private Integer place;

    @Nullable
    private Integer score;
}