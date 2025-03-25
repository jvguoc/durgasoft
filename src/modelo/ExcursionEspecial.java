package modelo;

import java.util.Date;

public class ExcursionEspecial extends Excursion {
    private TipoEvento tipo;

    public ExcursionEspecial(int idExcursion, String nombre, Date fecha, String lugar, int plazasDisponibles, TipoEvento tipo) {
        super(idExcursion, nombre, fecha, lugar, plazasDisponibles);
        this.tipo = tipo;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    @Override
    public void crear() {
        super.crear();
        System.out.println("Excursión especial de tipo: " + tipo);
    }

    @Override
    public void modificar(String nuevoNombre, Date nuevaFecha, String nuevoLugar, int nuevasPlazas) {
        super.modificar(nuevoNombre, nuevaFecha, nuevoLugar, nuevasPlazas);
        System.out.println("Tipo de excursión especial modificado a: " + tipo);
    }

    @Override
    public void eliminar() {
        super.eliminar();
        System.out.println("Excursión especial eliminada.");
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo de Evento: " + tipo;
    }
}
