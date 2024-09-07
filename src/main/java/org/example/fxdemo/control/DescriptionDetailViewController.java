package org.example.fxdemo.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.fxdemo.service.ItemService;

public class DescriptionDetailViewController {
    private MasterDetailController parentController;

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

    public void goToDescriptionPage(ActionEvent actionEvent) {
        this.parentController.loadNameDetailView();
    }

    public void handleBack(ActionEvent actionEvent) {
        this.parentController.loadNameDetailView();
    }

    public void handleSave(ActionEvent actionEvent) {
        ItemService.updateItem(this.parentController.getItem());
        this.parentController.closeDetailView();
    }

    public void handleCancel(ActionEvent actionEvent) {
        this.parentController.closeDetailView();
    }

    public void handleDelete(ActionEvent actionEvent) {
        ItemService.deleteItem(this.parentController.getItem().getId());
        this.parentController.closeDetailView();
    }
}
