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
    public String toString() {
        return super.toString() + ", Tipo de Evento: " + tipo;
    }
}
