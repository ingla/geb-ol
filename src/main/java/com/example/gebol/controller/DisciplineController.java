package com.example.gebol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {

    @GetMapping
    public String addDiscipline() {
        return "add-discipline";
    }
}
