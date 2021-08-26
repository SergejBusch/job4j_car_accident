package ru.job4j.accident.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;
    private RuleJdbcTemplate ruleJdbcTemplate;
    private TypeJdbcTemplate typeJdbcTemplate;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Accident accident) {
        String sqlInsert = "insert into accident (name, text, address, type_id) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(con ->  {
                PreparedStatement ps = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, accident.getName());
                ps.setString(2, accident.getText());
                ps.setString(3, accident.getAddress());
                ps.setInt(4, accident.getType().getId());
                return ps;
        }, keyHolder);
        int id = (int) keyHolder.getKeys().get("id");
        insertRules(id, accident.getRules());
    }

    public Collection<Accident> getAll() {
        return jdbc.query("select id, name, text, address, type_id from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(typeJdbcTemplate.getById(rs.getInt("type_id")));
                    accident.setRules(ruleJdbcTemplate.getRulesByAccidentId(accident.getId()));
                    return accident;
                });
    }

    public void update(Accident accident) {
        jdbc.update(
                "update accident set " +
                        "name = ?, " +
                        "text = ?, " +
                        "address = ?, " +
                        "type_id = ? " +
                        "where id = ?",

                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());

        updateRulesForAccident(accident.getId(), accident.getRules());
    }

    public Accident getById(int id) {
        Accident accident = jdbc.queryForObject(
                "select name, text, address, type_id from accident where id = ?",
                (rsl, row) -> {
                    var newAccident = new Accident(id,
                            rsl.getString("name"),
                            rsl.getString("text"),
                            rsl.getString("address"),
                            typeJdbcTemplate.getById(rsl.getInt("type_id")),
                            ruleJdbcTemplate.getRulesByAccidentId(id));
                    return newAccident;
                }, id);
    return accident;
    }

    private void updateRulesForAccident(int id, Set<Rule> rules) {
        deleteRules(id);
        insertRules(id, rules);
    }

    private void deleteRules(int id) {
        jdbc.update("delete from accident_rule where accident_id = ?",
                id);
    }

    private void insertRules(int id, Set<Rule> rules) {
        for (var rule : rules) {
            jdbc.update("insert into accident_rule(accident_id, rule_id) values (?, ?)",
                    id, rule.getId());
        }
    }

    @Autowired
    public void setRuleJdbcTemplate(RuleJdbcTemplate ruleJdbcTemplate) {
        this.ruleJdbcTemplate = ruleJdbcTemplate;
    }

    @Autowired
    public void setTypeJdbcTemplate(TypeJdbcTemplate typeJdbcTemplate) {
        this.typeJdbcTemplate = typeJdbcTemplate;
    }
}
