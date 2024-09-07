package org.example.fxdemo.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.model.ItemAction;
import org.example.fxdemo.service.ItemService;

public class ActionTableCellController {
    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    private final Item item;
    private final ItemAction action;

    public ActionTableCellController(Item item, ItemAction action) {
        this.item = item;
        this.action = action;
    }

    @FXML
    public void initialize() {
        btnEdit.setDisable(!this.action.isEditAllowed());
        btnDelete.setDisable(!this.action.isDeleteAllowed());
    }

    public void handleEdit(ActionEvent action) throws CloneNotSupportedException {
        var node = (Node) action.getSource();
        var scene = node.getScene();
        scene.setRoot(this.loadDetailView(scene, (Item) this.item.clone()));
    }

    public void handleDelete(ActionEvent actionEvent) {
        ItemService.deleteItem(this.item.getId());
    }

    private <T> T loadDetailView(Scene scene, Item selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MasterDetail.fxml"));
            T parent = loader.<T>load();

            MasterDetailController detailController = loader.getController();
            detailController.setScene(scene);
            detailController.setItem(selectedItem);
            detailController.loadNameDetailView();

            return parent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
