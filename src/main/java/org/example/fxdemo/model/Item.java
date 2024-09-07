package org.example.fxdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item implements Cloneable {
    private int id;
    private String name;
    private String description;
    private ItemAction action;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
