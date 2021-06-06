package com.example.gebol.controller;

import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.StandingResult;
import com.example.gebol.service.PointCalculationService;
import com.example.gebol.service.StandingResultsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/standings")
public class StandingsController {

    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final PointCalculationService pointCalculationService;
    private final StandingResultsService standingResultsService;

    public StandingsController(
            ResultRepository resultRepository,
            ParticipantRepository participantRepository,
            PointCalculationService pointCalculationService,
            StandingResultsService standingResultsService
    ) {
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
        this.pointCalculationService = pointCalculationService;
        this.standingResultsService = standingResultsService;
    }

    @GetMapping
    public String getStandings(Model model) {
        log.info("getStandings");

        List<StandingResult> standingResults = standingResultsService.getStandingResults();
        model.addAttribute("standingResults", standingResults);

        return "standings";
    }
}
