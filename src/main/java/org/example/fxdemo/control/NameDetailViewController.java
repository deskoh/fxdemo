package org.example.fxdemo.control;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.fxdemo.MainApplication;
import org.example.fxdemo.NavigationController;
import org.example.fxdemo.NavigationViewController;
import org.example.fxdemo.Util;
import org.example.fxdemo.model.Item;

@RequiredArgsConstructor
public class NameDetailViewController extends NavigationViewController {
    @Getter
    private final Item item;

    @FXML
    TextField nameField;

    @FXML
    public void initialize() {
        Util.bindPropertyToSetter(nameField.textProperty(), item::setName, item.getName());
    }

    public void goToDescriptionPage() {
        this.navigationController.pushView(NavigationController.View.DESCRIPTION, this.item);
    }

    public void handleCancel() {
        MainApplication.closeItemForm();
    }
}
