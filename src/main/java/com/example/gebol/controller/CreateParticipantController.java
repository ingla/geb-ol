package com.example.gebol.controller;

import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.model.Participant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/add-participant")
public class CreateParticipantController {

    private ParticipantRepository participantRepository;

    @Autowired
    public CreateParticipantController(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @GetMapping
    public String addParticipant(Model model) {
        model.addAttribute("participant", new Participant());
        return "add-participant";
    }

    @PostMapping
    public String processParticipant(@Valid @ModelAttribute Participant participant, BindingResult result, Errors errors) {
        if (errors.hasErrors()) {
            return "add-participant";
        }

        // Check if participant already exists in database.
        if (participantRepository.hasParticipant(participant.getName())) {
            ObjectError objectError = new ObjectError("globalError", "Deltager allerede lagt til");
            result.addError(objectError);
            return "add-participant";

        } else {
            participantRepository.save(participant);
        }
        return "redirect:/admin/participants";
    }
}
