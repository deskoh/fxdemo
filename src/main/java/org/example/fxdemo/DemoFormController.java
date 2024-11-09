package org.example.fxdemo;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.example.fxdemo.control.ItemListSection;
import org.example.fxdemo.event.ItemEvent;
import org.example.fxdemo.model.DemoModel;
import org.example.fxdemo.model.Fruit;
import org.example.fxdemo.model.Vegetable;

public class DemoFormController extends NavigationViewController {
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private ComboBox<String> typeCombobox;
    @FXML
    private ItemListSection itemListSection;

    private static final ObservableList<String> fruitOptions = FXCollections.observableArrayList("Apple", "Banana", "Cherry");
    private static final ObservableList<String> vegetableOptions = FXCollections.observableArrayList("Carrot", "Broccoli", "Lettuce");
    private static final ObservableList<String> otherOptions = FXCollections.observableArrayList("Others");

    private ObservableList<Fruit> fruits = FXCollections.observableArrayList(new Fruit(1, "Fruit1"));
    private ObservableList<Vegetable> vegetables = FXCollections.observableArrayList(new Vegetable("100g"));
    private ObservableList<String> others = FXCollections.observableArrayList("Others");

    private DemoModel model;

    public DemoFormController(DemoModel model) {
        this.model = model;
    }

    public DemoFormController() {
        this(null);
    }

    @FXML
    private void initialize() {
        initializeBindings();
        initializeValues();
    }

    private void initializeBindings() {
        typeCombobox.itemsProperty().bind(Bindings.createObjectBinding(() -> {
            var selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
            if ("Fruits".equals(selectedCategory)) {
                return fruitOptions;
            } else if ("Vegetables".equals(selectedCategory)) {
                return vegetableOptions;
            } else if ("Others".equals(selectedCategory)) {
                return otherOptions;
            }
            return null;
        }, categoryComboBox.getSelectionModel().selectedItemProperty()));

        if (this.model != null) {
            typeCombobox.setDisable(true);
        } else {
            // Disable typeCombobox when `Others` category selected
            typeCombobox.disableProperty().bind(
                    new When(categoryComboBox.getSelectionModel().selectedItemProperty().isEqualTo("Others"))
                            .then(true)
                            .otherwise(false)
            );
        }

        // Select first Type when category change
        categoryComboBox.getSelectionModel().selectedItemProperty().addListener(
                (obs) -> typeCombobox.getSelectionModel().selectFirst()
        );
        categoryComboBox.setDisable(this.model != null);

        if (this.model != null) {
            this.fruits = FXCollections.observableArrayList(this.model.getItems());
            this.vegetables = FXCollections.observableArrayList(this.model.getItems());
            this.others = FXCollections.observableArrayList(this.model.getItems());
        }
        itemListSection.getItemList().itemsProperty().bind(Bindings.createObjectBinding(() -> {
            var selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
            if ("Fruits".equals(selectedCategory)) {
                return this.fruits;
            } else if ("Vegetables".equals(selectedCategory)) {
                return this.vegetables;
            } else if ("Others".equals(selectedCategory)) {
                return this.others;
            }
            return null;
        }, categoryComboBox.getSelectionModel().selectedItemProperty()));
    }

    private void initializeValues() {
        if (this.model != null) {
            Util.bindPropertyToSetter(categoryComboBox.valueProperty(), model::setCategory, model.getCategory());
            Util.bindPropertyToSetter(typeCombobox.valueProperty(), model::setType, model.getType());
        } else {
            categoryComboBox.getSelectionModel().selectFirst();
        }
    }

    private static int counter = 1;

    @FXML
    private void onItemAdd() {
        var selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
        if ("Fruits".equals(selectedCategory)) {
            this.fruits.add(new Fruit(this.fruits.size() + 1, "Fruit " + counter++));
        } else if ("Vegetables".equals(selectedCategory)) {
            this.vegetables.add(new Vegetable((100 + counter++) + "g"));
        } else if ("Others".equals(selectedCategory)) {
            this.others.add("Others " + counter++);
        }
    }

    @FXML
    private void onItemRemove() {
        var selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
        if ("Fruits".equals(selectedCategory)) {
            for (var i = 0; i < this.fruits.size(); i++) {
                this.fruits.get(i).setId(i + 1);
                this.fruits.set(i, this.fruits.get(i));
            }
        }
    }

    @FXML
    private <T> void onItemClicked(ItemEvent<T> event) {
        System.out.println(event.getItem());
    }

    @FXML
    private void handleBack(ActionEvent actionEvent) {
        this.navigationController.popView();
    }
}
