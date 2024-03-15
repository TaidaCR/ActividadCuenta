import src.logica.CategoriaIngreso;
import src.presentacion.Validador;

public class Probatinas {
    public static void main(String[] args) {
        CategoriaIngreso categoria = new Validador().pideCatIngreso("Mensaje Entrada", "Mensaje Error");

    }
}
