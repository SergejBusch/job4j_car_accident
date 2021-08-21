package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final AtomicInteger id = new AtomicInteger(1);
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        this.create(new Accident(id.get(), "a", "desc", "a1"));
        this.create(new Accident(id.get(), "b", "desc", "b2"));
        this.create(new Accident(id.get(), "c", "desc", "c3"));
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
}
