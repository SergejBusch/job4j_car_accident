package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.RuleJdbcTemplate;
import ru.job4j.accident.repository.TypeJdbcTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class AccidentService {
    private final AccidentJdbcTemplate accidentJdbcTemplate;
    private final TypeJdbcTemplate typeJdbcTemplate;
    private final RuleJdbcTemplate ruleJdbcTemplate;

    @Autowired
    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate,
                           TypeJdbcTemplate typeJdbcTemplate,
                           RuleJdbcTemplate ruleJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
        this.typeJdbcTemplate = typeJdbcTemplate;
        this.ruleJdbcTemplate = ruleJdbcTemplate;
    }

    public List<Accident> getAllAccidents() {
        return new ArrayList<>(accidentJdbcTemplate.getAll());
    }

    public Accident getAccident(int id) {
        return accidentJdbcTemplate.getById(id);
    }

    public void create(Accident accident) {
        accidentJdbcTemplate.save(accident);
    }

    public void update(Accident accident) {
        accidentJdbcTemplate.update(accident);
    }

    public Collection<Rule> getRules() {
        return ruleJdbcTemplate.getAll();
    }

    public Collection<AccidentType> getTypes() {
        return typeJdbcTemplate.getAll();
    }

    public Set<Rule> getRulesByIds(String... ids) {
        return ruleJdbcTemplate.getSetOfRulesByIds(ids);
    }

    public AccidentType getTypeById(int id) {
        return typeJdbcTemplate.getById(id);
    }
}
