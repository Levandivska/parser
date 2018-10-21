import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.lang.String;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//TODO Create class that represents coordinates

@Getter
@Setter
@ToString
public class Coordinates {
    public String lat = "0";
    public String lon = "0";

    @SneakyThrows
    public Coordinates coordinates(String url){
        Document docc = Jsoup.connect(url).get();
        String coord = docc.select("[class$=geo]").text();

        if (coord.equals("")){
            return this;
        }
        this.lat = coord.split("\\s")[0].replaceAll(";", "");
        this.lon = coord.split("\\s")[1].replaceAll(";", "");
        return this;
    }
}
