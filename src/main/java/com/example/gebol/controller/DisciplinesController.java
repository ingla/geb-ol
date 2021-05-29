package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.Discipline;
import com.example.gebol.model.DisciplineResult;
import com.example.gebol.model.Participant;
import com.example.gebol.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/disciplines")
public class DisciplinesController {

    private final DisciplineRepository disciplineRepository;
    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;

    public DisciplinesController(
            DisciplineRepository disciplineRepository,
            ResultRepository resultRepository,
            ParticipantRepository participantRepository
    ) {
        this.disciplineRepository = disciplineRepository;
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
    }

    @GetMapping
    public String getDisciplineList(Model model) {
        log.info("getDisciplineList");
        Iterable<Discipline> list = disciplineRepository.findAll();
        System.out.println(list);
        model.addAttribute("disciplineList", list);
        return "disciplines";
    }

    @GetMapping("/{name}")
    public String getDisciplineItem(Model model, @PathVariable String name) {
        log.info("getDisciplineItem");
        Discipline d = disciplineRepository.findOne(name);
        model.addAttribute("discipline", d);
        String dow = d.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("no"));
        model.addAttribute("weekday", dow);

        // Create list of results
        List<Result> results = resultRepository.findByDisciplineId(d.getId());
        results.sort(Comparator.comparing(Result::getPlace)); // Sort list on place

        // Map result list to DisciplineResult objects (includes participant names).
        List <DisciplineResult> disciplineResults = results
                .stream()
                .map((res) -> new DisciplineResult(
                        participantRepository.getNameById(res.getParticipantId()),
                        res.getParticipantId(),
                        res.getPlace()))
                .collect(Collectors.toList());

        model.addAttribute("disciplineResults", disciplineResults);

        return "discipline-details";
    }
}
