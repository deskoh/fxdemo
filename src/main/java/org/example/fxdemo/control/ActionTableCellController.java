package org.example.fxdemo.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Setter;
import org.example.fxdemo.SceneUtil;
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
        SceneUtil.loadDetailView(action, (Item) this.item.clone());
    }

    public void handleDelete() {
        ItemService.deleteItem(this.item.getId());
    }
}
