package org.example.fxdemo.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.fxdemo.MainApplication;
import org.example.fxdemo.service.ItemService;

public class DescriptionDetailViewController {
    private final MasterDetailController parentController;

    @FXML
    TextField descriptionField;

    @FXML
    Button btnDelete;

    public DescriptionDetailViewController(MasterDetailController masterDetailController) {
        this.parentController = masterDetailController;
    }

    @FXML
    public void initialize() {
        var item = this.parentController.getItem();
        descriptionField.setText(item.getDescription());
        descriptionField.textProperty().addListener((observable, oldValue, newValue) -> item.setDescription(newValue));

        btnDelete.setDisable(!item.getAction().isDeleteAllowed());
    }

    public void handleBack() {
        this.parentController.loadNameDetailView();
    }

    public void handleSave() {
        ItemService.updateItem(this.parentController.getItem());
        MainApplication.closeDetailView();
    }

    public void handleCancel() {
        MainApplication.closeDetailView();
    }

    public void handleDelete() {
        ItemService.deleteItem(this.parentController.getItem().getId());
        MainApplication.closeDetailView();
    }
}
