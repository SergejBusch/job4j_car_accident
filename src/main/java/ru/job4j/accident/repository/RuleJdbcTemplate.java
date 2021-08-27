package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.Rule;

import java.util.*;

//@Repository
public class RuleJdbcTemplate {
    private final JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Rule> getAll() {
        return jdbc.query("select id, name from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Rule getById(int id) {
        return jdbc.queryForObject(
                "select name from rule where id = ?",
                (rsl, row) -> {
                    Rule rule = new Rule();
                    rule.setId(id);
                    rule.setName(rsl.getString("name"));
                    return rule;
                }, id
        );
    }

    public Set<Rule> getRulesByAccidentId(int id) {
        Set<Rule> rules = new HashSet<>();
        List<Integer> ids = getRuleIds(id);
        for (int i : ids) {
            Rule rule = getById(i);
            rule.setId(i);
            rules.add(rule);
        }
        return rules;
    }

    public Set<Rule> getSetOfRulesByIds(String... ids) {
        Set<Rule> rules = new HashSet<>();
        for (String i : ids) {
            Rule rule = getById(Integer.parseInt(i));
            rules.add(rule);
        }
        return rules;
    }

    private List<Integer> getRuleIds(int accidentId) {
        return jdbc.query(
                "select rules_id from accident_rule where accident_id = ?",
                (rsl, row) -> rsl.getInt("rule_id"), accidentId
        );

    }
}
