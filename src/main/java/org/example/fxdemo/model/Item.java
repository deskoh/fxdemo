package org.example.fxdemo.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Item implements Cloneable {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private ItemAction action;
    private int maxValue = 10;
    private int value = 5;
    private Category category = Category.Category1;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @RequiredArgsConstructor
    public enum Category {
        Category1("Cat 1", 1),
        Category2("Cat 2", 2),
        Category3("Cat 3", 3),
        Category4("Cat 4", 4);

        private final String category;
        private final int value;

        @Override
        public String toString() {
            return this.category;
        }
    }
}
