package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Set;

@Controller
public class AccidentControl {
    private final AccidentHibernate accidentHibernate;

    @Autowired
    public AccidentControl(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    @GetMapping("/create")
    public String create(Model model) {
        getTypesAndRules(model);
        return "create";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam int id) {
        getTypesAndRules(model);
        var accident = accidentHibernate.getAccidentById(id);
        model.addAttribute("accident", accident);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        Set<Rule> rules = accidentHibernate.getRulesByIds(req.getParameterValues("rIds"));
        accident.setRules(rules);
        accident.setType(accidentHibernate.getTypeById(accident.getType().getId()));
        accidentHibernate.save(accident);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest req) {
        var rules = accidentHibernate.getRulesByIds(req.getParameterValues("rIds"));
        accident.setRules(rules);
        accident.setType(accidentHibernate.getTypeById(accident.getType().getId()));
        accidentHibernate.update(accident);
        return "redirect:/";
    }

    private void getTypesAndRules(Model model) {
        Collection<AccidentType> types = accidentHibernate.getAllTypes();
        model.addAttribute("types", types);

        Collection<Rule> rules = accidentHibernate.getAllRules();
        model.addAttribute("rules", rules);
    }
}
