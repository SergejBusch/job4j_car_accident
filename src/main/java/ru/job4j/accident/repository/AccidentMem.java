package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private static int id = 1;
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        this.create(new Accident(id, "a", "desc", "a1"));
        this.create(new Accident(id, "b", "desc", "b2"));
        this.create(new Accident(id, "c", "desc", "c3"));
    }

    public HashMap<Integer, Accident> getAccidents() {
        return accidents;
    }

    public void create(Accident accident) {
        accident.setId(id);
        accidents.put(id++, accident);
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident getById(int id) {
        return accidents.get(id);
    }
}
