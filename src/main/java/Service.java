

import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Service {

    /**
     * Метод отправляет GET запрос на url + id региона, обрабатывает полученный json файл,
     * подсчитывает кол-во активных записей и возвращает их количество
     *
     * @param code - Код региона
     * @return - Количество активных записей
     */
    public static int getActiveRecords(int code) throws IOException {
        int count = 0;

        URL url = new URL("https://api.spimex.com/otc/lookup-tables/" + code);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");

        try (final BufferedReader jsonReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {

            String fullJsonText = readAll(jsonReader);

            JSONObject json = new JSONObject(fullJsonText);
            JSONArray jsonArray = json.getJSONArray("records");

            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).isNull("BlockDate") || jsonArray.getJSONObject(i).get("BlockDate") == "") {
                    ++count;
                }
            }
        } catch (final Exception ex) {
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
