package org.example.fxdemo;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;

import java.util.function.Consumer;

public class Util {

    /**
     * Unidirectional binding from ObservableProperty to a setter
     *
     * @param property     Binding source.
     * @param setter       Setter method to call to update value when binding source change.
     * @param initialValue Initial value for property.
     * @param <T>          The type value to bind to.
     * @param <S>          The property type.
     */
    public static <T, S extends ObservableValue<T> & WritableValue<T>> void bindPropertyToSetter(
            S property, Consumer<T> setter, T initialValue
    ) {
        property.setValue(initialValue);
        property.addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                setter.accept(newVal);
            }
        });
    }
}
