package org.example.fxdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.fxdemo.control.ActionTableCell;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.model.ItemAction;
import org.example.fxdemo.service.ItemService;

public class TableViewController {
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<Item> tableView;

    @FXML
    private TableColumn<Item, ItemAction> actionColumn;

    @FXML
    private void initialize() {
        actionColumn.setCellFactory(ActionTableCell.getCellFactory());
        var itemList = ItemService.getItems();
        tableView.setItems(itemList);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void handleAdd(ActionEvent actionEvent) {

    }
}