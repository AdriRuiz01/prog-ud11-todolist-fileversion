module es.progcipfpbatoi {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.progcipfpbatoi.controlador to javafx.fxml;
    exports es.progcipfpbatoi;
}
