package org.example.fxdemo.control;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Getter;
import org.example.fxdemo.MainApplication;
import org.example.fxdemo.NavigationController;
import org.example.fxdemo.NavigationViewController;
import org.example.fxdemo.model.Item;

public class NameDetailViewController extends NavigationViewController {
    @Getter
    private final Item item;

    @FXML
    TextField nameField;

    public NameDetailViewController(Item item) {
        this.item = item;
    }

    @FXML
    public void initialize() {
        nameField.setText(this.item.getName());
        nameField.textProperty().addListener((observable, oldValue, newValue) -> this.item.setName(newValue));
    }

    public void goToDescriptionPage() {
        this.navigationController.pushView(NavigationController.View.DESCRIPTION, param -> new DescriptionDetailViewController(this.item));
    }

    public void handleCancel() {
        MainApplication.closeItemForm();
    }
}
