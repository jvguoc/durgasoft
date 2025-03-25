package modelo;

public class ExcursionEspecial extends Excursion {
    private TipoEvento tipo;

    public ExcursionEspecial(int idExcursion, String nombre, Date fecha, String lugar, int plazasDisponibles, TipoEvento tipo) {
        super(idExcursion, nombre, fecha, lugar, plazasDisponibles);
        this.tipo = tipo;
    }

    // G & S
    public TipoEvento getTipo() {
        return tipo;
    }
}
