package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentHibernate;

@Controller
public class IndexControl {

    private AccidentHibernate accidentHibernate;

    @Autowired
    public IndexControl(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentHibernate.getAllAccidents());
        return "index";
    }
}
