package org.example.fxdemo.control;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Getter;
import org.example.fxdemo.MainApplication;

public class NameDetailViewController {
    @Getter
    private final MasterDetailController parentController;

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

    public void goToDescriptionPage() {
        this.parentController.loadDescriptionDetailView();
    }

    public void handleCancel() {
        MainApplication.closeDetailView();
    }
}
