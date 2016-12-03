package in.example.skybooker.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijay on 11/7/2016.
 */
public class BaggageInformation {
    @SerializedName("FreeAllowance")
    public String FreeAllowance;
    @SerializedName("QuantityCode")
    public String QuantityCode;

    public BaggageInformation(String freeAllowance, String quantityCode) {
        FreeAllowance = freeAllowance;
        QuantityCode = quantityCode;
    }

    public String getQuantityCode() {
        return QuantityCode;
    }

    public void setQuantityCode(String quantityCode) {
        QuantityCode = quantityCode;
    }

    public String getFreeAllowance() {

        return FreeAllowance;
    }

    public void setFreeAllowance(String freeAllowance) {
        FreeAllowance = freeAllowance;
    }
}
