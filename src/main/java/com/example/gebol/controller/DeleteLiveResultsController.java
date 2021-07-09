package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.LiveResultRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.persistent.Discipline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/delete-live-results")
public class DeleteLiveResultsController {

    private LiveResultRepository liveResultRepository;
    private DisciplineRepository disciplineRepository;

    @Autowired
    public DeleteLiveResultsController(
            LiveResultRepository liveResultRepository,
            DisciplineRepository disciplineRepository) {
        this.liveResultRepository = liveResultRepository;
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping
    public String deleteLiveResults(Model model) {
        log.info("deleteLiveResults");
        model.addAttribute("discipline", new Discipline());

        List<Discipline> allDisciplines = liveResultRepository.findAllDisciplineIds()
                .stream()
                .map(disciplineId -> disciplineRepository.findById(disciplineId))
                .collect(Collectors.toList());

        model.addAttribute("allDisciplines", allDisciplines);
        return "delete-live-results";
    }

    @PostMapping
    public String processDelete(Model model, @ModelAttribute Discipline discipline, Errors errors) {
        log.info("processDelete");
        if (errors.hasErrors()) {
            log.info("Errors" + errors.getAllErrors());

            List<Discipline> allDisciplines = liveResultRepository.findAllDisciplineIds()
                    .stream()
                    .map(disciplineId -> disciplineRepository.findById(disciplineId))
                    .collect(Collectors.toList());

            model.addAttribute("allDisciplines", allDisciplines);
            return "delete-live-results";
        }

        liveResultRepository.deleteByDisciplineId(discipline.getId());
        return "redirect:/admin/live-results";
    }
}
