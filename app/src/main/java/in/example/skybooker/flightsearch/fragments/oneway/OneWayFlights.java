package in.example.skybooker.flightsearch.fragments.oneway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.DecoderException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import in.example.skybooker.FlexibleDates;
import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.review.ReviewActivity;
import in.example.skybooker.SingleTon;
import in.example.skybooker.filters.FiltersActivity;
import in.example.skybooker.filters.PopupAdapter;
import in.example.skybooker.pojo.FlightSegmentPojo;
import in.example.skybooker.pojo.OneWayPojo;

public class OneWayFlights extends AppCompatActivity implements OneWayFlightsCustomAdapter.OnSecurityQnClickListener,Serializable,PopupAdapter.OnSecurityQnClickListener{
        RecyclerView rvTraveller,rvPopup;
        PopupAdapter adpt;
        ImageView toolIcon,sortImg,filterImg,nearbyImg,flexiImg;
        ArrayList<String> PopupArray;
        TextView tv_fromCity,tv_toCity,tv_fromDate,tv_toDate;
        RelativeLayout toolbarlayout;
        RecyclerView relatedFlightsRv;
        OneWayFlightsCustomAdapter adapter;
        LinearLayout pagerLayout;
        RelativeLayout sortLayout,filtersLayout,nearLayout,flexibleLayout;
        RelativeLayout LowestPriseGrouped,LowestPriseUnGrouped,DepartTime,Stops,CheapOpick;
        TextView tv_PopupText1,tv_PopupText2,tv_PopupText3,tv_PopupText4,tv_PopupText5;
        ArrayList<String> toCityArray,fromCityArray;
        ArrayList<OneWayPojo> flightsList=new ArrayList<>();
        ArrayList<FlightSegmentPojo> flightSegmentPojoArr ;
        View popupView;
        public static PopupWindow popupWindow;
        String key=" ",count,sessionId,executedIn,durationStr,airlineStr,departureDate,departureTime,arrivalDate,arrivalTime,
                    toLocation,fromLocat,marketingCarrier,operatingCarrier,flightNumber,aircraftType,equipmentType,electronicTicket,productDetailsQua,
                    rbd,cabin,avlStaus,fareBasis,flightSegmentStr,freeAllowance,quantityCode,outboundStr,currecy,totalFare,totalFareTax,
                    numberType,adult,adultToatalFare,adultTotalTax,child,childTotalFare,childTotalTax,fareRule,cabinClass,userName;

        public JSONObject localJSresp,searchFlightResponse,outbound,recomendation,flightDetails,fromObj,to,flightSegment,reference,baggageInfo,
                fareType,flySegArrayObj;
        JSONArray flightResult = null,flySegmentsArray;
        HashMap<Integer,ArrayList<String>> onewayHash;
        ArrayList<String> onewayArray;
        ArrayList<String> departureDateArray,departureTimeArray,arrivalDateArray,arrivalTimeArray,toLocationArray,fromLocatArray,terminalArray,marketingCarrierArr;
        ArrayList<String> operatingCarrierArr,flightNumberArr,aircraftTypeArr,equipmentTypeArr,electronicTicketArr,productDetailsQuaArr,rbdArray;
        ArrayList<String> cabinArray,avlStausArr,fareBasisArr,flightSegmentStrArray,fromLocationStrArr;

