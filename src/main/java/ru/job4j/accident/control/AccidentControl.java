package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepository;
import ru.job4j.accident.repository.TypeRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Set;

@Controller
public class AccidentControl {
    private final AccidentRepository accidents;
    private final RuleRepository ruleRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public AccidentControl(AccidentRepository accidents, RuleRepository ruleRepository, TypeRepository typeRepository) {
        this.accidents = accidents;
        this.ruleRepository = ruleRepository;
        this.typeRepository = typeRepository;
    }

    @GetMapping("/create")
    public String create(Model model) {
        getTypesAndRules(model);
        return "create";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam int id) {
        getTypesAndRules(model);
        var accident = accidents.findAccidentById(id);
        model.addAttribute("accident", accident);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        Set<Rule> rules = ruleRepository.findRulesByIds(req.getParameterValues("rIds"));
        accident.setRules(rules);
        accident.setType(typeRepository.findById(accident.getType().getId()).orElseGet(null));
        accidents.save(accident);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest req) {
        var rules = ruleRepository.findRulesByIds(req.getParameterValues("rIds"));
        accident.setRules(rules);
        accident.setType(typeRepository.findById(accident.getType().getId()).orElse(null));
        accidents.save(accident);
        return "redirect:/";
    }

    private void getTypesAndRules(Model model) {
        Collection<AccidentType> types = (Collection<AccidentType>) typeRepository.findAll();
        model.addAttribute("types", types);

        Collection<Rule> rules = (Collection<Rule>) ruleRepository.findAll();
        model.addAttribute("rules", rules);
    }
}
