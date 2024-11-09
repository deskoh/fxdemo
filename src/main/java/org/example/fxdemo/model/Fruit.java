package org.example.fxdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fruit {
    private int id;
    private String name;

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
