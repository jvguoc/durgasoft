package vista;

import controlador.ControladorInscripcion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import modelo.Inscripcion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VistaInscripcionControlador {

    @FXML private TableView<Inscripcion> tablaInscripciones;
    @FXML private TableColumn<Inscripcion, Integer> colId;
    @FXML private TableColumn<Inscripcion, Integer> colSocio;
    @FXML private TableColumn<Inscripcion, Integer> colExcursion;
    @FXML private TableColumn<Inscripcion, String> colFecha;

    private ControladorInscripcion controlador;
    private ObservableList<Inscripcion> listaInscripciones;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    public void initialize() {
        controlador = new ControladorInscripcion();

        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getIdInscripcion()).asObject());
        colSocio.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getSocio().getIdSocio()).asObject());
        colExcursion.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getExcursion().getIdExcursion()).asObject());
        colFecha.setCellValueFactory(data -> new SimpleStringProperty(formatoFecha.format(data.getValue().getFechaInscripcion())));

        actualizarLista();
    }

    private void actualizarLista() {
        listaInscripciones = FXCollections.observableArrayList(controlador.obtenerInscripciones());
        tablaInscripciones.setItems(listaInscripciones);
    }

    @FXML
    private void alRegistrarInscripcion() {
        Dialog<int[]> dialogo = new Dialog<>();
        dialogo.setTitle("Registrar Inscripción");

        TextField socioInput = new TextField();
        TextField excursionInput = new TextField();

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.addRow(0, new Label("ID Socio:"), socioInput);
        grid.addRow(1, new Label("ID Excursión:"), excursionInput);

        dialogo.getDialogPane().setContent(grid);
        dialogo.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialogo.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                try {
                    int idSocio = Integer.parseInt(socioInput.getText());
                    int idExcursion = Integer.parseInt(excursionInput.getText());
                    return new int[]{idSocio, idExcursion};
                } catch (Exception e) {
                    mostrarError("IDs inválidos.");
                }
            }
            return null;
        });

        dialogo.showAndWait().ifPresent(ids -> {
            controlador.inscribir(ids[0], ids[1]);
            actualizarLista();
        });
    }

    @FXML
    private void alEliminarInscripcion() {
        Inscripcion seleccionada = tablaInscripciones.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            controlador.eliminarInscripcion(seleccionada.getIdInscripcion());
            actualizarLista();
        } else {
            mostrarError("Selecciona una inscripción para eliminar.");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK);
        alerta.showAndWait();
    }
}
