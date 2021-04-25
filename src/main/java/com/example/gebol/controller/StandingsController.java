package com.example.gebol.controller;

import com.example.gebol.model.Discipline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/standings")
public class StandingsController {
    @GetMapping
    public String getStandings(Model model) {
        log.info("getStandings");

        //Iterable<Discipline> list = disciplineRepository.findAll();
        //log.info(list.toString());

        //model.addAttribute("disciplineList", list);
        return "standings";
    }
}
