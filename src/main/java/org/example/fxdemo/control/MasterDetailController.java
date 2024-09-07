package org.example.fxdemo.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import org.example.fxdemo.model.Item;

/**
 * Master Detail Controller that swaps child detail views.
 */
public class MasterDetailController {
    private Scene scene;

    @Getter
    @Setter
    private Item item;

    @FXML
    private Pane rootPane;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void loadNameDetailView() {
        var controller = new NameDetailViewController(this);
        this.loadView("NameDetailView.fxml", controller);
    }

    public void loadDescriptionDetailView() {
        var controller = new DescriptionDetailViewController(this);
        this.loadView("DescriptionDetailView.fxml", controller);
    }

    public void closeDetailView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/fxdemo/TableView.fxml"));
            this.scene.setRoot(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadView(String fxmlFile, Object controller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            fxmlLoader.setController(controller);

            this.rootPane.getChildren().clear();
            this.rootPane.getChildren().add(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
