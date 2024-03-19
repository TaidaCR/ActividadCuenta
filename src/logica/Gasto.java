package src.logica;

import java.time.LocalDate;

public class Gasto extends Movimiento {
    public Gasto(LocalDate fecha, String concepto, float valor, CategoriaGasto categoria) {
        super(fecha, concepto, valor, categoria);
    }
}
