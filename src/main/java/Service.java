

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Service {

    private static final String API_URL = "https://api.spimex.com/otc/lookup-tables/1";

    /**
     * Метод отправляет GET запрос, обрабатывает полученный json файл,
     * подсчитывает кол-во активных записей и возвращает их количество
     *
     * @param areaCode - Код региона
     * @return - Количество активных записей
     */
    public static int getCountActiveRecords(String areaCode) throws IOException {
        int count = 0;

        URL url = new URL(API_URL);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");

        try (final BufferedReader jsonReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {

            String fullJsonText = readAll(jsonReader);

            JSONObject json = new JSONObject(fullJsonText);
            JSONArray jsonArray = json.getJSONArray("records");

            String inn = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                inn = (String) jsonArray.getJSONObject(i).get("INN");
                if (!inn.isEmpty()) {
                    if (inn.substring(0, 2).equals(areaCode)) {
                        if (jsonArray.getJSONObject(i).get("BlockDate") == "") {
                            ++count;
                        }
                    }
                }
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    private static String readAll(BufferedReader jsonReader) throws IOException {
        StringBuilder builder = new StringBuilder();
        int charIndex;
        while ((charIndex = jsonReader.read()) != -1) {
            builder.append((char) charIndex);
        }
        return builder.toString();
    }
}
