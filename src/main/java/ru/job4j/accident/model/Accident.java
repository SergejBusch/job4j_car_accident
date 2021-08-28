package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    private AccidentType type;

    @ManyToMany(cascade = {CascadeType.MERGE})
    private Set<Rule> rules = new HashSet<>();
}
