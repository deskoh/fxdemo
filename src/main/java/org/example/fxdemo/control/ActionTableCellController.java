package org.example.fxdemo.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import lombok.Setter;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.model.ItemAction;
import org.example.fxdemo.service.ItemService;

public class ActionTableCellController {
    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @Setter
    private Item item;
    @Setter
    private ItemAction action;

    public void refresh() {
        btnEdit.setDisable(!this.action.isEditAllowed());
        btnDelete.setDisable(!this.action.isDeleteAllowed());
    }

    public void handleEdit(ActionEvent action) throws CloneNotSupportedException {
        var node = (Node) action.getSource();
        var scene = node.getScene();
        scene.setRoot(this.loadDetailView(scene, (Item) this.item.clone()));
    }

    public void handleDelete() {
        ItemService.deleteItem(this.item.getId());
    }

    private <T> T loadDetailView(Scene scene, Item selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MasterDetail.fxml"));
            T parent = loader.load();

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
