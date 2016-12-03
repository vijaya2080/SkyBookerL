package in.example.skybooker.pojo;

/**
 * Created by nbhag on 11/22/2016.
 */
public class FlightSegmentPojo {

    String  departureDate,departureTime,arrivalDate,arrivalTime, toLocation,fromLocationStr,terminal,
            marketingCarrier,operatingCarrier,flightNumber,aircraftType,equipmentType,
            electronicTicket,productDetailsQua,rbd,cabin,avlStaus,fareBasis,flightSegmentStr,fromLocat;

    public FlightSegmentPojo(String departureDate, String departureTime, String arrivalDate, String arrivalTime, String toLocation,
                             String fromLocationStr, String terminal, String marketingCarrier, String operatingCarrier,
                             String flightNumber, String aircraftType, String equipmentType, String electronicTicket,
                             String productDetailsQua, String rbd, String cabin, String avlStaus, String fareBasis, String flightSegmentStr) {

        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.toLocation = toLocation;
        this.fromLocationStr = fromLocationStr;
        this.terminal = terminal;
        this.marketingCarrier = marketingCarrier;
        this.operatingCarrier = operatingCarrier;
        this.flightNumber = flightNumber;
        this.aircraftType = aircraftType;
        this.equipmentType = equipmentType;
        this.electronicTicket = electronicTicket;
        this.productDetailsQua = productDetailsQua;
        this.rbd = rbd;
        this.cabin = cabin;
        this.avlStaus = avlStaus;
        this.fareBasis = fareBasis;
        this.flightSegmentStr = flightSegmentStr;
    }

    public FlightSegmentPojo() {
    }

    public String getFromLocat() {
        return fromLocat;
    }

    public void setFromLocat(String fromLocat) {
        this.fromLocat = fromLocat;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getFromLocationStr() {
        return fromLocationStr;
    }

    public void setFromLocationStr(String fromLocationStr) {
        this.fromLocationStr = fromLocationStr;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getMarketingCarrier() {
        return marketingCarrier;
    }

    public void setMarketingCarrier(String marketingCarrier) {
        this.marketingCarrier = marketingCarrier;
    }

    public String getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getElectronicTicket() {
        return electronicTicket;
    }

    public void setElectronicTicket(String electronicTicket) {
        this.electronicTicket = electronicTicket;
    }

    public String getProductDetailsQua() {
        return productDetailsQua;
    }

    public void setProductDetailsQua(String productDetailsQua) {
        this.productDetailsQua = productDetailsQua;
    }

    public String getRbd() {
        return rbd;
    }

    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getAvlStaus() {
        return avlStaus;
    }

    public void setAvlStaus(String avlStaus) {
        this.avlStaus = avlStaus;
    }

    public String getFareBasis() {
        return fareBasis;
    }

    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

    public String getFlightSegmentStr() {
        return flightSegmentStr;
    }

    public void setFlightSegmentStr(String flightSegmentStr) {
        this.flightSegmentStr = flightSegmentStr;
    }
}
