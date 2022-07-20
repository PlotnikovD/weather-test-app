import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class WeatherTest {
    static String key = "1672fe1157c099911b4a61b5b3d57e93";
    static String getContent(String city){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city +"&appid=" + key +
                    "&units=metric&lang=en");
            URLConnection urlConnection = url.openConnection();
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
        } catch (Exception e) {
            System.out.println("Incorrect data entry ");
        }
        return content.toString();
    }

    public static void main(String[] args) {
        System.out.println("Enter city: ");
        Scanner scanner = new Scanner(System.in);
        String yourCity = scanner.nextLine();
        String jsonContent = getContent(yourCity);
        LocalDateTime localDateTime = LocalDateTime.now();

        JSONObject object = new JSONObject(jsonContent);

        System.out.println("City: " + yourCity);
        System.out.println(("Country: " + object.getJSONObject("sys").getString("country")));
        System.out.println("Temperature: " + object.getJSONObject("main").getDouble("temp") + " ℃");
        System.out.println("Current time: " + localDateTime.truncatedTo(ChronoUnit.SECONDS));
    }
}


