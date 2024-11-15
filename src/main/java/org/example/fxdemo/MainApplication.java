package org.example.fxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.fxdemo.model.Item;

import java.io.IOException;

public class MainApplication extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("TableView.fxml"));
        scene = new Scene(fxmlLoader.load(), 640, 480);
        scene.getStylesheets().add("stylesheet.css");
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void loadItemForm(Item item) {
        Parent parent = SceneUtil.loadItemForm(item);
        scene.setRoot(parent);
    }

    public static void closeItemForm() {
        Parent parent = SceneUtil.loadView("TableView.fxml");
        scene.setRoot(parent);
    }

    public static void loadView(String fxmlFile, Callback<Class<?>, Object> controllerFactory) {
        Parent parent = SceneUtil.loadView(fxmlFile, controllerFactory);
        scene.setRoot(parent);
    }
}