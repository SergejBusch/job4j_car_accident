package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        getTypesAndRules(model);
        return "create";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam int id) {
        getTypesAndRules(model);
        var accident = service.getAccident(id);
        model.addAttribute("accident", accident);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        var rules = service.getRulesByIds(req.getParameterValues("rIds"));
        accident.setRules(rules);
        accident.setType(service.getTypeById(accident.getType().getId()));
        service.create(accident);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest req) {
        var rules = service.getRulesByIds(req.getParameterValues("rIds"));
        accident.setRules(rules);
        accident.setType(service.getTypeById(accident.getType().getId()));
        service.update(accident);
        return "redirect:/";
    }

    private void getTypesAndRules(Model model) {
        Collection<AccidentType> types = service.getTypes();
        model.addAttribute("types", types);

        Collection<Rule> rules = service.getRules();
        model.addAttribute("rules", rules);
    }
}
