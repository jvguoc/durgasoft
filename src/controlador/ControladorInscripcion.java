package controlador;

import modelo.Excursion;
import modelo.Inscripcion;
import modelo.Socio;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ControladorInscripcion {
    private List<Inscripcion> listaInscripciones = new ArrayList<>();
    private ControladorSocio controladorSocio;
    private ControladorExcursion controladorExcursion;

    public ControladorInscripcion(ControladorSocio cs, ControladorExcursion ce) {
        this.controladorSocio = cs;
        this.controladorExcursion = ce;
    }

    public void inscribirSocio(int idSocio, int idExcursion) throws SocioNoEncontradoException, ExcursionNoEncontradaException, PlazasInsuficientesException, InscripcionDuplicadaException {
        Socio socio = controladorSocio.buscarSocioPorId(idSocio);
        Excursion excursion = controladorExcursion.buscarExcursionPorId(idExcursion);

        if (socio == null) throw new SocioNoEncontradoException("Socio no encontrado.");
        if (excursion == null) throw new ExcursionNoEncontradaException("Excursión no encontrada.");
        if (excursion.getPlazasDisponibles() <= 0) throw new PlazasInsuficientesException("No hay plazas disponibles.");
        if (excursion.getSociosInscritos().contains(idSocio)) throw new InscripcionDuplicadaException("El socio ya está inscrito en la excursión.");

        excursion.getSociosInscritos().add(idSocio);
        excursion.setPlazasDisponibles(excursion.getPlazasDisponibles() - 1);
        listaInscripciones.add(new Inscripcion(socio, excursion, new Date()));
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
            Excursion excursion = controladorExcursion.buscarExcursionPorId(idExcursion);
            if (excursion != null) {
                excursion.setPlazasDisponibles(excursion.getPlazasDisponibles() + 1);
            }

            listaInscripciones.remove(inscripcionAEliminar);
        }
    }

    public boolean estaInscrito(int idSocio, int idExcursion) {
        Excursion excursion = controladorExcursion.buscarExcursionPorId(idExcursion);
        return excursion != null && excursion.getSociosInscritos().contains(idSocio);
    }
}
