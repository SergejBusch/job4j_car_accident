package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentRepository;

@Controller
public class IndexControl {

    private AccidentRepository accidents;

    public IndexControl() {
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidents.findAllAccident());
        return "index";
    }

    @Autowired
    public void setAccidents(AccidentRepository accidents) {
        this.accidents = accidents;
    }
}
