package org.example.fxdemo.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.model.ItemAction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ItemService {

    private static final ObservableList<Item> items = FXCollections.observableList(new ArrayList<>(List.of(
            new Item(1, "Item 1", "Editable and deletable", new ItemAction(true, true)),
            new Item(2, "Item 2", "Editable only", new ItemAction(true, false)),
            new Item(3, "Item 3", "Deleteable only", new ItemAction(false, true)),
            new Item(4, "Item 4", "Readonly", new ItemAction(false, false))
    )));

    public static ObservableList<Item> getItems() {
        return items;
    }

    public static void updateItem(Item newItem) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getId() == newItem.getId()) {
                items.set(i, newItem);
                return;
            }
        }
        addItem(newItem);
    }

    public static void deleteItem(int id) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getId() == id) {
                items.remove(i);
                return; // Exit after removing the item
            }
        }
    }

    public static Item createItem() {
        int newIndex = items.stream().max(Comparator.comparingInt(Item::getId))
                .map(item -> item.getId() + 1).orElse(1);
        return new Item(newIndex, "New Item", "", new ItemAction(true, true));
    }

    public static void addItem(Item item) {
        items.add(item);
    }
}
