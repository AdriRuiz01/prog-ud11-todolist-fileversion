package es.progcipfpbatoi.controlador;

import es.progcipfpbatoi.modelo.dto.Tarea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URISyntaxException;

public class TaskListViewCellController extends ListCell<Tarea> {
    @FXML
    private AnchorPane root;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView statusImage;

    @FXML
    private ImageView categoryImage;


    public TaskListViewCellController() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/vistas/list_item.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Tarea tarea, boolean empty) {

        super.updateItem(tarea, empty);

        if (empty) {
            setGraphic(null);
        } else {
            descriptionLabel.setText(tarea.getDescripcion());
            setStatusImage(tarea);
            setCategoryImage(tarea);
            setGraphic(root);
        }
    }

    private void setCategoryImage(Tarea tarea) {
        try {
            switch (tarea.getCategoria()) {
                case CLASE -> categoryImage.setImage(new Image(getPathImage("/images/homework.png")));
                case HOGAR -> categoryImage.setImage(new Image(getPathImage("/images/housework.png")));
                case JUGAR -> categoryImage.setImage(new Image(getPathImage("/images/play.png")));
            }
        }catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    private void setStatusImage(Tarea tarea) {
        try {
            if (tarea.isFinalizada()) {
                statusImage.setImage(new Image(getPathImage("/images/done.png")));
            } else {
                statusImage.setImage(new Image(getPathImage("/images/pending.png")));
            }
        }catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    private String getPathImage(String fileName) throws URISyntaxException {

        return getClass().getResource(fileName).toURI().toString();
    }
}
