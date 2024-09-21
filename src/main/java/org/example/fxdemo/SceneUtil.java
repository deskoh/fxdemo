package org.example.fxdemo;

import javafx.fxml.FXMLLoader;
import org.example.fxdemo.control.MasterDetailController;
import org.example.fxdemo.model.Item;

public class SceneUtil {
    public static <T> T loadMasterDetailView(Item selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneUtil.class.getResource("control/MasterDetail.fxml"));
            loader.setControllerFactory(param -> new MasterDetailController(selectedItem));
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
