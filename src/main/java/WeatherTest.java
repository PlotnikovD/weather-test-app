import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherTest {
    static String key = "1672fe1157c099911b4a61b5b3d57e93";
    static String getContent(String city){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city +"&appid=" + key +
                    "&units=metric");
            URLConnection urlConnection = url.openConnection();
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
        } catch (Exception e) {
            System.out.println("ошибка");
        }
        return content.toString();
    }

    public static void main(String[] args) {
        String yourCity = "Chelyabinsk";
        String jsonContent = getContent(yourCity);

        JSONObject object = new JSONObject(jsonContent);
        System.out.println("Weather in " + yourCity + " " + object.getJSONObject("main").getDouble("temp") + " ℃");

    }
}


