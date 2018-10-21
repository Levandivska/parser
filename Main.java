import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;


public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String city_name = sc.nextLine();

        String url = "https://uk.wikipedia.org/wiki/%D0%9C%D1%96%D1%81%D1%82%D0%B0_%D0%A3%D0%BA%D1%80%D0%B0%D1%97%D0%BD%D0%B8_(%D0%B7%D0%B0_%D0%B0%D0%BB%D1%84%D0%B0%D0%B2%D1%96%D1%82%D0%BE%D0%BC)";
        Document doc = Jsoup.connect(url).get();
        Elements cities = doc.select("table tr");

        for (Element city : cities) {
            City myCity = new City();
            myCity.set_name(city);
            if (myCity.name != null) {
                if (myCity.name.toLowerCase().equals(city_name.toLowerCase())){
                    myCity.parse(city);
                    WeatherForecaster myforecast = new WeatherForecaster();
                    myforecast.forecast(myCity);
                    break;
                }
            }
        }
    }
}
