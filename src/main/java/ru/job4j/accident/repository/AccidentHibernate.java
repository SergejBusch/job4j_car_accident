package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public void save(Accident accident) {
        try (Session session = sf.openSession()) {
            var transaction = session.beginTransaction();
            session.save(accident);
            transaction.commit();
        }
    }

    public Collection<Accident> getAllAccidents() {
        var list = new ArrayList<Accident>();
        try (Session session = sf.openSession()) {
            var rsl = session
                    .createQuery("select distinct a from Accident a " +
                            "join fetch a.type " +
                            "left join fetch a.rules")
                    .list();
            list.addAll(rsl);
        }
        return list;
    }

    public Accident getAccidentById(int id) {
        try (Session session = sf.openSession()) {
            return (Accident) session
                    .createQuery("select distinct a from Accident a " +
                            "join fetch a.type " +
                            "full join fetch a.rules " +
                            "where a.id =:Id")
                    .setParameter("Id", id)
                    .list().stream().findFirst().orElseGet(null);
        }
    }

    public Set<Rule> getRulesByIds(String[] rIds) {
        Set<Rule> rules = new HashSet<>();
        try (var session = sf.openSession()) {
            for (String id : rIds) {
                var rule = getRuleById(Integer.parseInt(id));
                rules.add(rule);
            }
        }
        return rules;
    }

    public AccidentType getTypeById(int id) {
        try (var session = sf.openSession()) {
            var type = session.load(AccidentType.class, id);
            type.setId(id);
            return type;
        }
    }

    public Collection<AccidentType> getAllTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType ")
                    .list();
        }
    }

    public Collection<Rule> getAllRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule ")
                    .list();
        }
    }

    public void update(Accident accident) {
        try (Session session = sf.openSession()) {
//            session.update(accident);
            var transaction = session.beginTransaction();
            session.update(accident);
            transaction.commit();
        }
    }

    public Rule getRuleById(int id) {
        try (var session = sf.openSession()) {
            var rule = session.load(Rule.class, id);
            rule.setId(id);
            return rule;
        }
    }
}
