package org.example.fxdemo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.util.Stack;

public class NavigationController {
    @AllArgsConstructor
    public enum View {
        NAME("control/NameDetailView.fxml"),
        DESCRIPTION("control/DescriptionDetailView.fxml"),
        COMBOBOX("control/ComboBoxDemo.fxml");

        @Getter
        private final String fxmlFile;
    }

    protected final Stack<Node> viewStack = new Stack<>();
    protected final Pane rootPane;

    /**
     * Constructs a Navigation Controller with root node.
     *
     * @param rootPane Root node for view stack.
     */
    public NavigationController(Pane rootPane) {
        this.rootPane = rootPane;
    }

    /**
     * Constructs a Navigation Controller with root node, initial view and model.
     *
     * @param rootPane Root node for view stack.
     * @param view     Initial view.
     * @param model    Model for initial view controller.
     * @param <T>      Type of model for view controller.
     */
    public <T> NavigationController(Pane rootPane, View view, T model) {
        this.rootPane = rootPane;
        this.pushView(view, model);
    }

    /**
     * Push a new view with specified controller factory.
     */
    public void pushView(View view, Callback<Class<?>, NavigationViewController> controllerFactory) {
        var node = this.loadView(view.getFxmlFile(), controllerFactory::call);
        rootPane.getChildren().setAll(this.viewStack.push(node));
    }

    /**
     * Push a view with controller construction using specified model.
     */
    public <T> void pushView(View view, T model) {
        this.pushView(view, getControllerFactory(model)::call);
    }

    public void popView() {
        if (this.viewStack.size() > 1) {
            this.viewStack.pop();
            rootPane.getChildren().setAll(this.viewStack.peek());
        }
    }

    protected Node loadView(String fxmlFile, Callback<Class<?>, Object> controllerFactory) {
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

    @SuppressWarnings("unchecked")
    private static <T extends NavigationViewController, M> Callback<Class<?>, T> getControllerFactory(M model) {
        return type -> {
            try {
                for (Constructor<?> constructor : type.getConstructors()) {
                    if (constructor.getParameterCount() == 1
                            && constructor.getParameterTypes()[0].equals(model.getClass())
                    ) {
                        return (T) constructor.newInstance(model);
                    }
                }
                return (T) type.getConstructor().newInstance();
            } catch (Exception ex) {
                return null;
            }
        };
    }
}
