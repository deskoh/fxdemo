package org.example.fxdemo.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.model.ItemAction;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private static ObservableList<Item> items = FXCollections.observableList(new ArrayList<Item>(List.of(
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
}
