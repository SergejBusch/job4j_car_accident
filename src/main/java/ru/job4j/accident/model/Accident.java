package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
}
