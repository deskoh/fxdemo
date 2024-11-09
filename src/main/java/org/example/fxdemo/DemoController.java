package org.example.fxdemo;

import javafx.event.ActionEvent;
import org.example.fxdemo.model.DemoModel;
import org.example.fxdemo.model.Fruit;
import org.example.fxdemo.model.Vegetable;

import java.util.ArrayList;
import java.util.Arrays;

public class DemoController extends NavigationViewController {
    public void handleDemo1(ActionEvent actionEvent) {
        DemoModel<Fruit> data1 = new DemoModel<>(
                "Fruits", "Apple", new ArrayList(Arrays.asList(new Fruit(1, "Durian")))
        );
        this.navigationController.pushView(NavigationController.View.DEMO_FORM, data1);
    }

    public void handleDemo2(ActionEvent actionEvent) {
        DemoModel<Vegetable> data2 = new DemoModel<>(
                "Vegetables", "Carrot", new ArrayList(Arrays.asList(new Vegetable("123g")))
        );
        this.navigationController.pushView(NavigationController.View.DEMO_FORM, data2);
    }

    public void handleDemo3(ActionEvent actionEvent) {
        DemoModel<String> data3 = new DemoModel<>(
                "Others", "Others", new ArrayList(Arrays.asList("Misc"))
        );
        this.navigationController.pushView(NavigationController.View.DEMO_FORM, data3);
    }

    public void handleDemo(ActionEvent actionEvent) {
        this.navigationController.pushView(NavigationController.View.DEMO_FORM, null);
    }
}
