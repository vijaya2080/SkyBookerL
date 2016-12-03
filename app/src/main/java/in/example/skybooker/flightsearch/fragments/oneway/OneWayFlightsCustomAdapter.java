package in.example.skybooker.flightsearch.fragments.oneway;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import in.example.skybooker.R;
import in.example.skybooker.pojo.FlightSegmentPojo;
import in.example.skybooker.pojo.OneWayPojo;

/**
 * Created by nbhag on 11/18/2016.
 */
public class OneWayFlightsCustomAdapter extends RecyclerView.Adapter<OneWayFlightsCustomAdapter.MyViewHolder> {

    //OneWayPojo pojo=new OneWayPojo();
    ArrayList<OneWayPojo> flightsList;
    ArrayList<FlightSegmentPojo> segmentPojoArr=new ArrayList<>();
    HashMap<Integer,ArrayList<String>> fromHash,toHash;
    ArrayList<String > toArray,flightsArray;
    Context c;
    OnSecurityQnClickListener listner;
    ViewGroup viewGroup;
    SimpleDateFormat format,dayFormater;
    String convertDate,dateStart,dateStop,arrivalDate,arrivalTime,departureDate,departureTime;


    public OneWayFlightsCustomAdapter(Context c, ArrayList<OneWayPojo> flightsList) {
        this.flightsList = flightsList;
        this.c=c;


    }
    @Override
    public void onViewRecycled(OneWayFlightsCustomAdapter.MyViewHolder holder) {

        super.onViewRecycled(holder);
        holder.toAndFromCities.removeAllViews();
        viewGroup.removeAllViews();

    }
    @Override
    public void onBindViewHolder(OneWayFlightsCustomAdapter.MyViewHolder holder, int position) {

        final OneWayPojo  pojo=flightsList.get(position);
        segmentPojoArr=pojo.getSegmentArray();

        Log.i("Size",segmentPojoArr.size()+" ");

        Log.i("pojoArray",flightsList+" ");

        fromHash=new HashMap<>();
        toHash=new HashMap<>();

        format = new SimpleDateFormat("ddMMyy HHmm");

        pojo.getOnewayHP();
        Log.i("HashMap",pojo.getOnewayHP().size()+" ");

        for(int i=0;i<pojo.getOnewayHP().size();i++){

            toArray=new ArrayList<>();

               toArray = pojo.getOnewayHP().get(i);

                for (int j=0;j<toArray.size();j++){
                    Log.i("ToArray",toArray.get(j)+" ");
                    View segmentView2 = LayoutInflater.from(c).inflate(R.layout.toandfromcity, null, false);

                    holder.tv_FromCity=(TextView)segmentView2.findViewById(R.id.tv_customFromCity);
                    holder.travelPoint=(View)segmentView2.findViewById(R.id.travelPoint);
                    holder.tv_FromCity.setText(toArray.get(j));
                    holder.toAndFromCities.addView(segmentView2);

                    if(j== toArray.size()-1){
                        holder.travelPoint.setVisibility(View.GONE);
                    }
                }

        }

        for (int k=0; k<segmentPojoArr.size();k++){

            holder.tv_departFromTime.setText(segmentPojoArr.get(k).getDepartureTime());
            Log.i("Departure array",(segmentPojoArr.get(k).getDepartureTime())+"");

            holder.tv_departToTime.setText(segmentPojoArr.get(k).getArrivalTime());
            Log.i("Arrival array",(segmentPojoArr.get(k).getArrivalTime())+"");
        }

       //for( int m = 0; m < segmentPojoArr.size() ; m++ ){

            departureDate = segmentPojoArr.get(0).getDepartureDate();
            departureTime = segmentPojoArr.get(0).getDepartureTime();

            arrivalDate = segmentPojoArr.get(segmentPojoArr.size()-1).getArrivalDate();
            arrivalTime = segmentPojoArr.get(segmentPojoArr.size()-1).getArrivalTime();

            dateStart = departureDate + " "+departureTime;
            dateStop = arrivalDate +" " +arrivalTime;
            convertDate=departureDate;

            Log.i("datestart",dateStart+"");
            Log.i("dateStop",dateStop+"");

            Date d1,d2;

            try {
                d1 = format.parse(dateStart);
                d2 = format.parse(dateStop);

                //in milliseconds
                long diff = d2.getTime() - d1.getTime();

                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);

                Log.i( " days ",diffDays +" ");
                Log.i( " hours ", diffHours +" ");
                Log.i( " minutes ",diffMinutes +" ");

                holder.tv_departDays.setText(diffDays+"");
            } catch (Exception e) {
                e.printStackTrace();
            }

        //}


       /* flightsArray=new ArrayList<>();
        flightsArray = pojo.getDepartureTimeArray();
        if(flightsArray.size()>=1){
            Log.i("entered into if","set");

            Log.i("depature array",pojo.getDepartureTimeArray()+"");
            for(int k=0;k<=flightsArray.size(); k++){
                Log.i("entered into for","set");
                holder.tv_departFromTime.setText( pojo.getDepartureTimeArray().get(k));
                holder.tv_departToTime.setText(pojo.getArrivalTimeArray().get(k));
            }
        }*/

        holder.tv_departDuration.setText(pojo.getDurationStr());
        holder.tv_cost.setText(pojo.getTotalFare());
       // holder.tv_ToCity.setText(pojo.getToLocation());
    }

    @Override
    public int getItemCount() {
        return flightsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_departFromTime,tv_departToTime,tv_flightDesc,tv_cost,tv_ToCity,
                tv_FromCity,tv_departDuration,tv_departDays;
        View travelPoint;
        ImageView imgFlightIcon;
        RelativeLayout durationlay;
        LinearLayout toAndFromCities;

        @TargetApi(Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            tv_departFromTime = (TextView) view.findViewById(R.id.tv_OneWaydepartFromTime);


            tv_departToTime=(TextView)view.findViewById(R.id.tv_time);
            tv_departDuration=(TextView)view.findViewById(R.id.tv_durationTime) ;
            tv_departDays=(TextView)view.findViewById(R.id.tv_durationdays);
            tv_flightDesc=(TextView)view.findViewById(R.id.oneWayFlihtsSaving);
            tv_cost=(TextView)view.findViewById(R.id.tv_oneWayCost);

            toAndFromCities=(LinearLayout)view.findViewById(R.id.oneWayToAndFromRL);

            imgFlightIcon=(ImageView)view.findViewById(R.id.oneWayAirLineImg);


        }

        @Override
        public void onClick(View v) {
            try{
                listner.onSecurityQnClick(v,getLayoutPosition());
            } catch (DecoderException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // viewGroup=parent;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_onewaycustomrv, parent, false);

        return new MyViewHolder(itemView);
    }

    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view,int position)throws ClassNotFoundException,IOException,DecoderException;
    }

    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener){
        this.listner=listener;

    }
}
