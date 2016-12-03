package in.example.skybooker.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijay on 11/7/2016.
 */
public class Recommendation {
    @SerializedName("Reference")
    public  Reference reference;
    @SerializedName("Currency")
    public String Currency;
    @SerializedName("TotalFare")
    public String TotalFare;
    @SerializedName("TotalFareTax")
    public String TotalFareTax;
    @SerializedName("NumberType")
    public String NumberType;
    @SerializedName("Adult")
    public String Adult;
    @SerializedName("AdultTotalFare")
    public String AdultTotalFare;
    @SerializedName("AdultTotalTax")
    public String AdultTotalTax;
    @SerializedName("Child")
    public String Child;
    @SerializedName("ChildTotalFare")
    public String ChildTotalFare;
    @SerializedName("ChildTotalTax")
    public String ChildTotalTax;
    @SerializedName("FareRule")
    public String FareRule;
    @SerializedName("CabinClass")
    public String CabinClass;
    @SerializedName("UserName")
    public String UserName;

    public void Recommendation()
    {

    }

    public Recommendation(Reference reference, String currency, String totalFare,
                          String totalFareTax, String numberType, String adult,
                          String adultTotalFare, String adultTotalTax, String child,
                          String childTotalFare, String childTotalTax, String fareRule,
                          String cabinClass, String userName) {
        this.reference = reference;
        Currency = currency;
        TotalFare = totalFare;
        TotalFareTax = totalFareTax;
        NumberType = numberType;
        Adult = adult;
        AdultTotalFare = adultTotalFare;
        AdultTotalTax = adultTotalTax;
        Child = child;
        ChildTotalFare = childTotalFare;
        ChildTotalTax = childTotalTax;
        FareRule = fareRule;
        CabinClass = cabinClass;
        UserName = userName;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getTotalFare() {
        return TotalFare;
    }

    public void setTotalFare(String totalFare) {
        TotalFare = totalFare;
    }

    public String getTotalFareTax() {
        return TotalFareTax;
    }

    public void setTotalFareTax(String totalFareTax) {
        TotalFareTax = totalFareTax;
    }

    public String getNumberType() {
        return NumberType;
    }

    public void setNumberType(String numberType) {
        NumberType = numberType;
    }

    public String getAdult() {
        return Adult;
    }

    public void setAdult(String adult) {
        Adult = adult;
    }

    public String getAdultTotalFare() {
        return AdultTotalFare;
    }

    public void setAdultTotalFare(String adultTotalFare) {
        AdultTotalFare = adultTotalFare;
    }

    public String getAdultTotalTax() {
        return AdultTotalTax;
    }

    public void setAdultTotalTax(String adultTotalTax) {
        AdultTotalTax = adultTotalTax;
    }

    public String getChild() {
        return Child;
    }

    public void setChild(String child) {
        Child = child;
    }

    public String getChildTotalFare() {
        return ChildTotalFare;
    }

    public void setChildTotalFare(String childTotalFare) {
        ChildTotalFare = childTotalFare;
    }

    public String getChildTotalTax() {
        return ChildTotalTax;
    }

    public void setChildTotalTax(String childTotalTax) {
        ChildTotalTax = childTotalTax;
    }

    public String getFareRule() {
        return FareRule;
    }

    public void setFareRule(String fareRule) {
        FareRule = fareRule;
    }

    public String getCabinClass() {
        return CabinClass;
    }

    public void setCabinClass(String cabinClass) {
        CabinClass = cabinClass;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
