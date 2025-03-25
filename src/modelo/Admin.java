package modelo;

public class Admin {
    public void crearSocio(Socio socio) {
        //TODO: Crear modelo.Socio + añadir exception handling etc
        System.out.println("modelo.Socio " + socio.getNombre() + " creado.");
    }

    public void crearExcursion(Excursion excursion) {
        //TODO: Crear modelo.Socio + añadir exception handling etc
        System.out.println("Excursión " + excursion.getNombre() + " creada.");
    }

    public void consultarSocios() {
        //TODO: Consultar todas los socios de la BBDD
    }

    public void consultarExcursiones() {
        //TODO: Consultar todas las excursiones de la BBDD
    }
}
