package org.example.fxdemo.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.example.fxdemo.MainApplication;
import org.example.fxdemo.NavigationController;
import org.example.fxdemo.NavigationViewController;
import org.example.fxdemo.Util;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.service.ItemService;

@RequiredArgsConstructor
public class DescriptionDetailViewController extends NavigationViewController {
    private final Item item;

    @FXML
    TextField descriptionField;

    @FXML
    Button btnDelete;

    @FXML
    public void initialize() {
        Util.bindPropertyToSetter(descriptionField.textProperty(), item::setDescription, item.getDescription());
        btnDelete.setDisable(!item.getAction().isDeleteAllowed());
    }

    public void handleBack() {
        this.navigationController.popView();
    }

    public void handleNext() {
        this.navigationController.pushView(NavigationController.View.COMBOBOX, param -> new ComboBoxDemoController(item));
    }

    public void handleSave() {
        ItemService.updateItem(this.item);
        MainApplication.closeItemForm();
    }

    public void handleCancel() {
        MainApplication.closeItemForm();
    }

    public void handleDelete() {
        ItemService.deleteItem(this.item.getId());
        MainApplication.closeItemForm();
    }
}
