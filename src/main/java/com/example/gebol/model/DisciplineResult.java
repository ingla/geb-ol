package com.example.gebol.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class DisciplineResult {

    public DisciplineResult(String participantName, Long participantId, int place) {
        this.participantName = participantName;
        this.participantId = participantId;
        this.place = place;
    }

    @NotNull
    private String participantName;

    @NotNull
    private Long participantId;

    @NotNull
    private int place;
}
