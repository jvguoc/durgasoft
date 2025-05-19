package vista;

import controlador.ControladorSocio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import modelo.Socio;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;


public class VistaSocioControlador {

    @FXML private TableView<Socio> tablaSocios;
    @FXML private TableColumn<Socio, Integer> colId;
    @FXML private TableColumn<Socio, String> colNombre;
    @FXML private TableColumn<Socio, String> colLocalidad;
    @FXML private TableColumn<Socio, String> colTelefono;

    private ControladorSocio controlador;
    private ObservableList<Socio> listaSocios;

    @FXML
    public void initialize() {
        controlador = new ControladorSocio();

        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getIdSocio()).asObject());
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colLocalidad.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLocalidad()));
        colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefono()));

        actualizarLista();
    }

    private void actualizarLista() {
        listaSocios = FXCollections.observableArrayList(controlador.obtenerSocios());
        tablaSocios.setItems(listaSocios);
    }

    @FXML
    private void alRegistrarSocio() {
        Dialog<Socio> dialogo = new Dialog<>();
        dialogo.setTitle("Registrar Socio");

        // Campos de entrada
        TextField nombreInput = new TextField();
        TextField localidadInput = new TextField();
        TextField telefonoInput = new TextField();

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.addRow(0, new Label("Nombre:"), nombreInput);
        grid.addRow(1, new Label("Localidad:"), localidadInput);
        grid.addRow(2, new Label("Teléfono:"), telefonoInput);

        dialogo.getDialogPane().setContent(grid);
        dialogo.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialogo.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Socio(
                        nombreInput.getText(),
                        localidadInput.getText(),
                        telefonoInput.getText()
                );
            }
            return null;
        });

        dialogo.showAndWait().ifPresent(socio -> {
            controlador.registrarSocio(socio);
            actualizarLista();
        });
    }

    @FXML
    private void alEliminarSocio() {
        Socio seleccionado = tablaSocios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            controlador.eliminarSocio(seleccionado.getIdSocio());
            actualizarLista();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecciona un socio para eliminar.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void alModificarSocio() {
        Socio seleccionado = tablaSocios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Debes seleccionar un socio.");
            return;
        }

        Dialog<Socio> dialogo = new Dialog<>();
        dialogo.setTitle("Modificar Socio");

        TextField nombreInput = new TextField(seleccionado.getNombre());
        TextField localidadInput = new TextField(seleccionado.getLocalidad());
        TextField telefonoInput = new TextField(seleccionado.getTelefono());

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.addRow(0, new Label("Nombre:"), nombreInput);
        grid.addRow(1, new Label("Localidad:"), localidadInput);
        grid.addRow(2, new Label("Teléfono:"), telefonoInput);

        dialogo.getDialogPane().setContent(grid);
        dialogo.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialogo.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                seleccionado.setNombre(nombreInput.getText());
                seleccionado.setLocalidad(localidadInput.getText());
                seleccionado.setTelefono(telefonoInput.getText());
                return seleccionado;
            }
            return null;
        });

        dialogo.showAndWait().ifPresent(socio -> {
            controlador.modificarSocio(socio);
            actualizarLista();
        });
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING, mensaje, ButtonType.OK);
        alerta.showAndWait();
    }

}
