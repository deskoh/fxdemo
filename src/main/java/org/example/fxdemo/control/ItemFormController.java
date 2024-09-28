package org.example.fxdemo.control;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import org.example.fxdemo.NavigationController;
import org.example.fxdemo.model.Item;

public class ItemFormController {
    private NavigationController navigationController;

    @Getter
    private final Item item;

    @FXML
    private AnchorPane rootPane;

    public ItemFormController(Item item) {
        this.item = item;
    }

    @FXML
    private void initialize() {
        navigationController = new NavigationController(rootPane);
        this.navigationController.pushView(NavigationController.View.NAME, param -> new NameDetailViewController(this.item));
    }
}
