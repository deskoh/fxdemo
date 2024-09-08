package org.example.fxdemo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.example.fxdemo.control.MasterDetailController;
import org.example.fxdemo.model.Item;

import java.util.EventObject;

public class SceneUtil {
    public static void loadDetailView(EventObject event, Item item) {
        var scene = getScene(event);
        Parent parent = loadDetailView(scene, item);
        scene.setRoot(parent);
    }

    public static void closeDetailView(EventObject event) {
        try {
            var scene = getScene(event);
            FXMLLoader fxmlLoader = new FXMLLoader(SceneUtil.class.getResource("TableView.fxml"));
            scene.setRoot(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Scene getScene(EventObject event) {
        var node = (Node) event.getSource();
        return node.getScene();
    }

    private static <T> T loadDetailView(Scene scene, Item selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneUtil.class.getResource("control/MasterDetail.fxml"));
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
