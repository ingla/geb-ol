package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.LiveResultRepository;
import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.DisciplineLiveResult;
import com.example.gebol.model.persistent.Discipline;
import com.example.gebol.model.DisciplineResult;
import com.example.gebol.model.persistent.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/schedule")
public class DisciplinesController {

    private final DisciplineRepository disciplineRepository;
    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final LiveResultRepository liveResultRepository;

    public DisciplinesController(
            DisciplineRepository disciplineRepository,
            ResultRepository resultRepository,
            ParticipantRepository participantRepository,
            LiveResultRepository liveResultRepository
    ) {
        this.disciplineRepository = disciplineRepository;
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
        this.liveResultRepository = liveResultRepository;
    }

    @GetMapping
    public String getProgram(Model model) {
        log.info("getProgram");
        List<Discipline> list = disciplineRepository.findAllSortedByDate();
        HashMap<LocalDateTime, List<Discipline>> program = new HashMap<LocalDateTime, List<Discipline>>();
        TreeMap<LocalDateTime, String> weekdays = new TreeMap<LocalDateTime, String>();
        String dow;
        for (Discipline d : list) {
            List<Discipline> l1;
            dow = d.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("no"));
            LocalDateTime key = d.getDate().truncatedTo(ChronoUnit.DAYS);
            weekdays.put(key, dow);

            if (program.containsKey(key)) {
                l1 = program.get(key);
            } else {
                l1 = new ArrayList<>();
            }
            l1.add(d);
            program.put(key, l1);
        }
        model.addAttribute("program", program);
        model.addAttribute( "weekdays", weekdays);
        return "schedule";
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

        // Get list of LiveResults for the discipline
        List<DisciplineLiveResult> liveResults = liveResultRepository.findByDisciplineId(d.getId())
                .stream()
                .map((res) -> new DisciplineLiveResult( // Map to DisciplineLiveResult objects
                        getNameOrBlank(res.getParticipantId()),
                        res.getLevel(),
                        res.getPlace(),
                        res.getScore()
                )).collect(Collectors.toList());

        model.addAttribute("liveResults", liveResults);
        log.info(liveResults.toString());
        return "discipline-details";
    }

    private String getNameOrBlank(Long id) {
        return participantRepository.hasParticipant(id) ?
                participantRepository.getNameById(id) : "";
    }
}
