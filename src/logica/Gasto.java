package src.logica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public void save(String fichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))) {
            // convertir los datos del objeto a string
            String datosGasto = String.format("%s,%s,%.2f,%s%n", getFecha(), getConcepto(), getValor(), categoria);
            // escribir la cadena en el fichero
            bw.write(datosGasto);
            System.out.println("Gasto guardado correctamente.");

        } catch (IOException error) {
            System.out.println("Ha ocurrido un error al guardar el gasto en el archivo.");
            error.printStackTrace();
        }
  }
}
