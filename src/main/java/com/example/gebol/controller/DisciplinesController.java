package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.model.Discipline;
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
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/disciplines")
public class DisciplinesController {

    private final DisciplineRepository disciplineRepository;

    public DisciplinesController(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
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
        System.out.println("Found d" + d);
        model.addAttribute("discipline", d);
        String dow = d.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("no"));
        model.addAttribute("weekday", dow);
        return "discipline-details";
    }
}
