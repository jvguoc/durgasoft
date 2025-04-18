package controlador;

import modelo.Socio;
import java.util.ArrayList;
import java.util.List;

public class ControladorSocio {
    private List<Socio> listaSocios = new ArrayList<>();

    public void registrarSocio(Socio socio) {
        listaSocios.add(socio);
    }

    public void modificarSocio(int id, String nuevoNombre, String nuevaLocalidad, String nuevoTelefono) throws SocioNoEncontradoException {
        Socio socio = buscarSocioPorId(id);
        if (socio == null) {
            throw new SocioNoEncontradoException("No se encontró el socio con ID: " + id);
        }

        socio.setNombre(nuevoNombre);
        socio.setLocalidad(nuevaLocalidad);
        socio.setTelefono(nuevoTelefono);
        System.out.println("Datos del socio con ID " + id + " han sido actualizados.");
    }

    public void eliminarSocio(int id) throws SocioNoEncontradoException {
        Socio socio = buscarSocioPorId(id);
        if (socio == null) {
            throw new SocioNoEncontradoException("No se encontró el socio con ID: " + id);
        }
        listaSocios.remove(socio);
        System.out.println("Socio con ID " + id + " ha sido eliminado.");
    }

    public List<Socio> obtenerSocios() {
        return listaSocios;
    }

    public Socio buscarSocioPorId(int id) {
        for (Socio socio : listaSocios) {
            if (socio.getIdSocio() == id) {
                return socio;
            }
        }
        return null;
    }
}
