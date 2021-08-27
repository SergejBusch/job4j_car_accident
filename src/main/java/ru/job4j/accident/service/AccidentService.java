package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.repository.AccidentHibernate;

@Service
public class AccidentService {
    private final AccidentHibernate accidentHibernate;

    @Autowired
    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }
}
