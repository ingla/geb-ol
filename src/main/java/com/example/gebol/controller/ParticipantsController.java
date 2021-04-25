package com.example.gebol.controller;

import com.example.gebol.model.Discipline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/participants")
public class ParticipantsController {
    @GetMapping
    public String getParticipants(Model model) {
        log.info("getParticipants");

        //Iterable<Discipline> list = disciplineRepository.findAll();
        //log.info(list.toString());

        //model.addAttribute("disciplineList", list);
        return "participants";
    }
}
