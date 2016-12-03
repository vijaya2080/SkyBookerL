package in.example.skybooker.flightsearch;

/**
 * Created by siris on 10/29/2016.
 */
public class ViewPagerEnum {
    String to_City,from_City,date;



    public ViewPagerEnum(String to_City, String from_City, String date) {
        this.to_City = to_City;
        this.from_City = from_City;
        this.date = date;
    }

    public String getTo_City() {
        return to_City;
    }

    public void setTo_City(String to_City) {
        this.to_City = to_City;
    }

    public String getFrom_City() {
        return from_City;
    }

    public void setFrom_City(String from_City) {
        this.from_City = from_City;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
