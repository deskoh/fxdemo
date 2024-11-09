package org.example.fxdemo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;
import org.example.fxdemo.model.Item;

public class SceneUtil {
    @SuppressWarnings("unchecked")
    public static <T> T loadItemForm(Item selectedItem) {
        return (T) loadView("ItemForm.fxml", param -> new ItemFormController(selectedItem));
    }

    public static Parent loadView(String fxmlFile) {
        return loadView(fxmlFile, null);
    }

    public static Parent loadView(String fxmlFile, Callback<Class<?>, Object> controllerFactory) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneUtil.class.getResource(fxmlFile));
            if (controllerFactory != null) {
                fxmlLoader.setControllerFactory(controllerFactory);
            }
            return fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
