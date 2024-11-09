package org.example.fxdemo.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;
import org.example.fxdemo.event.ItemEvent;

public class ItemListControl<T> extends HBox {

    // Property to hold the ObservableList of items
    private final ObjectProperty<ObservableList<T>> items = new SimpleObjectProperty<>(FXCollections.observableArrayList());

    @Getter
    @Setter
    private EventHandler<ActionEvent> onRemove;

    @Getter
    @Setter
    private EventHandler<ItemEvent<T>> onItemClicked;

    public ItemListControl() {
        // Bind listener to items to update cards when the list changes
        items.addListener((obs, oldList, newList) -> {
            if (oldList != null) {
                oldList.removeListener(listChangeListener);
            }
            if (newList != null) {
                newList.addListener(listChangeListener);
                updateCards(newList);
            }
        });
    }

    // Listener to update the cards when items are added or removed
    private final ListChangeListener<T> listChangeListener = change -> {
        while (change.next()) {
            if (change.wasReplaced()) {
                for (int i = change.getFrom(); i < change.getTo(); ++i) {
                    getChildren().set(i, createCard(change.getList().get(i)));
                }
            } else if (change.wasAdded()) {
                for (int i = change.getFrom(); i < change.getTo(); ++i) {
                    getChildren().add(i, createCard(change.getList().get(i)));
                }
            } else if (change.wasRemoved()) {
                updateCards((ObservableList<T>) change.getList());
                break;
            }
        }
    };

    // Updates the displayed cards based on the items in the list
    private void updateCards(ObservableList<T> items) {
        getChildren().clear(); // Clear existing cards
        for (T item : items) {
            getChildren().add(createCard(item)); // Add a new card for each item
        }
    }

    private HBox createCard(T item) {
        var card = new HBox();
        Label label = new Label(item.toString());
        var removeButton = new Button("X");

        // Action to remove the item from the list
        removeButton.setOnAction(e -> {
            items.get().remove(item);
            if (this.onRemove != null) {
                this.onRemove.handle(e);
            }
        });

        card.getChildren().addAll(label, removeButton);
        card.getStyleClass().add("card");
        card.setOnMouseClicked(e -> {
            var event = new ItemEvent<>(e.getSource(), e.getTarget(), item);
            this.fireEvent(e);
            if (this.onItemClicked != null) {
                onItemClicked.handle(event);
            }
        });
        return card;
    }

    // Getter and setter for the items property
    public ObservableList<T> getItems() {
        return items.get();
    }

    public void setItems(ObservableList<T> items) {
        this.items.set(items);
    }

    public ObjectProperty<ObservableList<T>> itemsProperty() {
        return items;
    }
}
