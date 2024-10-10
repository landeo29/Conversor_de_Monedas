package conversor.modelo;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MonedasDisponibles {

    public static Map<String, Double> parsearJSON(String respuestaAPI) {
        Map<String, Double> tasasCambio = new HashMap<>();

        JSONObject jsonObject = new JSONObject(respuestaAPI);
        JSONObject rates = jsonObject.getJSONObject("rates");

        for (String clave : rates.keySet()) {
            tasasCambio.put(clave, rates.getDouble(clave));
        }

        return tasasCambio;
    }
}