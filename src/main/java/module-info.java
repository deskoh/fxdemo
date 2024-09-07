module org.example.fxdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires static lombok;

    opens org.example.fxdemo to javafx.fxml;
    opens org.example.fxdemo.model to javafx.base;
    exports org.example.fxdemo;
    exports org.example.fxdemo.control;
    opens org.example.fxdemo.control to javafx.fxml;
}