package src.entities;
import java.time.LocalDate;

public class Movimiento {
    private LocalDate fecha;
    private String concepto;
    private float valor;

    //contructor principal
    public Movimiento(LocalDate fecha, String concepto, float valor) {
        // Movimiento mv = new Movimiento(new LocalDate.of(1970, 4, 8), "Comprar potitos", 150)
        this.fecha = fecha;
        this.concepto = concepto;
        this.valor = valor;
    }

    public Movimiento(String fecha, String concepto, float valor) {
        // Movimiento mv = new Movimiento("1970-04-08", "Comprar potitos", 150)
        this(LocalDate.parse(fecha), concepto, valor);
    }

    public Movimiento(String concepto, float valor) {
        this(LocalDate.now(), concepto, valor);
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

}
