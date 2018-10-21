import lombok.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.lang.String;

@Getter
@Setter
@ToString
public class City {
    public String name;
    private String url;
    private String administrativeArea;
    private int numberOfCitizens;
    private String yearOfFound;
    public Coordinates coordinates;
    private double area;

    private static final int INFO_SIZE = 6;

    public City set_name(Element city){
        Elements info = city.select("td");
        if (info.size() == INFO_SIZE) {
            Element anchor = info.get(1).select("a").get(0);
            this.name = anchor.attr("title").split("\\(")[0];
        }
        return this;
    }


    public City parse(Element city) {
        Elements info = city.select("td");

        if (info.size() == INFO_SIZE) {

            //Set name of city
            Element for_name = info.get(1).select("a").get(0);
            if (this.name == null){
                this.set_name(city);
            }

            //Set administrative area of city
            Element for_adminArea = info.get(2).select("a").get(0);
            this.administrativeArea = for_adminArea.attr("title");

            //Set number of citizens.
            Element citizens = info.get(3).select("td").get(0);
            if (citizens.text().length() > 7){
                this.numberOfCitizens = Integer.valueOf(citizens.text().split("\\s")[0]);
            }
            else{
                String strnum = citizens.text().replaceAll("\\s","");
                this.numberOfCitizens = Integer.valueOf(strnum);
            }

            //Set year of founded
            Element for_yearFound = info.get(4).select("a").get(0);
            this.yearOfFound = for_yearFound.attr("title");

            //Set area of city
            Element for_area = info.get(5).select("td").get(0);
            this.area = Double.parseDouble(for_area.text());

            //Set url
            this.url = String.format("https://uk.wikipedia.org%s", for_name.attr("href"));

            //Set coordinates
            Coordinates coord = new Coordinates();
            this.coordinates = coord.coordinates(this.url);


        }

        String result;
        result = "Інформація про місто " + this.name + "\nКількість жительів міста: " + this.numberOfCitizens +
                "\nРік заснування: " + this.yearOfFound + "\nОбласть" + this.administrativeArea + "\nПлоща міста: " +
                this.area + " км^2";
        System.out.println(result);
        return this;
    }
}