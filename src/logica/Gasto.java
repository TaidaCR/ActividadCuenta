package src.logica;

import java.time.LocalDate;

public class Gasto extends Movimiento {
    private CategoriaGasto categoria;

    public Gasto(LocalDate fecha, String concepto, float valor, CategoriaGasto categoria) {
        super(fecha, concepto, valor);
        this.categoria = categoria;
    }

    public CategoriaGasto getCategoria() {
        return categoria;
    }
}
