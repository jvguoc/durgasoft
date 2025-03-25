package controlador;

import modelo.*;
import vista.VistaConsola;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controlador {
    private List<Socio> listaSocios = new ArrayList<>();
    private List<Excursion> listaExcursiones = new ArrayList<>();
    private List<Inscripcion> listaInscripciones = new ArrayList<>();
    private VistaConsola vista = new VistaConsola();

    public void registrarSocio(Socio socio) {
        socio.registrar();
        listaSocios.add(socio);
        vista.mostrarMensaje("Socio " + socio.getNombre() + " registrado con éxito.");
    }

    public void registrarExcursion(Excursion excursion) {
        excursion.crear();
        listaExcursiones.add(excursion);
        vista.mostrarMensaje("Excursión " + excursion.getNombre() + " registrada con éxito.");
    }

    public void modificarSocio(int id, String nuevoNombre, String nuevaLocalidad, String nuevoTelefono) throws SocioNoEncontradoException {
        Socio socio = buscarSocioPorId(id);
        if (socio == null) {
            throw new SocioNoEncontradoException("No se encontró el socio con ID: " + id);
        }
        socio.modificar(nuevoNombre, nuevaLocalidad, nuevoTelefono);
        vista.mostrarMensaje("Socio modificado con éxito.");
    }

    public void eliminarSocio(int id) throws SocioNoEncontradoException{
        Socio socio = buscarSocioPorId(id);
        if (socio != null) {
            socio.eliminar();
            listaSocios.remove(socio);
            vista.mostrarMensaje("Socio eliminado con éxito.");
        }
    }

    public void mostrarSocios() {
        if (listaSocios.isEmpty()) {
            vista.mostrarMensaje("No hay socios registrados.");
            return;
        }
        vista.mostrarMensaje("Lista de Socios:");
        for (Socio socio : listaSocios) {
            vista.mostrarMensaje(socio.toString());
        }
    }

    public void mostrarExcursiones() {
        if (listaExcursiones.isEmpty()) {
            vista.mostrarMensaje("No hay excursiones registradas.");
            return;
        }
        vista.mostrarMensaje("Lista de Excursiones:");
        for (Excursion excursion : listaExcursiones) {
            vista.mostrarMensaje(excursion.toString());
        }
    }

    public Socio buscarSocioPorId(int id) {
        for (Socio s : listaSocios) {
            if (s.getIdSocio() == id) {
                return s;
            }
        }
        vista.mostrarMensaje("Socio no encontrado.");
        return null;
    }


    public Excursion buscarExcursionPorId(int id) {
        for (Excursion e : listaExcursiones) {
            if (e.getIdExcursion() == id) {
                return e;
            }
        }
        vista.mostrarMensaje("Excursión no encontrada.");
        return null;
    }

    public void inscribirSocio(int idSocio, int idExcursion) throws SocioNoEncontradoException, ExcursionNoEncontradaException, PlazasInsuficientesException, InscripcionDuplicadaException {
        Socio socio = buscarSocioPorId(idSocio);
        Excursion excursion = buscarExcursionPorId(idExcursion);

        if (socio == null) {
            throw new SocioNoEncontradoException("Socio no encontrado.");
        }
        if (excursion == null) {
            throw new ExcursionNoEncontradaException("Excursión no encontrada.");
        }
        if (excursion.getPlazasDisponibles() <= 0) {
            throw new PlazasInsuficientesException("No hay plazas disponibles.");
        }
        if (estaInscrito(idSocio, idExcursion)) {
            throw new InscripcionDuplicadaException("El socio ya está inscrito en la excursión.");
        }

        excursion.getSociosInscritos().add(idSocio);
        excursion.setPlazasDisponibles(excursion.getPlazasDisponibles() - 1);

        vista.mostrarMensaje("Socio " + socio.getNombre() + " se ha inscrito en la excursión: " + excursion.getNombre());
    }



    public void eliminarInscripcion(int idSocio, int idExcursion) {
        Inscripcion inscripcionAEliminar = null;

        for (Inscripcion inscripcion : listaInscripciones) {
            if (inscripcion.getSocio().getIdSocio() == idSocio &&
                    inscripcion.getExcursion().getIdExcursion() == idExcursion) {
                inscripcionAEliminar = inscripcion;
                break;
            }
        }

        if (inscripcionAEliminar != null) {
            inscripcionAEliminar.cancelar();  // Devuelve una plaza a la excursión
            listaInscripciones.remove(inscripcionAEliminar);
            vista.mostrarMensaje("Inscripción eliminada correctamente.");
        } else {
            vista.mostrarMensaje("No se encontró una inscripción con esos datos.");
        }
    }

    // JUNIT
    public boolean estaInscrito(int idSocio, int idExcursion) {
        Excursion excursion = buscarExcursionPorId(idExcursion);
        return excursion != null && excursion.getSociosInscritos().contains(idSocio);
    }

}
