package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.Participant;
import com.example.gebol.model.ParticipantResult;
import com.example.gebol.model.Result;
import com.example.gebol.model.StandingResult;
import com.example.gebol.service.PointCalculationService;
import com.example.gebol.service.StandingResultsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/participants")
public class ParticipantsController {

    private final ParticipantRepository participantRepository;
    private final ResultRepository resultRepository;
    private final DisciplineRepository disciplineRepository;
    private final PointCalculationService pointCalculationService;
    private final StandingResultsService standingResultsService;

    public ParticipantsController(
            ParticipantRepository participantRepository,
            ResultRepository resultRepository,
            DisciplineRepository disciplineRepository,
            PointCalculationService pointCalculationService,
            StandingResultsService standingResultsService
    ) {
        this.participantRepository = participantRepository;
        this.resultRepository = resultRepository;
        this.disciplineRepository = disciplineRepository;
        this.pointCalculationService = pointCalculationService;
        this.standingResultsService = standingResultsService;
    }

    @GetMapping
    public String getParticipants(Model model) {
        log.info("getParticipants");

        Iterable<Participant> list = participantRepository.findAll();
        model.addAttribute("participantList", list);
        return "participants";
    }

    @GetMapping("/{id}")
    public String getParticipantItem(Model model, @PathVariable Long id) {
        Participant p = participantRepository.findOne(id);
        model.addAttribute("participant", p);

        List<Result> results = resultRepository.findByParticipantId(id);

        List<ParticipantResult> myResults = results
                .stream()
                .map((res) ->
                        new ParticipantResult(
                                disciplineRepository.getNameById(res.getDisciplineId()),
                                res.getPlace()))
                .collect(Collectors.toList());
        model.addAttribute("participantResults", myResults);

        // Generate place and points for overall standings
        List<StandingResult> standingResults = standingResultsService.getStandingResults();

        for (StandingResult s : standingResults) {
            if (s.getParticipantId() == p.getId()) {
                model.addAttribute("standingResult", s);
            }
        }


        return "participant-details";
    }
}
