package org.example.fxdemo;

import javafx.fxml.FXMLLoader;
import org.example.fxdemo.control.ItemFormController;
import org.example.fxdemo.model.Item;

public class SceneUtil {
    public static <T> T loadItemForm(Item selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneUtil.class.getResource("control/ItemForm.fxml"));
            loader.setControllerFactory(param -> new ItemFormController(selectedItem));
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T loadTableView() {
        try {
            return FXMLLoader.load(SceneUtil.class.getResource("TableView.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
