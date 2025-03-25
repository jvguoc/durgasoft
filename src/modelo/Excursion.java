package modelo;

public class Excursion {
    private int idExcursion;
    private String nombre;
    private Date fecha;
    private String lugar;
    private int plazasDisponibles;

    public Excursion(int idExcursion, String nombre, Date fecha, String lugar, int plazasDisponibles) {
        this.idExcursion = idExcursion;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.plazasDisponibles = plazasDisponibles;
    }

    public void crear() {
        //TODO
    }

    public void modificar() {
        //TODO
    }

    public void eliminar() {
        //TODO
    }

    public void consultar() {
        //TODO
    }

    // G & S
    public int getIdExcursion() {
        return idExcursion;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }
}
