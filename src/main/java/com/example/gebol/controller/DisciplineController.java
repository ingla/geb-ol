package com.example.gebol.controller;

import com.example.gebol.model.Discipline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/discipline")
public class DisciplineController {

    @GetMapping
    public String addDiscipline(Model model) {
        log.info("addDiscipline");
        model.addAttribute("disciplineObject", new Discipline());
        return "add-discipline";
    }

    @PostMapping
    public String processDiscipline(@ModelAttribute Discipline discipline) {
        log.info("processDiscipline in controller: new discpline " +
                discipline.toString() + " was added");
        return "redirect:/";
    }
}
