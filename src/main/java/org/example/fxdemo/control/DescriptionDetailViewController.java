package org.example.fxdemo.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.fxdemo.MainApplication;
import org.example.fxdemo.NavigationViewController;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.service.ItemService;

public class DescriptionDetailViewController extends NavigationViewController {
    private final Item item;

    @FXML
    TextField descriptionField;

    @FXML
    Button btnDelete;

    public DescriptionDetailViewController(Item item) {
        this.item = item;
    }

    @FXML
    public void initialize() {
        descriptionField.setText(item.getDescription());
        descriptionField.textProperty().addListener((observable, oldValue, newValue) -> item.setDescription(newValue));

        btnDelete.setDisable(!item.getAction().isDeleteAllowed());
    }

    public void handleBack() {
        this.navigationController.popView();
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
