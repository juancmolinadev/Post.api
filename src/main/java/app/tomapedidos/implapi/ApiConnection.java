package app.tomapedidos.implapi;

import app.tomapedidos.sync.logger.TomaPedidosSyncLogger;
import net.comtor.http.HttpClient;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import net.comtor.http.HttpClientException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiConnection {

    public static List<JSONObject> createRequest(String urlDirection) {

        // inicio de la conexion
        HttpClient client = new HttpClient(120000, 120000, 120000);

        List<JSONObject> jsonObjectsList = new LinkedList<>();
        try {
            // solicitud a la api y alamacenamiento de la respuesta
            JSONArray jsonArray = client.get(urlDirection, JSONArray.class);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                jsonObjectsList.add(jsonObject);
            }
        } catch (IOException | HttpClientException ex) {
            TomaPedidosSyncLogger.log("Error al conectar con la api, La pagina dice: " + ex);
        }
        return jsonObjectsList;
    }
}
