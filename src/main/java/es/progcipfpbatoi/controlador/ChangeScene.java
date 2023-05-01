package es.progcipfpbatoi.controlador;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeScene {
    public static void change(Stage stage, Initializable controller,
                              String path_to_view_file) throws IOException{

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(ChangeScene.class.getResource(path_to_view_file));
            fxmlLoader.setController(controller);

            AnchorPane rootLayout = fxmlLoader.load();

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
    }

    public static void change(Event event, Initializable controller,
                              String path_to_view_file) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        change(stage, controller, path_to_view_file);
    }
}
