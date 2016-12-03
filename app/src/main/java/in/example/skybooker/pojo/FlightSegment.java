package in.example.skybooker.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijay on 11/7/2016.
 */
public class FlightSegment {
    @SerializedName("DepartureDate")
    public String DepartureDate;
    @SerializedName("DepartureTime")
    public String DepartureTime;
    @SerializedName("ArrivalDate")
    public String ArrivalDate;
    @SerializedName("ArrivalTime")
    public String ArrivalTime;
    @SerializedName("FromLocation")
    public String FromLocation;
    @SerializedName("from")
    public From from;
    @SerializedName("to")
    public To to;
    @SerializedName("MarketingCarrier")
    public String MarketingCarrier;
    @SerializedName("OperatingCarrier")
    public String OperatingCarrier;
    @SerializedName("FlightNumber")
    public String FlightNumber;
    @SerializedName("AircraftType")
    public String AircraftType;
    @SerializedName("EquipmentType")
    public String EquipmentType;
    @SerializedName("ElectronicTicketing")
    public String ElectronicTicketing;
    @SerializedName("ProductDetailQualifier")
    public String ProductDetailQualifier;
    @SerializedName("Rbd")
    public String Rbd;
    @SerializedName("Cabin")
    public String Cabin;
    @SerializedName("AvlStatus")
    public String AvlStatus;
    @SerializedName("FareBasis")
    public String FareBasis;
    @SerializedName("fareType")
    public FareType fareType;

    public FlightSegment(String departureDate, String departureTime, String arrivalDate,
                         String arrivalTime, String fromLocation, From from, To to,
                         String marketingCarrier, String operatingCarrier, String flightNumber,
                         String aircraftType, String equipmentType, String electronicTicketing,
                         String productDetailQualifier, String rbd, String cabin, String avlStatus,
                         String fareBasis, FareType fareType) {
        DepartureDate = departureDate;
        DepartureTime = departureTime;
        ArrivalDate = arrivalDate;
        ArrivalTime = arrivalTime;
        FromLocation = fromLocation;
        this.from = from;
        this.to = to;
        MarketingCarrier = marketingCarrier;
        OperatingCarrier = operatingCarrier;
        FlightNumber = flightNumber;
        AircraftType = aircraftType;
        EquipmentType = equipmentType;
        ElectronicTicketing = electronicTicketing;
        ProductDetailQualifier = productDetailQualifier;
        Rbd = rbd;
        Cabin = cabin;
        AvlStatus = avlStatus;
        FareBasis = fareBasis;
        this.fareType = fareType;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }
    public String getDepartureTime() {

        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }
    public String getArrivalDate() {

        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        ArrivalDate = arrivalDate;
    }
    public String getArrivalTime() {

        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getFromLocation() {

        return FromLocation;
    }

    public void setFromLocation(String fromLocation) {
        FromLocation = fromLocation;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public To getTo() {
        return to;
    }

    public void setTo(To to) {
        this.to = to;
    }

    public String getMarketingCarrier() {
        return MarketingCarrier;
    }

    public void setMarketingCarrier(String marketingCarrier) {
        MarketingCarrier = marketingCarrier;
    }

    public String getOperatingCarrier() {
        return OperatingCarrier;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        OperatingCarrier = operatingCarrier;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        FlightNumber = flightNumber;
    }

    public String getAircraftType() {
        return AircraftType;
    }

    public void setAircraftType(String aircraftType) {
        AircraftType = aircraftType;
    }

    public String getElectronicTicketing() {
        return ElectronicTicketing;
    }

    public void setElectronicTicketing(String electronicTicketing) {
        ElectronicTicketing = electronicTicketing;
    }

    public String getEquipmentType() {

        return EquipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        EquipmentType = equipmentType;
    }

    public String getProductDetailQualifier() {
        return ProductDetailQualifier;
    }

    public void setProductDetailQualifier(String productDetailQualifier) {
        ProductDetailQualifier = productDetailQualifier;
    }

    public String getRbd() {
        return Rbd;
    }

    public void setRbd(String rbd) {
        Rbd = rbd;
    }

    public String getCabin() {
        return Cabin;
    }

    public void setCabin(String cabin) {
        Cabin = cabin;
    }

    public String getAvlStatus() {
        return AvlStatus;
    }

    public void setAvlStatus(String avlStatus) {
        AvlStatus = avlStatus;
    }


    public String getFareBasis() {

        return FareBasis;
    }

    public void setFareBasis(String fareBasis) {
        FareBasis = fareBasis;
    }
    public FareType getFareType() {
        return fareType;
    }

    public void setFareType(FareType fareType) {
        this.fareType = fareType;
    }

}
