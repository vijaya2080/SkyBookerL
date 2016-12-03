package in.example.skybooker.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijay on 11/7/2016.
 */
public class FareType {

    @SerializedName("FlightSegment")
    public String FlightSegment;

    public FareType(String flightSegment) {
        FlightSegment = flightSegment;
    }

    public void setFlightSegment(String flightSegment) {
        FlightSegment = flightSegment;
    }

    public String getFlightSegment() {
        return FlightSegment;
    }



}
