package org.example.fxdemo;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.model.ItemAction;
import org.example.fxdemo.service.ItemService;

public class TableViewController {
    @FXML
    private TableView<Item> tableView;

    @FXML
    private Button btnDelete;

    @FXML
    private Pane demoCombobox;

    @FXML
    private Pane demoRoot;

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

        new NavigationController(
                demoCombobox, NavigationController.View.COMBOBOX,
                new Item(1, "test", "desc", new ItemAction(true, true))
        );
        new NavigationController(demoRoot, NavigationController.View.DEMO);
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