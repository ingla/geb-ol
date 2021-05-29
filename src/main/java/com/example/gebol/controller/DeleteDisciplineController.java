package com.example.gebol.controller;

import com.example.gebol.data.DisciplineRepository;
import com.example.gebol.data.ResultRepository;
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
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/delete-discipline")
public class DeleteDisciplineController {

    private DisciplineRepository disciplineRepository;
    private ResultRepository resultRepository;

    @Autowired
    public DeleteDisciplineController(
            DisciplineRepository disciplineRepository,
            ResultRepository resultRepository
    ) {
        this.disciplineRepository = disciplineRepository;
        this.resultRepository = resultRepository;
    }

    @GetMapping
    public String deleteDiscipline(Model model) {
        model.addAttribute("discipline", new Discipline());

        List<Discipline> allDisciplines = disciplineRepository.findAll();
        model.addAttribute("allDisciplines", allDisciplines);
        return "delete-discipline";
    }

    @PostMapping
    public String processDelete(Model model, @ModelAttribute Discipline discipline, Errors errors) {
        if (errors.hasErrors()) {
            log.info("Errors" + errors.getAllErrors());
            List<Discipline> allDisciplines = disciplineRepository.findAll();
            model.addAttribute("allDisciplines", allDisciplines);
            return "delete-discipline";
        }

        disciplineRepository.deleteById(discipline.getId());
        resultRepository.deleteByDisciplineId(discipline.getId());
        return "redirect:/admin/disciplines";
    }
}
