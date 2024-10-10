package conversor;

import conversor.api.APICliente;
import conversor.modelo.ConversorMonedas;
import conversor.modelo.HistorialConversiones;
import conversor.modelo.MonedasDisponibles;
import conversor.utilidades.Utilidades;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HistorialConversiones historial = new HistorialConversiones();

        System.out.println("==============================================================================");
        System.out.println("                 Bienvenido al Conversor de Monedas.");
        System.out.println("==============================================================================");
        System.out.println("Por favor, ingresa la cantidad y las monedas en el formato: cantidad | moneda_origen | moneda_destino (Ejemplo: 100 USD PEN)");

        while (true) {
            String entrada = scanner.nextLine();
            String[] partes = entrada.split(" ");

            if (Utilidades.validarEntrada(partes)) {
                double cantidad = Double.parseDouble(partes[0]);
                String monedaOrigen = Utilidades.normalizarCodigoMoneda(partes[1]);
                String monedaDestino = Utilidades.normalizarCodigoMoneda(partes[2]);

                try {
                    String respuestaAPI = APICliente.obtenerTasas(monedaOrigen);
                    Map<String, Double> tasasCambio = MonedasDisponibles.parsearJSON(respuestaAPI);

                    ConversorMonedas conversor = new ConversorMonedas(tasasCambio);
                    double resultado = conversor.convertir(cantidad, monedaOrigen, monedaDestino);

                    System.out.printf("Resultado: %.2f %s%n", resultado, monedaDestino);

                    historial.agregarConversion(cantidad, monedaOrigen, monedaDestino, resultado);
                } catch (Exception e) {
                    System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
                }
            }

            System.out.print("¿Desea ver el historial de conversiones? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                historial.mostrarHistorial();
            }


            System.out.println("\n¿Deseas realizar otra conversión? (S/N)");
            String continuar = scanner.nextLine();
            if (continuar.equalsIgnoreCase("S")) {
                System.out.println("Por favor, ingresa la cantidad y las monedas en el formato: cantidad moneda_origen moneda_destino (Ejemplo: 100 USD PEN)");

            }else{
                System.out.println("Gracias por Usar el conversor");
                break;
            }
        }

        scanner.close();
    }
}