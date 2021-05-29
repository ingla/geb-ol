package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.ParticipantRepository;
import com.example.gebol.data.ResultRepository;
import com.example.gebol.model.AddResultUserInput;
import com.example.gebol.model.Discipline;
import com.example.gebol.model.Participant;
import com.example.gebol.model.Result;
import com.example.gebol.model.ResultListCreation;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class AddResultController {

    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;

    @Autowired
    public AddResultController(ResultRepository resultRepository,
                               ParticipantRepository participantRepository,
                               DisciplineRepository disciplineRepository) {
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping("/init-add-result")
    public String addResult(Model model) {
        log.info("addResult");
        Iterable<Discipline> disciplinesWithoutResult = disciplineRepository.findAll()
                .stream()
                .filter(discipline -> !resultRepository.hasDisciplineId(discipline.getId()))
                .collect(Collectors.toList());

        model.addAttribute("allDisciplines", disciplinesWithoutResult);
        model.addAttribute("addResultUserInput", new AddResultUserInput());
        return "init-add-result";
    }

    @PostMapping("/init-add-result")
    public String processAddResult(Model model, AddResultUserInput addResultUserInput) {
        Iterable<Participant> allParticipants = participantRepository.findAll();
        model.addAttribute("allParticipants", allParticipants);

        ResultListCreation resultList = new ResultListCreation();
        for (int i = 0; i < addResultUserInput.getParticipantCount(); i++) {
            Result r = new Result();
            r.setPlace(i + 1);
            r.setDisciplineId(addResultUserInput.getDisciplineId());
            resultList.addResult(r);
        }

        model.addAttribute("resultListCreation", resultList);
        return "add-results-for-discipline";
    }

    @PostMapping("/save-results")
    public String processAddResult(Model model, ResultListCreation resultListCreation, BindingResult bindingResult) {
        log.info("save-results");
        log.info(resultListCreation.getResults().toString());

        // Validate input - all participants should be unique
        Set<Long> uniqueParticipants = new HashSet<>();
        List<Result> results = resultListCreation.getResults();
        for (Result res : results) {
            uniqueParticipants.add(res.getParticipantId());
        }
        if (uniqueParticipants.size() != results.size()) {
            ObjectError objectError = new ObjectError(
                    "globalError",
                    "Deltagere må være unike"
            );
            bindingResult.addError(objectError);

            Iterable<Participant> allParticipants = participantRepository.findAll();
            model.addAttribute("allParticipants", allParticipants);

            return "add-results-for-discipline";
        }

        resultRepository.saveAll(resultListCreation.getResults());
        return "redirect:/admin/results";
    }
}
