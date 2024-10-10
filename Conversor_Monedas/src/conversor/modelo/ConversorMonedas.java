package conversor.modelo;
import java.util.Map;


public class ConversorMonedas {

    private Map<String, Double> tasasCambio;

    public ConversorMonedas(Map<String, Double> tasasCambio) {
        this.tasasCambio = tasasCambio;
    }

    public double convertir(double cantidad, String monedaOrigen, String monedaDestino) {
        if (!tasasCambio.containsKey(monedaOrigen) || !tasasCambio.containsKey(monedaDestino)) {
            throw new IllegalArgumentException("Moneda no soportada.");
        }

        double tasaOrigen = tasasCambio.get(monedaOrigen);
        double tasaDestino = tasasCambio.get(monedaDestino);

        return (cantidad / tasaOrigen) * tasaDestino;
    }
}