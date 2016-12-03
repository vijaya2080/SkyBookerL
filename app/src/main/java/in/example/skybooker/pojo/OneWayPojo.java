package in.example.skybooker.pojo;

import android.os.Binder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nbhag on 11/19/2016.
 */
public class OneWayPojo extends Binder {
    String  count;
    String sessionId;
    String executedIn;
    String durationStr;
    String airlineStr;
    ArrayList<String> fromLocationStrArray;
    ArrayList<String> departureDateArray,departureTimeArray,arrivalDateArray,arrivalTimeArray,toLocationArray,fromLocatArray,terminalArray,marketingCarrierArr;
    ArrayList<String> operatingCarrierArr,flightNumberArr,aircraftTypeArr,equipmentTypeArr,electronicTicketArr,productDetailsQuaArr,rbdArray;
    ArrayList<String> cabinArray,avlStausArr,fareBasisArr,flightSegmentStrArray;
    ArrayList<FlightSegmentPojo> segmentArray;
    String freeAllowance;
    String quantityCode;
    String outboundStr;
    String currecy;
    String totalFare;
    String totalFareTax;
    String numberType;
    String adult;
    String adultToatalFare;
    String adultTotalTax;
    String child;
    String childTotalFare;
    String childTotalTax;
    String fareRule;
    String cabinClass;
    String userName;
    HashMap<Integer,ArrayList<String>> onewayHP,onewayHMTo;

    public OneWayPojo(String count, String sessionId, String executedIn, String durationStr, String airlineStr, ArrayList<String> fromLocationStrArray, ArrayList<String> departureDateArray, ArrayList<String> departureTimeArray, ArrayList<String> arrivalDateArray, ArrayList<String> arrivalTimeArray, ArrayList<String> toLocationArray, ArrayList<String> fromLocatArray, ArrayList<String> terminalArray, ArrayList<String> marketingCarrierArr, ArrayList<String> operatingCarrierArr, ArrayList<String> flightNumberArr, ArrayList<String> aircraftTypeArr, ArrayList<String> equipmentTypeArr, ArrayList<String> electronicTicketArr, ArrayList<String> productDetailsQuaArr, ArrayList<String> rbdArray, ArrayList<String> cabinArray, ArrayList<String> avlStausArr, ArrayList<String> fareBasisArr, ArrayList<String> flightSegmentStrArray, String freeAllowance, String quantityCode, String outboundStr, String currecy, String totalFare, String totalFareTax, String numberType, String adult, String adultToatalFare, String adultTotalTax, String child, String childTotalFare, String childTotalTax, String fareRule, String cabinClass, String userName, HashMap<Integer, ArrayList<String>> onewayHP, HashMap<Integer, ArrayList<String>> onewayHMTo) {
        this.count = count;
        this.sessionId = sessionId;
        this.executedIn = executedIn;
        this.durationStr = durationStr;
        this.airlineStr = airlineStr;
        this.fromLocationStrArray = fromLocationStrArray;
        this.departureDateArray = departureDateArray;
        this.departureTimeArray = departureTimeArray;
        this.arrivalDateArray = arrivalDateArray;
        this.arrivalTimeArray = arrivalTimeArray;
        this.toLocationArray = toLocationArray;
        this.fromLocatArray = fromLocatArray;
        this.terminalArray = terminalArray;
        this.marketingCarrierArr = marketingCarrierArr;
        this.operatingCarrierArr = operatingCarrierArr;
        this.flightNumberArr = flightNumberArr;
        this.aircraftTypeArr = aircraftTypeArr;
        this.equipmentTypeArr = equipmentTypeArr;
        this.electronicTicketArr = electronicTicketArr;
        this.productDetailsQuaArr = productDetailsQuaArr;
        this.rbdArray = rbdArray;
        this.cabinArray = cabinArray;
        this.avlStausArr = avlStausArr;
        this.fareBasisArr = fareBasisArr;
        this.flightSegmentStrArray = flightSegmentStrArray;
        this.freeAllowance = freeAllowance;
        this.quantityCode = quantityCode;
        this.outboundStr = outboundStr;
        this.currecy = currecy;
        this.totalFare = totalFare;
        this.totalFareTax = totalFareTax;
        this.numberType = numberType;
        this.adult = adult;
        this.adultToatalFare = adultToatalFare;
        this.adultTotalTax = adultTotalTax;
        this.child = child;
        this.childTotalFare = childTotalFare;
        this.childTotalTax = childTotalTax;
        this.fareRule = fareRule;
        this.cabinClass = cabinClass;
        this.userName = userName;
        this.onewayHP = onewayHP;
        this.onewayHMTo = onewayHMTo;
    }

