package org.example.fxdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DemoModel<T> {
    private String Category;
    private String Type;
    private List<T> Items;
}
