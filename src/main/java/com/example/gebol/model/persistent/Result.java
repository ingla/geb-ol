package com.example.gebol.model.persistent;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Result {

    public Result(Long disciplineId, Long participantId, int place) {
        this.disciplineId = disciplineId;
        this.participantId = participantId;
        this.place = place;
    }

    @NotNull
    private Long disciplineId;

    @NotNull
    private Long participantId;

    @NotNull
    private Integer place;
}
