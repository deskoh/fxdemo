package org.example.fxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.fxdemo.model.Item;

import java.io.IOException;

public class MainApplication extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("TableView.fxml"));
        scene = new Scene(fxmlLoader.load(), 640, 480);
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
        Parent parent = SceneUtil.loadTableView();
        scene.setRoot(parent);
    }
}