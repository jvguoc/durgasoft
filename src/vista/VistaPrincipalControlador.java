package vista;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VistaPrincipalControlador {

    private void cargarVentana(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirVistaSocios() {
        cargarVentana("/vista/VistaSocio.fxml", "Gestión de Socios");
    }

    @FXML
    private void abrirVistaExcursiones() {
        cargarVentana("/vista/VistaExcursion.fxml", "Gestión de Excursiones");
    }

    @FXML
    private void abrirVistaInscripciones() {
        cargarVentana("/vista/VistaInscripcion.fxml", "Gestión de Inscripciones");
    }
}
