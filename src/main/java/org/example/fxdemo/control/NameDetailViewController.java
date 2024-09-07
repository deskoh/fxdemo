package org.example.fxdemo.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NameDetailViewController {
    private MasterDetailController parentController;

    @FXML
    TextField nameField;

    public NameDetailViewController(MasterDetailController masterDetailController) {
        this.parentController = masterDetailController;
    }

    @FXML
    public void initialize() {
        var item = this.parentController.getItem();
        nameField.setText(item.getName());
        nameField.textProperty().addListener((observable, oldValue, newValue) -> item.setName(newValue));
    }

    public void goToDescriptionPage(ActionEvent actionEvent) {
        this.parentController.loadDescriptionDetailView();
    }

    public void handleCancel(ActionEvent actionEvent) {
        this.parentController.closeDetailView();
    }
}
