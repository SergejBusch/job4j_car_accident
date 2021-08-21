package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentService {
    private AccidentMem accidentMem;

    @Autowired
    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public List<Accident> getAllAccidents() {
        return new ArrayList<>(accidentMem.getAccidents());
    }

    public Accident getAccident(int id) {
        return accidentMem.getById(id);
    }

    public void create(Accident accident) {
        accidentMem.create(accident);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }
}
