module es.jconca.gameab1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.jconca.gameab1 to javafx.fxml;
    exports es.jconca.gameab1;
}