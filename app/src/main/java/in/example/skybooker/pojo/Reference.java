package in.example.skybooker.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijay on 11/7/2016.
 */
public class Reference {
    @SerializedName("Outbound")
    public String Outbound;

    public Reference(String outbound) {
        Outbound = outbound;
    }
}
