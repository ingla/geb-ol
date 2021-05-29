package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.model.Discipline;
import com.example.gebol.model.Participant;
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

@Slf4j
@Controller
@RequestMapping("/delete-participant")
public class DeleteParticipantController {

    private ParticipantRepository participantRepository;

    @Autowired
    public DeleteParticipantController(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @GetMapping
    public String deleteParticipant(Model model) {
        model.addAttribute("participant", new Participant());

        List<Participant> allParticipants = participantRepository.findAll();
        model.addAttribute("allParticipants", allParticipants);
        return "delete-participant";
    }

    @PostMapping
    public String processDelete(Model model, @ModelAttribute Participant participant, Errors errors) {
        if (errors.hasErrors()) {
            log.info("Errors" + errors.getAllErrors());
            List<Participant> allParticipants = participantRepository.findAll();
            model.addAttribute("allDisciplines", allParticipants);
            return "delete-participant";
        }

        participantRepository.deleteById(participant.getId());
        return "redirect:/admin/participants";
    }
}
