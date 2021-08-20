package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private static int id = 1;
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(1, "a", "desc", "a1"));
        accidents.put(2, new Accident(2, "b", "desc", "b2"));
        accidents.put(3, new Accident(3, "c", "desc", "c3"));
    }

    public HashMap<Integer, Accident> getAccidents() {
        return accidents;
    }
}
