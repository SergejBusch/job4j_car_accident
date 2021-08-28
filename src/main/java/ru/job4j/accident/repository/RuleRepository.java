package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public interface RuleRepository extends CrudRepository<Rule, Integer> {

    default Set<Rule> findRulesByIds(String[] rIds) {
        Set<Rule> rules = new HashSet<>();
        for (String id : rIds) {
            var rule = this.findById(Integer.parseInt(id));
            rule.ifPresent(rules::add);
        }
        return rules;
    }
}
