package org.example.fxdemo.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Setter;
import org.example.fxdemo.MainApplication;
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

    public void handleEdit() throws CloneNotSupportedException {
        MainApplication.loadItemForm((Item) this.item.clone());
    }

    public void handleDelete() {
        ItemService.deleteItem(this.item.getId());
    }
}
