package org.example.fxdemo.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;
import org.example.fxdemo.SceneUtil;
import org.example.fxdemo.event.ItemEvent;

public class ItemListSection<T> extends HBox {
    @FXML
    private ItemListControl<T> items;

    @Getter
    @Setter
    private EventHandler<ActionEvent> onAdd;

    public ItemListSection() {
        try {
            FXMLLoader loader = new FXMLLoader(SceneUtil.class.getResource("control/ItemListSection.fxml"));
            loader.setControllerFactory(param -> this);
            loader.setRoot(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ItemListControl<T> getItemList() {
        return this.items;
    }

    @FXML
    private void onAdd(ActionEvent event) {
        if (onAdd != null) {
            onAdd.handle(event);
        }
    }

    public void setOnRemove(EventHandler<ActionEvent> action) {
        this.items.setOnRemove(action);
    }

    public EventHandler<ActionEvent> getOnRemove() {
        return this.items.getOnRemove();
    }

    public void setOnItemClicked(EventHandler<ItemEvent<T>> action) {
        this.items.setOnItemClicked(action);
    }

    public EventHandler<ItemEvent<T>> getOnItemClicked() {
        return this.items.getOnItemClicked();
    }
}
