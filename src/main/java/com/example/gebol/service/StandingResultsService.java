package com.example.gebol.service;

import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.persistent.Participant;
import com.example.gebol.model.StandingResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StandingResultsService {
    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final PointCalculationService pointCalculationService;

    StandingResultsService(
            ResultRepository resultRepository,
            ParticipantRepository participantRepository,
            PointCalculationService pointCalculationService
    ) {
        this.resultRepository = resultRepository;
        this. participantRepository = participantRepository;
        this.pointCalculationService = pointCalculationService;
    }

    public List<StandingResult> getStandingResults() {
        List<Participant> participants = participantRepository.findAll();
        List<StandingResult> standingResults = new ArrayList<>();

        for (Participant participant : participants) {
            // Sum up points for all results
            int points = resultRepository.findByParticipantId(participant.getId())
                    .stream()
                    .map(result -> result.getPlace())
                    .map(place -> pointCalculationService.getPointsForPlace(place))
                    .reduce(0, (points1, points2) -> points1 + points2);

            StandingResult standingResult = new StandingResult(
                    participant.getName(),
                    participant.getId(),
                    points
            );
            standingResults.add(standingResult);
        }
        standingResults.sort(Comparator.comparing(StandingResult::getPoints).reversed());
        int prevPoints = -1;
        StandingResult prevStandingResult = null;
        for (int i = 0; i < standingResults.size(); i++) {
            if (standingResults.get(i).getPoints() == prevPoints) {
                standingResults.get(i).setPlace(prevStandingResult.getPlace());
            }
            else {
                standingResults.get(i).setPlace(i+1);
                prevPoints =  standingResults.get(i).getPoints();

            }
            prevStandingResult = standingResults.get(i);
        }
        return standingResults;
    }
}
