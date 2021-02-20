package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.model.Discipline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        log.info(list.toString());

        model.addAttribute("disciplineList", list);
        return "disciplines";
    }
}
