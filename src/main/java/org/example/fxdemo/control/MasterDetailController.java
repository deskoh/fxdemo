package org.example.fxdemo.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import lombok.Getter;
import org.example.fxdemo.model.Item;

/**
 * Master Detail Controller that swaps child detail views.
 */
public class MasterDetailController {
    @Getter
    private final Item item;

    @FXML
    private Pane rootPane;

    public MasterDetailController(Item item) {
        this.item = item;
    }

    @FXML
    private void initialize() {
        this.loadNameDetailView();
    }

    public void loadNameDetailView() {
        this.loadView("NameDetailView.fxml", param -> new NameDetailViewController(this));
    }

    public void loadDescriptionDetailView() {
        this.loadView("DescriptionDetailView.fxml", param -> new DescriptionDetailViewController(this));
    }

    private void loadView(String fxmlFile, Callback<Class<?>, Object> controllerFactory) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            fxmlLoader.setRoot(rootPane);
            fxmlLoader.setControllerFactory(controllerFactory);

            rootPane.getChildren().clear();
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
