module org.matt {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.matt to javafx.fxml;
    exports org.matt;
}