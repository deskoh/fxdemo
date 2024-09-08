package org.example.fxdemo.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.model.ItemAction;

public class ActionTableCell extends TableCell<Item, ItemAction> {
    private FXMLLoader fxmlLoader;
    private ActionTableCellController controller;
    private Node node;

    public static Callback<TableColumn<Item, ItemAction>, TableCell<Item, ItemAction>> getCellFactory() {
        return param -> new ActionTableCell();
    }

    @Override
    protected void updateItem(ItemAction itemAction, boolean empty) {
        super.updateItem(itemAction, empty);

        if (empty || itemAction == null) {
            setGraphic(null);
            return;
        }
        if (fxmlLoader == null) {
            try {
                fxmlLoader = new FXMLLoader(getClass().getResource("ActionTableCell.fxml"));
                node = fxmlLoader.load();
                // setGraphic(node);
                controller = fxmlLoader.getController();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (getGraphic() == null) {
            setGraphic(node);
        }
        // Following will run when row is created or neighbouring rows are deleted
        var item = this.getTableRow().getItem();
        controller.setItem(item);
        controller.setAction(item.getAction());
        controller.refresh();
    }
}
