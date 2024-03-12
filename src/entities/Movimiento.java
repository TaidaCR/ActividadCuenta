package src.entities;
import java.time.LocalDate;

public class Movimiento {
    private LocalDate fecha;
    private String asunto;
    private float valor;

    //contructor principal
    public Movimiento(LocalDate fecha, String asunto, float valor) {
        // Movimiento mv = new Movimiento(new LocalDate.of(1970, 4, 8), "Comprar potitos", 150)
        this.fecha = fecha;
        this.asunto = asunto;
        this.valor = valor;
    }

    public Movimiento(String fecha, String asunto, float valor) {
        // Movimiento mv = new Movimiento("1970-04-08", "Comprar potitos", 150)
        this(LocalDate.parse(fecha), asunto, valor);
    }

    public Movimiento(String asunto, float valor) {
        this(LocalDate.now(), asunto, valor);
    }



    public LocalDate getFecha() {
        return fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public float getValor() {
        return valor;
    }

}
