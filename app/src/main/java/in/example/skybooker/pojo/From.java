package in.example.skybooker.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijay on 11/7/2016.
 */
public class From {


    @SerializedName("Location")
    public String Location;
    public void setLocation(String location) {
        Location = location;
    }
    public String getLocation() {
        return Location;
    }

    public From(String location) {
        Location = location;
    }


}
