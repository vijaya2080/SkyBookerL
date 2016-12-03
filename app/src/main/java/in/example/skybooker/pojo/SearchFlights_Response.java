package in.example.skybooker.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vijay on 11/7/2016.
 */
public class SearchFlights_Response {
    @SerializedName("-Count")
  public   String  Count;
    @SerializedName("-SessionId")
  public   String SessionId;
    @SerializedName("-ExecutedIn")
  public   String ExecutedIn;
    @SerializedName("FlightResult")
  public List<FlightResult> flightResultList;

    public SearchFlights_Response(String count, String sessionId, String executedIn, List<FlightResult> flightResultList) {
        Count = count;
        SessionId = sessionId;
        ExecutedIn = executedIn;
        this.flightResultList = flightResultList;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    public String getExecutedIn() {
        return ExecutedIn;
    }

    public void setExecutedIn(String executedIn) {
        ExecutedIn = executedIn;
    }

    public List<FlightResult> getFlightResultList() {
        return flightResultList;
    }

    public void setFlightResultList(List<FlightResult> flightResultList) {
        this.flightResultList = flightResultList;
    }
}
