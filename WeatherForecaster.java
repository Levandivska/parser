import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.SneakyThrows;
import org.json.JSONException;
import org.json.JSONObject;


public class WeatherForecaster {

    @SneakyThrows
    public WeatherForecaster forecast(City city){
        String result = "";
        String url = "http://api.apixu.com/v1/current.json?key=f6da3a783d34446f8f4120423180410&q=";
        try {
            HttpResponse<JsonNode> response = Unirest.get(url + city.coordinates.lat + "%20"+ city.coordinates.lon).
                    header("Accept", "application/json")
                    .asJson();
            JSONObject json = response.getBody().getObject();
            Object tmp_c = json.getJSONObject("current").get("temp_c");
            Object feellike_c = json.getJSONObject("current").get("feelslike_c");
            result = "Температура " + tmp_c + " °C" +"\nВідчуття як " +
                    feellike_c + " °C";
        }
        catch (UnirestException e){
        }
        catch (IllegalArgumentException d){
        }
        catch (JSONException d){
        }

        System.out.println("\n\nПрогноз погоди:");
        if (result.equals("")){
            System.out.println("Sorry we didn`t support this city");
        }
        else{
            System.out.println(result);
        }

        return null;
    }

}
