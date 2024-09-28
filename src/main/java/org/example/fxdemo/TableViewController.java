package org.example.fxdemo;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.service.ItemService;

public class TableViewController {
    @FXML
    private TableView<Item> tableView;

    @FXML
    private Button btnDelete;

    @FXML
    private void initialize() {
        // Following defined in TableView.fxml
        // actionColumn.setCellFactory(ActionTableCell.getCellFactory());
        var itemList = ItemService.getItems();
        tableView.setItems(itemList);

        // btnDelete.disableProperty().bind(Bindings.isNull(tableView.getSelectionModel().selectedItemProperty()));
        btnDelete.disableProperty().bind(
                Bindings.createBooleanBinding(
                        () -> {
                            Item selectedItem = tableView.getSelectionModel().getSelectedItem();
                            return selectedItem == null || !selectedItem.getAction().isDeleteAllowed();
                        },
                        tableView.getSelectionModel().selectedItemProperty()
                )
        );
    }

    public void handleAdd() {
        MainApplication.loadItemForm(ItemService.createItem());
    }

    public void handleRemove() {
        var selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            tableView.getItems().remove(selectedItem);
        }
    }
}