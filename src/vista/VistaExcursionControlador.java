package vista;

import controlador.ControladorExcursion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import modelo.Excursion;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class VistaExcursionControlador {

    @FXML private TableView<Excursion> tablaExcursiones;
    @FXML private TableColumn<Excursion, Integer> colId;
    @FXML private TableColumn<Excursion, String> colNombre;
    @FXML private TableColumn<Excursion, String> colLugar;
    @FXML private TableColumn<Excursion, String> colFecha;
    @FXML private TableColumn<Excursion, Integer> colPlazas;

    private ControladorExcursion controlador;
    private ObservableList<Excursion> listaExcursiones;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    public void initialize() {
        controlador = new ControladorExcursion();

        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getIdExcursion()).asObject());
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colLugar.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLugar()));
        colFecha.setCellValueFactory(data -> new SimpleStringProperty(formatoFecha.format(data.getValue().getFecha())));
        colPlazas.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPlazasDisponibles()).asObject());

        actualizarLista();
    }

    private void actualizarLista() {
        listaExcursiones = FXCollections.observableArrayList(controlador.obtenerExcursiones());
        tablaExcursiones.setItems(listaExcursiones);
    }

    @FXML
    private void alRegistrarExcursion() {
        Dialog<Excursion> dialogo = new Dialog<>();
        dialogo.setTitle("Registrar Excursión");

        TextField nombreInput = new TextField();
        TextField lugarInput = new TextField();
        TextField fechaInput = new TextField();
        TextField plazasInput = new TextField();

        fechaInput.setPromptText("yyyy-MM-dd");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.addRow(0, new Label("Nombre:"), nombreInput);
        grid.addRow(1, new Label("Lugar:"), lugarInput);
        grid.addRow(2, new Label("Fecha:"), fechaInput);
        grid.addRow(3, new Label("Plazas:"), plazasInput);

        dialogo.getDialogPane().setContent(grid);
        dialogo.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialogo.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                try {
                    String nombre = nombreInput.getText();
                    String lugar = lugarInput.getText();
                    Date fecha = formatoFecha.parse(fechaInput.getText());
                    int plazas = Integer.parseInt(plazasInput.getText());

                    return new Excursion(nombre, fecha, lugar, plazas);
                } catch (ParseException | NumberFormatException e) {
                    mostrarError("Formato inválido. Fecha debe ser yyyy-MM-dd y plazas un número.");
                }
            }
            return null;
        });

        dialogo.showAndWait().ifPresent(excursion -> {
            controlador.registrarExcursion(excursion);
            actualizarLista();
        });
    }

    @FXML
    private void alEliminarExcursion() {
        Excursion seleccionada = tablaExcursiones.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            controlador.eliminarExcursion(seleccionada.getIdExcursion());
            actualizarLista();
        } else {
            mostrarError("Selecciona una excursión para eliminar.");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK);
        alerta.showAndWait();
    }

    @FXML
    private void alModificarExcursion() {
        Excursion seleccionada = tablaExcursiones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarError("Debes seleccionar una excursión.");
            return;
        }

        Dialog<Excursion> dialogo = new Dialog<>();
        dialogo.setTitle("Modificar Excursión");

        TextField nombreInput = new TextField(seleccionada.getNombre());
        TextField lugarInput = new TextField(seleccionada.getLugar());
        TextField fechaInput = new TextField(formatoFecha.format(seleccionada.getFecha()));
        TextField plazasInput = new TextField(String.valueOf(seleccionada.getPlazasDisponibles()));
        fechaInput.setPromptText("yyyy-MM-dd");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.addRow(0, new Label("Nombre:"), nombreInput);
        grid.addRow(1, new Label("Lugar:"), lugarInput);
        grid.addRow(2, new Label("Fecha:"), fechaInput);
        grid.addRow(3, new Label("Plazas:"), plazasInput);

        dialogo.getDialogPane().setContent(grid);
        dialogo.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialogo.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                try {
                    seleccionada.setNombre(nombreInput.getText());
                    seleccionada.setLugar(lugarInput.getText());
                    seleccionada.setFecha(formatoFecha.parse(fechaInput.getText()));
                    seleccionada.setPlazasDisponibles(Integer.parseInt(plazasInput.getText()));
                    return seleccionada;
                } catch (Exception e) {
                    mostrarError("Formato inválido. Fecha debe ser yyyy-MM-dd y plazas un número.");
                }
            }
            return null;
        });

        dialogo.showAndWait().ifPresent(excursion -> {
            controlador.actualizarExcursion(excursion);
            actualizarLista();
        });
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK);
        alerta.showAndWait();
    }

}
