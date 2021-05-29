package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.Discipline;
import com.example.gebol.model.Participant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final DisciplineRepository disciplineRepository;
    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;

    public HomeController(
            DisciplineRepository disciplineRepository,
            ResultRepository resultRepository,
            ParticipantRepository participantRepository
    ) {
        this.disciplineRepository = disciplineRepository;
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/admin/disciplines")
    public String adminDisciplines(Model model) {
        Iterable<Discipline> list = disciplineRepository.findAll();
        model.addAttribute("disciplineList", list);
        return "admin-disciplines";
    }

    @GetMapping("/admin/participants")
    public String adminParticipants(Model model) {
        List<Participant> list = participantRepository.findAll();
        model.addAttribute("participantList", list);
        return "admin-participants";
    }

    @GetMapping("/admin/results")
    public String adminResults(Model model) {
        List<Discipline> list = resultRepository.findAllDisciplineIds()
                .stream()
                .map(disciplineId -> disciplineRepository.findById(disciplineId))
                .collect(Collectors.toList());
        model.addAttribute("disciplineList", list);
        return "admin-results";
    }
}
