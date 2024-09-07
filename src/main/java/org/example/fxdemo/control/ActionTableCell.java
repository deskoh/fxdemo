package org.example.fxdemo.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.model.ItemAction;

public class ActionTableCell extends TableCell<Item, ItemAction> {
    private FXMLLoader fxmlLoader;

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

                var item = this.getTableRow().getItem();
                var controller = new ActionTableCellController(item, item.getAction());
                fxmlLoader.setController(controller);

                setGraphic(fxmlLoader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
