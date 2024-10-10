package conversor.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistorialConversiones {

    private static class Conversion {
        double cantidad;
        String monedaOrigen;
        String monedaDestino;
        double resultado;
        LocalDateTime fechaHora;

        public Conversion(double cantidad, String monedaOrigen, String monedaDestino, double resultado) {
            this.cantidad = cantidad;
            this.monedaOrigen = monedaOrigen;
            this.monedaDestino = monedaDestino;
            this.resultado = resultado;
            this.fechaHora = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return String.format("%.2f %s -> %.2f %s (Fecha: %s)", cantidad, monedaOrigen, resultado, monedaDestino, fechaHora.toString());
        }
    }

    private List<Conversion> historial = new ArrayList<>();

    public void agregarConversion(double cantidad, String monedaOrigen, String monedaDestino, double resultado) {
        historial.add(new Conversion(cantidad, monedaOrigen, monedaDestino, resultado));
    }

    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay conversiones registradas.");
        } else {
            System.out.println("Historial de Conversiones:");
            for (Conversion conversion : historial) {
                System.out.println(conversion);
            }
        }
    }
}