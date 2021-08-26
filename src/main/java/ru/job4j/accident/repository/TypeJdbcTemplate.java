package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
public class TypeJdbcTemplate {
    private final JdbcTemplate jdbc;

    public TypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<AccidentType> getAll() {
        return jdbc.query("select id, name from type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public AccidentType getById(int id) {
        return jdbc.queryForObject(
                "select name from type where id = ?",
                (rsl, row) -> {
                    AccidentType newType = new AccidentType();
                    newType.setId(id);
                    newType.setName(rsl.getString("name"));
                    return newType;
                }, id);
    }
}
