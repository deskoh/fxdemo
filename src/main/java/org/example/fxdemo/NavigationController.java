package org.example.fxdemo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Stack;

public class NavigationController {
    @AllArgsConstructor
    public enum View {
        NAME("control/NameDetailView.fxml"),
        DESCRIPTION("control/DescriptionDetailView.fxml");

        @Getter
        private final String fxmlFile;
    }

    private final Stack<Node> viewStack = new Stack<>();
    private final Pane rootPane;

    public NavigationController(Pane rootPane) {
        this.rootPane = rootPane;
    }

    public void pushView(View view, Callback<Class<?>, NavigationViewController> controllerFactory) {
        var node = this.loadView(view.getFxmlFile(), controllerFactory::call);
        rootPane.getChildren().setAll(this.viewStack.push(node));
    }

    public void popView() {
        if (this.viewStack.size() > 1) {
            this.viewStack.pop();
            rootPane.getChildren().setAll(this.viewStack.peek());
        }
    }

    private Node loadView(String fxmlFile, Callback<Class<?>, Object> controllerFactory) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            fxmlLoader.setControllerFactory(controllerFactory);

            Node node = fxmlLoader.load();
            NavigationViewController controller = fxmlLoader.getController();
            controller.setNavigationController(this);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
