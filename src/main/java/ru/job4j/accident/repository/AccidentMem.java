package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final AtomicInteger id = new AtomicInteger(1);
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, Rule> rules = Map.of(
            1, Rule.of(1, "Rule. 1"),
            2, Rule.of(2, "Rule. 2"),
            3, Rule.of(3, "Rule. 3"));

    private final Map<Integer, AccidentType> types = Map.of(
            1, AccidentType.of(1, "Two cars"),
            2, AccidentType.of(2, "Car and man"),
            3, AccidentType.of(3, "Car and bicycle"));

    public AccidentMem() {
        this.create(new Accident(id.get(), "a", "desc", "a1",
                getTypeById(1),
                getSetOfRulesByIds("1", "2")));

        this.create(new Accident(id.get(), "b", "desc", "b2",
                getTypeById(2),
                getSetOfRulesByIds("1", "3")));

        this.create(new Accident(id.get(), "c", "desc", "c3",
                getTypeById(3),
                getSetOfRulesByIds("2")));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void create(Accident accident) {
        accident.setId(id.get());
        accidents.put(id.getAndIncrement(), accident);
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident getById(int id) {
        return accidents.get(id);
    }

    public Rule getRuleById(int id) {
        return rules.get(id);
    }

    public Set<Rule> getSetOfRulesByIds(String... ids) {
        Set<Rule> rls = new HashSet<>();
        for (String id : ids) {
            rls.add(getRuleById(Integer.parseInt(id)));
        }
        return rls;
    }

    public AccidentType getTypeById(int id) {
        return types.get(id);
    }
}
