package modelo;

public class Admin {
    public void crearSocio(Socio socio) {
        System.out.println("Socio " + socio.getNombre() + " creado.");
    }

    public void crearExcursion(Excursion excursion) {
        System.out.println("Excursión " + excursion.getNombre() + " creada.");
    }

    public void consultarSocios() {
    }

    public void consultarExcursiones() {
    }
}
