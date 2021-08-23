package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;
    private Set<Rule> rules;
}