    public OneWayPojo() {
    }

    public ArrayList<FlightSegmentPojo> getSegmentArray() {
        return segmentArray;
    }

    public void setSegmentArray(ArrayList<FlightSegmentPojo> segmentArray) {
        this.segmentArray = segmentArray;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getExecutedIn() {
        return executedIn;
    }

    public void setExecutedIn(String executedIn) {
        this.executedIn = executedIn;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    public String getAirlineStr() {
        return airlineStr;
    }

    public void setAirlineStr(String airlineStr) {
        this.airlineStr = airlineStr;
    }

    public ArrayList<String > getFromLocation() {
        return fromLocationStrArray;
    }

    public void setFromLocation(ArrayList<String> fromLocationStrArray) {
        this.fromLocationStrArray = fromLocationStrArray;
    }

    public ArrayList<String> getDepartureDateArray() {
        return departureDateArray;
    }

    public void setDepartureDateArray(ArrayList<String> departureDateArray) {
        this.departureDateArray = departureDateArray;
    }

    public ArrayList<String> getDepartureTimeArray() {
        return departureTimeArray;
    }

    public void setDepartureTimeArray(ArrayList<String> departureTimeArray) {
        this.departureTimeArray = departureTimeArray;
    }

    public ArrayList<String> getArrivalDateArray() {
        return arrivalDateArray;
    }

    public void setArrivalDateArray(ArrayList<String> arrivalDateArray) {
        this.arrivalDateArray = arrivalDateArray;
    }

    public ArrayList<String> getArrivalTimeArray() {
        return arrivalTimeArray;
    }

    public void setArrivalTimeArray(ArrayList<String> arrivalTimeArray) {
        this.arrivalTimeArray = arrivalTimeArray;
    }

    public ArrayList<String> getToLocationArray() {
        return toLocationArray;
    }

    public void setToLocationArray(ArrayList<String> toLocationArray) {
        this.toLocationArray = toLocationArray;
    }

    public ArrayList<String> getFromLocatArray() {
        return fromLocatArray;
    }

    public void setFromLocatArray(ArrayList<String> fromLocatArray) {
        this.fromLocatArray = fromLocatArray;
    }

    public ArrayList<String> getTerminalArray() {
        return terminalArray;
    }

    public void setTerminalArray(ArrayList<String> terminalArray) {
        this.terminalArray = terminalArray;
    }

    public ArrayList<String> getMarketingCarrierArr() {
        return marketingCarrierArr;
    }

    public void setMarketingCarrierArr(ArrayList<String> marketingCarrierArr) {
        this.marketingCarrierArr = marketingCarrierArr;
    }

    public ArrayList<String> getOperatingCarrierArr() {
        return operatingCarrierArr;
    }

    public void setOperatingCarrierArr(ArrayList<String> operatingCarrierArr) {
        this.operatingCarrierArr = operatingCarrierArr;
    }

    public ArrayList<String> getFlightNumberArr() {
        return flightNumberArr;
    }

    public void setFlightNumberArr(ArrayList<String> flightNumberArr) {
        this.flightNumberArr = flightNumberArr;
    }

    public ArrayList<String> getAircraftTypeArr() {
        return aircraftTypeArr;
    }

    public void setAircraftTypeArr(ArrayList<String> aircraftTypeArr) {
        this.aircraftTypeArr = aircraftTypeArr;
    }

    public ArrayList<String> getEquipmentTypeArr() {
        return equipmentTypeArr;
    }

    public void setEquipmentTypeArr(ArrayList<String> equipmentTypeArr) {
        this.equipmentTypeArr = equipmentTypeArr;
    }

    public ArrayList<String> getElectronicTicketArr() {
        return electronicTicketArr;
    }

    public void setElectronicTicketArr(ArrayList<String> electronicTicketArr) {
        this.electronicTicketArr = electronicTicketArr;
    }

    public ArrayList<String> getProductDetailsQuaArr() {
        return productDetailsQuaArr;
    }

    public void setProductDetailsQuaArr(ArrayList<String> productDetailsQuaArr) {
        this.productDetailsQuaArr = productDetailsQuaArr;
    }

    public ArrayList<String> getRbdArray() {
        return rbdArray;
    }

    public void setRbdArray(ArrayList<String> rbdArray) {
        this.rbdArray = rbdArray;
    }

    public ArrayList<String> getCabinArray() {
        return cabinArray;
    }

    public void setCabinArray(ArrayList<String> cabinArray) {
        this.cabinArray = cabinArray;
    }

    public ArrayList<String> getAvlStausArr() {
        return avlStausArr;
    }

    public void setAvlStausArr(ArrayList<String> avlStausArr) {
        this.avlStausArr = avlStausArr;
    }

    public ArrayList<String> getFareBasisArr() {
        return fareBasisArr;
    }

    public void setFareBasisArr(ArrayList<String> fareBasisArr) {
        this.fareBasisArr = fareBasisArr;
    }

    public ArrayList<String> getFlightSegmentStrArray() {
        return flightSegmentStrArray;
    }

    public void setFlightSegmentStrArray(ArrayList<String> flightSegmentStrArray) {
        this.flightSegmentStrArray = flightSegmentStrArray;
    }

    public String getFreeAllowance() {
        return freeAllowance;
    }

    public void setFreeAllowance(String freeAllowance) {
        this.freeAllowance = freeAllowance;
    }

    public String getQuantityCode() {
        return quantityCode;
    }

    public void setQuantityCode(String quantityCode) {
        this.quantityCode = quantityCode;
    }

    public String getOutboundStr() {
        return outboundStr;
    }

    public void setOutboundStr(String outboundStr) {
        this.outboundStr = outboundStr;
    }

    public String getCurrecy() {
        return currecy;
    }

    public void setCurrecy(String currecy) {
        this.currecy = currecy;
    }

    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }

    public String getTotalFareTax() {
        return totalFareTax;
    }

    public void setTotalFareTax(String totalFareTax) {
        this.totalFareTax = totalFareTax;
    }

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getAdultToatalFare() {
        return adultToatalFare;
    }

    public void setAdultToatalFare(String adultToatalFare) {
        this.adultToatalFare = adultToatalFare;
    }

    public String getAdultTotalTax() {
        return adultTotalTax;
    }

    public void setAdultTotalTax(String adultTotalTax) {
        this.adultTotalTax = adultTotalTax;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getChildTotalFare() {
        return childTotalFare;
    }

    public void setChildTotalFare(String childTotalFare) {
        this.childTotalFare = childTotalFare;
    }

    public String getChildTotalTax() {
        return childTotalTax;
    }

    public void setChildTotalTax(String childTotalTax) {
        this.childTotalTax = childTotalTax;
    }

    public String getFareRule() {
        return fareRule;
    }

    public void setFareRule(String fareRule) {
        this.fareRule = fareRule;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashMap<Integer, ArrayList<String>> getOnewayHP() {
        return onewayHP;
    }

    public void setOnewayHP(HashMap<Integer, ArrayList<String>> onewayHP) {
        this.onewayHP = onewayHP;
    }

    public HashMap<Integer, ArrayList<String>> getOnewayHMTo() {
        return onewayHMTo;
    }

    public void setOnewayHMTo(HashMap<Integer, ArrayList<String>> onewayHMTo) {
        this.onewayHMTo = onewayHMTo;
    }


}
