package src.logica;

import java.time.LocalDate;

public class Ingreso extends Movimiento {
    private CategoriaIngreso categoria;

    public Ingreso(LocalDate fecha, String concepto, float valor, CategoriaIngreso categoria) {
        super(fecha, concepto, valor);
        this.categoria = categoria;
    }

    public CategoriaIngreso getCategoria() {
        return categoria;
    }
}
