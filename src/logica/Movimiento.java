package src.logica;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public abstract class Movimiento {
    private LocalDate fecha;
    private String concepto;
    private float valor;
    private Enum<?> categoria;
    protected String tipo;

    //contructor principal
    public Movimiento(LocalDate fecha, String concepto, float valor, Enum<?> categoria) {
        // Movimiento mv = new Movimiento(new LocalDate.of(1970, 4, 8), "Comprar potitos", 150)
        this.fecha = fecha;
        this.concepto = concepto;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Movimiento(String concepto, float valor, Enum<?> categoria) {
        this(LocalDate.now(), concepto, valor, categoria);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public float getValor() {
        return valor;
    }

    public Enum<?> getCategoria() {
        return categoria;
    }

    public void save(String fichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true))) {
            // convertir los datos del objeto a string

            String datosMovimiento = String.format("%s;%s;%.2f;%s;%s\n", getFecha(), getConcepto(), getValor(), categoria, tipo);
            // escribir la cadena en el fichero
            bw.write(datosMovimiento);
            System.out.println("Movimiento guardado correctamente.");

        } catch (IOException error) {
            System.out.println("Ha ocurrido un error al guardar el movimiento en el archivo.");
            error.printStackTrace();
        }
    }

    
}
