package src.entities;

public class Movimiento {
    private String fecha;
    private String asunto;
    private float valor;

    public Movimiento(String fecha, String asunto, float valor) {
        this.fecha = fecha;
        this.asunto = asunto;
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public float getValor() {
        return valor;
    }

}
