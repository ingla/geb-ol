package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
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

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/add-discipline")
public class CreateDisciplineController {

    private DisciplineRepository disciplineRepository;

    @Autowired
    public CreateDisciplineController(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping
    public String addDiscipline(Model model) {
        log.info("addDiscipline");
        model.addAttribute("discipline", new Discipline());
        return "add-discipline";
    }

    @PostMapping
    public String processDiscipline(@Valid @ModelAttribute Discipline discipline, Errors errors) {
        log.info("processDiscipline in controller: new discpline " +
                discipline.toString() + " was added");

        if (errors.hasErrors()) {
            return "add-discipline";
        }

        disciplineRepository.save(discipline);
        return "redirect:/";
    }
}
