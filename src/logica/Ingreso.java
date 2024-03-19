package src.logica;

import java.time.LocalDate;

public class Ingreso extends Movimiento {
    public Ingreso(LocalDate fecha, String concepto, float valor, CategoriaIngreso categoria) {
        super(fecha, concepto, valor, categoria);
    }
}
