package conversor.utilidades;

public class Utilidades {

    public static boolean validarEntrada(String[] partes) {
        if (partes.length != 3) {
            System.out.println("Error: Formato incorrecto. Debes escribir cantidad, moneda origen y moneda destino.");
            return false;
        }

        try {
            Double.parseDouble(partes[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error: La cantidad debe ser un número.");
            return false;
        }

        return true;
    }

    public static String normalizarCodigoMoneda(String codigo) {
        return codigo.trim().toUpperCase();
    }
}