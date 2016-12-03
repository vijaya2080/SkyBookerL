package in.example.skybooker.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijay on 11/7/2016.
 */
public class FlightDetails {
    @SerializedName("FlightSegment")
    public FlightSegment flightSegment;
    @SerializedName("BaggageInformation")
    public BaggageInformation baggageInformation;

    public FlightDetails(FlightSegment flightSegment, BaggageInformation baggageInformation) {
        this.flightSegment = flightSegment;
        this.baggageInformation = baggageInformation;
    }

    public BaggageInformation getBaggageInformation() {
        return baggageInformation;
    }

    public void setBaggageInformation(BaggageInformation baggageInformation) {
        this.baggageInformation = baggageInformation;
    }

    public FlightSegment getFlightSegment() {

        return flightSegment;
    }

    public void setFlightSegment(FlightSegment flightSegment) {
        this.flightSegment = flightSegment;
    }
}
