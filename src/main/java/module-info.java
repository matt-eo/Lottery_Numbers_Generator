module org.matt {
    requires javafx.controls;
    requires javafx.fxml;
    //requires java.sql;
    //requires sqlite.jdbc;
    //requires org.postgresql.jdbc;

    opens org.matt to javafx.fxml;
    exports org.matt;
}