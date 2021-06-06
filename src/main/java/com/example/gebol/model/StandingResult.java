package com.example.gebol.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class StandingResult {

    public StandingResult(
            String participantName,
            Long participantId,
            int points
    ) {
        this.participantName = participantName;
        this.participantId = participantId;
        this.points = points;
    }

    @NotNull
    private String participantName;

    @NotNull
    private Long participantId;

    @NotNull
    private int place;

    @NotNull
    private int points;
}
