package org.example.fxdemo;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;
import org.example.fxdemo.model.Item;
import org.example.fxdemo.service.ItemService;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class ComboBoxDemoController extends NavigationViewController {
    private final Item item;

    @FXML
    Text selectionText;

    @FXML
    Spinner<Integer> maxValueSpinner;

    @FXML
    ComboBox<Integer> valueComboBox;

    @FXML
    ComboBox<Item.Category> categoryComboBox;

    @FXML
    public void initialize() {
        Util.bindPropertyToSetter(
                maxValueSpinner.getValueFactory().valueProperty(), item::setMaxValue, item.getMaxValue()
        );

        valueComboBox.itemsProperty().bind(Bindings.createObjectBinding(
                () -> FXCollections.observableArrayList(
                        IntStream.rangeClosed(0, maxValueSpinner.getValue()).boxed().collect(Collectors.toList())
                ), maxValueSpinner.valueProperty()
        ));
        Util.bindPropertyToSetter(valueComboBox.valueProperty(), item::setValue, item.getValue());


        StringBinding binding = Bindings.createStringBinding(() -> {
            var selectedValue = valueComboBox.getValue();
            return selectedValue != null ? "The selected value is: " + selectedValue : "No value has been selected";
        }, valueComboBox.valueProperty());
        selectionText.textProperty().bind(binding);

        categoryComboBox.setItems(FXCollections.observableArrayList(Item.Category.values()));
        Util.bindPropertyToSetter(categoryComboBox.valueProperty(), item::setCategory, item.getCategory());
    }

    public void handleSave(ActionEvent actionEvent) {
        ItemService.updateItem(this.item);
        MainApplication.closeItemForm();
    }
}
