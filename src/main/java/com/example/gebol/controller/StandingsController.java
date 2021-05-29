package com.example.gebol.controller;

import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.Discipline;
import com.example.gebol.model.Participant;
import com.example.gebol.model.StandingResult;
import com.example.gebol.service.PointCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/standings")
public class StandingsController {

    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final PointCalculationService pointCalculationService;

    public StandingsController(
            ResultRepository resultRepository,
            ParticipantRepository participantRepository,
            PointCalculationService pointCalculationService
    ) {
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
        this.pointCalculationService = pointCalculationService;
    }

    @GetMapping
    public String getStandings(Model model) {
        log.info("getStandings");

        List<Participant> participants = participantRepository.findAll();
        List<StandingResult> standingResults = new ArrayList<>();

        for (Participant p : participants) {
            // Sum up points for all results
            int points = resultRepository.findByParticipantId(p.getId())
                    .stream()
                    .map(result -> result.getPlace())
                    .map(place -> pointCalculationService.getPointsForPlace(place))
                    .reduce(0, (points1, points2) -> points1 + points2);

            StandingResult standingResult = new StandingResult(
                    p.getName(),
                    p.getId(),
                    points
            );
            standingResults.add(standingResult);
        }
        standingResults.sort(Comparator.comparing(StandingResult::getPoints).reversed());
        model.addAttribute("standingResults", standingResults);

        return "standings";
    }
}
