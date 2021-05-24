package com.example.gebol.controller;

import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.model.Discipline;
import com.example.gebol.model.Participant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/participants")
public class ParticipantsController {

    private final ParticipantRepository participantRepository;

    public ParticipantsController(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @GetMapping
    public String getParticipants(Model model) {
        log.info("getParticipants");

        Iterable<Participant> list = participantRepository.findAll();
        model.addAttribute("participantList", list);
        return "participants";
    }

    @GetMapping("/{id}")
    public String getParticipantItem(Model model, @PathVariable Long id) {
        Participant p = participantRepository.findOne(id);
        model.addAttribute("participant", p);
        return "participant-details";
    }
}
