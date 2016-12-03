package in.example.skybooker.flightsearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nbhag on 10/2/2016.
 */
public class TravellersObject implements Serializable{
    String from_City,to_City,departure_Date,class_S;
    HashMap<String, Integer> passengers;
    int id,count;
    String return_Date,fragmentKey;
    ArrayList<String> toArray,fromArray,dateArray;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReturn_Date() {
        return return_Date;
    }

    public void setReturn_Date(String return_Date) {
        this.return_Date = return_Date;
    }

    public String getFragmentKey() {
        return fragmentKey;
    }

    public void setFragmentKey(String fragmentKey) {
        this.fragmentKey = fragmentKey;
    }

    public String getFrom_City() {
        return from_City;
    }

    public void setFrom_City(String from_City) {
        this.from_City = from_City;
    }

    public String getTo_City() {
        return to_City;
    }

    public void setTo_City(String to_City) {
        this.to_City = to_City;
    }

    public String getDeparture_Date() {
        return departure_Date;
    }

    public void setDeparture_Date(String departure_Date) {
        this.departure_Date = departure_Date;
    }

    public String getClass_S() {
        return class_S;
    }

    public void setClass_S(String class_S) {
        this.class_S = class_S;
    }


    public HashMap<String, Integer> getPassengers() {
        return passengers;
    }

    public ArrayList<String> getToArray() {
        return toArray;
    }

    public void setToArray(ArrayList<String> toArray) {
        this.toArray = toArray;
    }

    public ArrayList<String> getFromArray() {
        return fromArray;
    }

    public void setFromArray(ArrayList<String> fromArray) {
        this.fromArray = fromArray;
    }

    public ArrayList<String> getDateArray() {
        return dateArray;
    }

    public void setDateArray(ArrayList<String> dateArray) {
        this.dateArray = dateArray;
    }

    public void setPassengers(HashMap<String, Integer> passengers) {
        this.passengers = passengers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
