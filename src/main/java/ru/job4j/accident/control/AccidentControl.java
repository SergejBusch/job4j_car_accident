package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam int id) {
        var accident = service.getAccident(id);
        model.addAttribute("accident", accident);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        service.create(accident);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        service.update(accident);
        return "redirect:/";
    }
}
