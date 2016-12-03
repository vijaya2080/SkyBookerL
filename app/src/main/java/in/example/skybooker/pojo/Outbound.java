package in.example.skybooker.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijay on 11/7/2016.
 */
public class Outbound {
    @SerializedName("Duration")
    public String Duration;
    @SerializedName("Airline")
    public String Airline;
    @SerializedName("FlightDetails")
    public FlightDetails flightDetails;

    public Outbound()
    {

    }

    public Outbound(String duration, String airline, FlightDetails flightDetails) {
        Duration = duration;
        Airline = airline;
        this.flightDetails = flightDetails;
    }


    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getAirline() {
        return Airline;
    }

    public void setAirline(String airline) {
        Airline = airline;
    }


    public FlightDetails getFlightDetails() {

        return flightDetails;
    }

    public void setFlightDetails(FlightDetails flightDetails) {
        this.flightDetails = flightDetails;
    }
}
