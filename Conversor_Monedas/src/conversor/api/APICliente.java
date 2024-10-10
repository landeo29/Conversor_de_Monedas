package conversor.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APICliente {

    private static final String URL_API = "https://api.exchangerate-api.com/v4/latest/";

    public static String obtenerTasas(String codigoMoneda) throws Exception {
        String urlCompleta = URL_API + codigoMoneda;
        URL url = new URL(urlCompleta);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String inputLine;
        StringBuffer respuesta = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            respuesta.append(inputLine);
        }
        in.close();
        return respuesta.toString();
    }
}