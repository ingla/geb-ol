package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.Discipline;
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
@RequestMapping("/delete-results")
public class DeleteResultsController {

    private ResultRepository resultRepository;
    private DisciplineRepository disciplineRepository;

    @Autowired
    public DeleteResultsController(
            ResultRepository resultRepository,
            DisciplineRepository disciplineRepository) {
        this.resultRepository = resultRepository;
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping
    public String deleteResults(Model model) {
        log.info("deleteResults");
        model.addAttribute("discipline", new Discipline());

        List<Discipline> allDisciplines = resultRepository.findAllDisciplineIds()
                .stream()
                .map(disciplineId -> disciplineRepository.findById(disciplineId))
                .collect(Collectors.toList());

        model.addAttribute("allDisciplines", allDisciplines);
        return "delete-results";
    }

    @PostMapping
    public String processDelete(Model model, @ModelAttribute Discipline discipline, Errors errors) {
        log.info("processDelete");
        if (errors.hasErrors()) {
            log.info("Errors" + errors.getAllErrors());

            List<Discipline> allDisciplines = resultRepository.findAllDisciplineIds()
                    .stream()
                    .map(disciplineId -> disciplineRepository.findById(disciplineId))
                    .collect(Collectors.toList());

            model.addAttribute("allDisciplines", allDisciplines);
            return "delete-results";
        }

        resultRepository.deleteByDisciplineId(discipline.getId());
        return "redirect:/admin/results";
    }
}
