package es.progcipfpbatoi.controlador;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.modelo.dto.Categoria;
import es.progcipfpbatoi.modelo.dto.Tarea;
import es.progcipfpbatoi.modelo.repositorios.TareaRepository;
import es.progcipfpbatoi.util.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TareaController implements Initializable {
    @FXML
    private ListView<Tarea> tareaListView;

    @FXML
    private TextField nuevaTareaTextField;

    @FXML
    private ComboBox<Categoria> categorySelector;

    @FXML
    private TextField searchBar;

    private TareaRepository tareaRepository;

    public TareaController(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    private ObservableList<Tarea> getData() {
        return FXCollections.observableArrayList(tareaRepository.findAll());
    }

    @FXML
    private void addNewTask() {
        Categoria categoria = categorySelector.getSelectionModel().getSelectedItem();

        Tarea tarea = new Tarea(
                tareaListView.getItems().size() + 1,
                nuevaTareaTextField.getText(),
                categoria);
        try {
            if (tareaRepository.save(tarea)) {
                tareaListView.getItems().add(tarea);
                nuevaTareaTextField.setText("");
                categorySelector.getSelectionModel().clearSelection();
            }
        }catch (DatabaseErrorException ex) {
            AlertMessages.mostrarAlertError("No se ha podido guardar la tarea. Error en el acceso a la base de datos.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    private void searchTasks() throws DatabaseErrorException {

        tareaListView.getItems().clear();
        String texto = searchBar.getText();
        ArrayList<Tarea> tareas = tareaRepository.findAll(texto);
        tareaListView.getItems().addAll(tareas);
    }

    @FXML
    private void handleSelectedItem(MouseEvent event) {
        /*
        tarea.cambiarEstado();
        if (tareaRepository.save(tarea)) {
            tareaListView.getSelectionModel().clearSelection();
            tareaListView.refresh();
        }*/
        try {
            Tarea tarea = tareaListView.getSelectionModel().getSelectedItem();
            TareaDetailController tareaDetailController = new TareaDetailController(
                    tarea, tareaRepository, this, "/vistas/tarea_list.fxml");
            ChangeScene.change(event, tareaDetailController, "/vistas/tarea_detail.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(nuevaTareaTextField.getPromptText());
        tareaListView.setItems(getData());
        tareaListView.setCellFactory((ListView<Tarea> l) -> new TaskListViewCellController());
        categorySelector.setItems(FXCollections.observableArrayList(Categoria.values()));
    }
}