        OneWayPojo pojo;
        FlightSegmentPojo segmentPojo;
        public static int rvPosition;
        SimpleDateFormat format;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_one_way_flights);


            toolbarlayout=(RelativeLayout)findViewById(R.id.oneWayFlightsToolBar);
            toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

            tv_fromCity=(TextView)toolbarlayout.findViewById(R.id.tv_fromCity);
            tv_toCity=(TextView)toolbarlayout.findViewById(R.id.tv_toCity);
            tv_fromDate=(TextView)toolbarlayout.findViewById(R.id.tv_fromJourneryDate);
            tv_toDate=(TextView)toolbarlayout.findViewById(R.id.tv_fromJourneryDate);

            pojo=new OneWayPojo();

            PopupArray=new ArrayList<>();
            PopupArray.add("Lowest Price(Grouped)");
            PopupArray.add("Lowest Price(UnGrouped)");
            PopupArray.add("Depart Time");
            PopupArray.add("Stops");
            PopupArray.add("CheapOpick(Shortest&Chaeapest)");

            pagerLayout=(LinearLayout)findViewById(R.id.oneWayFlitersLay);
            sortLayout=(RelativeLayout)pagerLayout.findViewById(R.id.oneWaySortLayout);
            filtersLayout=(RelativeLayout)pagerLayout.findViewById(R.id.oneWayFiltersLayout);
            nearLayout=(RelativeLayout)pagerLayout.findViewById(R.id.oneWayNearbyLayout);
            flexibleLayout=(RelativeLayout)pagerLayout.findViewById(R.id.oneWayFlexibleLayout);
            sortImg=(ImageView)sortLayout.findViewById(R.id.pagerImg);
            sortImg.setImageResource(R.mipmap.sort);
            filterImg=(ImageView)filtersLayout.findViewById(R.id.pagerImg);
            filterImg.setImageResource(R.mipmap.filter);
            nearbyImg=(ImageView)nearLayout.findViewById(R.id.pagerImg);
            nearbyImg.setImageResource(R.mipmap.nearby);
            flexiImg=(ImageView)flexibleLayout.findViewById(R.id.pagerImg);
            flexiImg.setImageResource(R.mipmap.flexible);


            sortLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"sort",Toast.LENGTH_SHORT).show();
                    LayoutInflater layoutInflater = (LayoutInflater)getBaseContext()
                            .getSystemService(LAYOUT_INFLATER_SERVICE);
                    popupView = layoutInflater.inflate(R.layout.popup_recycler, null);

                    rvPopup=(RecyclerView)popupView.findViewById(R.id.popuprecycler);
                    adpt = new PopupAdapter(getApplicationContext(), PopupArray,"popup");
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    rvPopup.setLayoutManager(layoutManager);
                    rvPopup.setItemAnimator(new DefaultItemAnimator());
                    adpt.setOnSecurityQnClickListener(OneWayFlights.this);
                    rvPopup.setAdapter(adpt);


                    popupWindow = new PopupWindow(
                            popupView,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    popupWindow.showAsDropDown(sortLayout, 40, -10);


                    flexibleLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(OneWayFlights.this,FlexibleDates.class));
                        }
                    });


               /* LowestPriseGrouped=(RelativeLayout)popupView.findViewById(R.id.LowestPriseGrouped);
                tv_PopupText1=(TextView)LowestPriseGrouped.findViewById(R.id.tv_PopupText);
                tv_PopupText1.setText("Lowest Price(Grouped)");

                LowestPriseUnGrouped=(RelativeLayout)popupView.findViewById(R.id.LowestPriseUnGrouped);
                tv_PopupText2=(TextView)LowestPriseUnGrouped.findViewById(R.id.tv_PopupText);
                tv_PopupText2.setText("Lowest Price(UnGrouped)");

                DepartTime=(RelativeLayout)popupView.findViewById(R.id.LowestPriseDepartTime);
                tv_PopupText3=(TextView)DepartTime.findViewById(R.id.tv_PopupText);
                tv_PopupText3.setText("Depart Time");

                Stops=(RelativeLayout)popupView.findViewById(R.id.LowestPriseStops);
                tv_PopupText4=(TextView)Stops.findViewById(R.id.tv_PopupText);
                tv_PopupText4.setText("Stops");

                CheapOpick=(RelativeLayout)popupView.findViewById(R.id.LowestPriseCheapOpick);
                tv_PopupText5=(TextView)CheapOpick.findViewById(R.id.tv_PopupText);
                tv_PopupText5.setText("CheapOpick(Shortest&Chaeapest)");*/



                }
            });

            filtersLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(OneWayFlights.this,FiltersActivity.class));
                }
            });

            toCityArray=new ArrayList<>();
            fromCityArray=new ArrayList<>();

            toCityArray.add("HYD"); toCityArray.add("VJD");toCityArray.add("RJD"); toCityArray.add("TPT");
            fromCityArray.add("HYD");fromCityArray.add("VJD");fromCityArray.add("RJD"); fromCityArray.add("TPT");


            try {
                Log.i("oneWaycalled","Before");
                oneWayParsing( );
                Log.i("oneWaycalledAfter","After");

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    private void oneWayParsing() throws JSONException {

        localJSresp= SingleTon.jsonObj1;
        Log.i("localjSresp",localJSresp+"");

        searchFlightResponse=localJSresp.getJSONObject("SearchFlights_Response");

        count=searchFlightResponse.getString("-Count");
        sessionId=searchFlightResponse.getString("-SessionId");
        executedIn=searchFlightResponse.getString("-ExecutedIn");

        flightResult=searchFlightResponse.getJSONArray("FlightResult");

        for(int i=0;i<flightResult.length();i++){

            JSONObject json = flightResult.getJSONObject(i);
            outbound=json.getJSONObject("Outbound");

            pojo=new OneWayPojo();
                pojo.setDurationStr(outbound.getString("Duration"));
                pojo.setAirlineStr(outbound.getString("Airline"));

                flightDetails=outbound.getJSONObject("FlightDetails");

                Object obj = flightDetails.get("FlightSegment");

            if(obj instanceof JSONObject) {

                flightSegment = (JSONObject) obj;

                segmentPojo=new FlightSegmentPojo();

                onewayArray=new ArrayList<>();
                onewayHash=new HashMap<>();
                flightSegmentPojoArr = new ArrayList<>();

                departureDateArray=new ArrayList<>();
                departureTimeArray=new ArrayList<>();
                arrivalDateArray=new ArrayList<>();
                arrivalTimeArray=new ArrayList<>();
                fromLocationStrArr=new ArrayList<>();
                marketingCarrierArr=new ArrayList<>();
                operatingCarrierArr=new ArrayList<>();
                flightNumberArr=new ArrayList<>();
                aircraftTypeArr=new ArrayList<>();
                equipmentTypeArr=new ArrayList<>();
                electronicTicketArr=new ArrayList<>();
                productDetailsQuaArr=new ArrayList<>();
                rbdArray=new ArrayList<>();
                cabinArray=new ArrayList<>();
                avlStausArr=new ArrayList<>();
                fareBasisArr=new ArrayList<>();
                flightSegmentStrArray=new ArrayList<>();

                segmentPojo.setDepartureDate(flightSegment.getString("DepartureDate"));
                segmentPojo.setDepartureTime(flightSegment.getString("DepartureTime"));
                Log.i("pojodeparttime@1",flightSegment.getString("DepartureTime")+" ");

                segmentPojo.setArrivalDate(flightSegment.getString("ArrivalDate"));
                segmentPojo.setArrivalTime(flightSegment.getString("ArrivalTime"));
                Log.i("pojoArrivaltime@1",flightSegment.getString("ArrivalTime")+" ");

                segmentPojo.setFromLocationStr(flightSegment.getString("FromLocation"));


                fromObj = flightSegment.getJSONObject("From");
                to = flightSegment.getJSONObject("To");
                onewayArray.add(fromObj.getString("Location"));
                onewayArray.add(to.getString("Location"));
                onewayHash.put(0,onewayArray);
                pojo.setOnewayHP(onewayHash);

                segmentPojo.setMarketingCarrier(flightSegment.getString("MarketingCarrier"));
                segmentPojo.setOperatingCarrier(flightSegment.getString("OperatingCarrier"));
                segmentPojo.setFlightNumber(flightSegment.getString("FlightNumber"));
                segmentPojo.setAircraftType(flightSegment.getString("AircraftType"));
                segmentPojo.setEquipmentType(flightSegment.getString("EquipmentType"));
                segmentPojo.setElectronicTicket(flightSegment.getString("ElectronicTicketing"));
                segmentPojo.setProductDetailsQua(flightSegment.getString("ProductDetailQualifier"));
                segmentPojo.setRbd(flightSegment.getString("Rbd"));
                segmentPojo.setCabin(flightSegment.getString("Cabin"));
                segmentPojo.setAvlStaus(flightSegment.getString("AvlStatus"));
                segmentPojo.setFareBasis(flightSegment.getString("FareBasis"));

                fareType = flightSegment.getJSONObject("FareType");
                segmentPojo.setFlightSegmentStr(fareType.getString("FlightSegment"));

                flightSegmentPojoArr.add(segmentPojo);
            }

            else if(obj instanceof JSONArray){

                    flySegmentsArray = (JSONArray)obj;
                    Log.i("JSONARRAY","jsonarrray");
                    Log.i("jsonArray",flySegmentsArray+"");

                    segmentPojo=new FlightSegmentPojo();
                    flightSegmentPojoArr = new ArrayList<>();
                    onewayArray=new ArrayList<>();

                    departureDateArray=new ArrayList<>();
                    departureTimeArray=new ArrayList<>();
                    arrivalDateArray=new ArrayList<>();
                    arrivalTimeArray=new ArrayList<>();
                    fromLocationStrArr=new ArrayList<>();
                    marketingCarrierArr=new ArrayList<>();
                    operatingCarrierArr=new ArrayList<>();
                    flightNumberArr=new ArrayList<>();
                    aircraftTypeArr=new ArrayList<>();
                    equipmentTypeArr=new ArrayList<>();
                    productDetailsQuaArr=new ArrayList<>();
                    rbdArray=new ArrayList<>();
                    cabinArray=new ArrayList<>();
                    avlStausArr=new ArrayList<>();
                    fareBasisArr=new ArrayList<>();

                    flightSegmentStrArray=new ArrayList<>();

                    for(int j=0;j<flySegmentsArray.length();j++){

                        flySegArrayObj=flySegmentsArray.getJSONObject(j);

                        segmentPojo.setDepartureDate(flySegArrayObj.getString("DepartureDate"));
                        segmentPojo.setDepartureTime(flySegArrayObj.getString("DepartureTime"));
                        Log.i("pojodeparttime",flightSegment.getString("DepartureTime")+" ");

                        segmentPojo.setArrivalDate(flySegArrayObj.getString("ArrivalDate"));
                        segmentPojo.setArrivalTime(flySegArrayObj.getString("ArrivalTime"));
                        Log.i("pojoarrivaltime",flightSegment.getString("ArrivalTime")+" ");

                        segmentPojo.setFromLocationStr(flySegArrayObj.getString("FromLocation"));


                        onewayHash=new HashMap<>();

                        fromObj=flySegArrayObj.getJSONObject("From");
                        to=flySegArrayObj.getJSONObject("To");

                        if(j==0){
                            onewayArray.add(fromObj.getString("Location"));
                            onewayArray.add(to.getString("Location"));
                        }else {
                            onewayArray.add(to.getString("Location"));
                        }


                        Log.i("OnewayArray",onewayArray+"");

                       // segmentPojo.setFromLocat(flySegArrayObj.getString(onewayArray.get(j)));
                        segmentPojo.setMarketingCarrier(flySegArrayObj.getString("MarketingCarrier"));
                        segmentPojo.setOperatingCarrier(flySegArrayObj.getString("OperatingCarrier"));
                        segmentPojo.setFlightNumber(flySegArrayObj.getString("FlightNumber"));
                        segmentPojo.setAircraftType(flySegArrayObj.getString("AircraftType"));
                        segmentPojo.setEquipmentType(flySegArrayObj.getString("EquipmentType"));
                        segmentPojo.setElectronicTicket(flySegArrayObj.getString("ElectronicTicketing"));
                        segmentPojo.setProductDetailsQua(flySegArrayObj.getString("ProductDetailQualifier"));
                        segmentPojo.setRbd(flySegArrayObj.getString("Rbd"));
                        segmentPojo.setCabin(flySegArrayObj.getString("Cabin"));
                        segmentPojo.setAvlStaus(flySegArrayObj.getString("AvlStatus"));
                        segmentPojo.setFareBasis(flySegArrayObj.getString("FareBasis"));

                        fareType=flySegArrayObj.getJSONObject("FareType");
                        segmentPojo.setFlightSegmentStr(fareType.getString("FlightSegment"));

                        flightSegmentPojoArr.add(segmentPojo);
                        Log.i("SegmentPojoArray size",flightSegmentPojoArr.size()+"");
                    }
               /* segmentPojo.setDepartureDate(departureDateArray);
                segmentPojo.setDepartureTimeArray(departureDateArray);
                pojo.setArrivalDateArray(arrivalDateArray);
                pojo.setArrivalTimeArray(arrivalTimeArray);
                pojo.setFromLocation(fromLocationStrArr);*/

                onewayHash.put(0,onewayArray);
                pojo.setOnewayHP(onewayHash);
                flightSegmentPojoArr.add(segmentPojo);


            }

            baggageInfo=flightDetails.getJSONObject("BaggageInformation");
            pojo.setFreeAllowance(baggageInfo.getString("FreeAllowance"));
            pojo.setQuantityCode(baggageInfo.getString("QuantityCode"));
            recomendation=json.getJSONObject("Recommendation");

                reference=recomendation.getJSONObject("Reference");
                    pojo.setOutboundStr(reference.getString("Outbound"));

                pojo.setCurrecy(recomendation.getString("Currency"));
                pojo.setTotalFare(recomendation.getString("TotalFare"));
                pojo.setTotalFareTax(recomendation.getString("TotalFareTax"));
                pojo.setNumberType(recomendation.getString("NumberType"));
                pojo.setAdult(recomendation.getString("Adult"));
                pojo.setAdultToatalFare(recomendation.getString("AdultTotalFare"));
                pojo.setAdultTotalTax(recomendation.getString("AdultTotalTax"));
                pojo.setChild(recomendation.getString("Child"));
                pojo.setChildTotalFare(recomendation.getString("ChildTotalFare"));
                pojo.setChildTotalTax(recomendation.getString("ChildTotalTax"));
                pojo.setFareRule(recomendation.getString("FareRule"));
                pojo.setCabinClass(recomendation.getString("CabinClass"));
                pojo.setUserName(recomendation.getString("UserName"));



            pojo.setSegmentArray(flightSegmentPojoArr);
            flightsList.add(pojo);
        }

        Log.i("ArrayList",flightsList+"");

        relatedFlightsRv=(RecyclerView)findViewById(R.id.oneWayFlightsRv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        relatedFlightsRv.setLayoutManager(layoutManager);
        relatedFlightsRv.setItemAnimator(new DefaultItemAnimator());
        adapter=new OneWayFlightsCustomAdapter(OneWayFlights.this,flightsList);
        relatedFlightsRv.setAdapter(adapter);
        adapter.setOnSecurityQnClickListener(this);

    }

    @Override
        public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException
        {
            rvPosition=position;
           // final Object objSent = new Object();
            final Bundle bundle = new Bundle();
            bundle.putBinder("oneWayFlightRv", flightsList.get(position));
            startActivity(new Intent(this, ReviewActivity.class).putExtras(bundle));
            Log.d("Sent", "original object=" + pojo);

           /* Intent i=new Intent(OneWayFlights.this, ReviewActivity.class);
            i.putExtra("oneWayFlightRv", (Serializable) flightsList.get(position));
            startActivity(i);*/
        }
}
