package es.progcipfpbatoi.util;

import javafx.scene.control.Alert;

public class AlertMessages {
    public static void mostrarAlertError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void mostrarAlertWarning(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Aviso");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private static void mostrarAlert(String title, String msg, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
