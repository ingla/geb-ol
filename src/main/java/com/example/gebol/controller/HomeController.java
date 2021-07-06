package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.LiveResultRepository;
import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.persistent.Discipline;
import com.example.gebol.model.persistent.Participant;
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
    private final LiveResultRepository liveResultRepository;

    public HomeController(
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

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
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

    @GetMapping("/admin/live-results")
    public String adminLiveResults(Model model) {
        List<Discipline> list = liveResultRepository.findAllDisciplineIds()
                .stream()
                .map(disciplineRepository::findById)
                .collect(Collectors.toList());
        model.addAttribute("disciplineList", list);
        return "admin-live-results";
    }
}
