package controlador;

import modelo.Excursion;

import java.util.ArrayList;
import java.util.List;

public class ControladorExcursion {
    private List<Excursion> listaExcursiones = new ArrayList<>();

    public void registrarExcursion(Excursion excursion) {
        listaExcursiones.add(excursion);
    }

    public List<Excursion> obtenerExcursiones() {
        return listaExcursiones;
    }

    public Excursion buscarExcursionPorId(int id) {
        for (Excursion excursion : listaExcursiones) {
            if (excursion.getIdExcursion() == id) {
                return excursion;
            }
        }
        return null;
    }
}
