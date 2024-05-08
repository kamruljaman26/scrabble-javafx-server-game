module client {
    requires javafx.controls;
    requires javafx.fxml;

    opens client to javafx.fxml;
    exports client;
    exports client.model;
    opens client.model to javafx.fxml;
    exports client.view;
    opens client.view to javafx.fxml;
}