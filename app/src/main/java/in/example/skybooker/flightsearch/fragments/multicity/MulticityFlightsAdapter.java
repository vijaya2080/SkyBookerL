package in.example.skybooker.flightsearch.fragments.multicity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;

import in.example.skybooker.R;

/**
 * Created by nbhag on 11/19/2016.
 */
public class MulticityFlightsAdapter extends RecyclerView.Adapter<MulticityFlightsAdapter.MyViewHolder> {

    ArrayList<String> toCityArray,fromCityArray;
    Context c;
    OnSecurityQnClickListener listner;
    private LayoutInflater inflater;
    ImageView wcIcon;
    String key;

    public MulticityFlightsAdapter(Context c, ArrayList<String> fromCityArray, ArrayList<String>toCityArray) {
        this.fromCityArray = fromCityArray;
        this.toCityArray=toCityArray;
        this.c = c;
        inflater = LayoutInflater.from(c);

    }

    @Override
    public void onBindViewHolder(MulticityFlightsAdapter.MyViewHolder holder, int position) {
        holder.tv_departureFromCity.setText(fromCityArray.get(position));
       // holder.tv_departureToCity.setText(toCityArray.get(position));
       // holder.tv_returnToCity.setText(toCityArray.get(position));
        holder.tv_returnFromCity.setText(fromCityArray.get(position));
    }

    @Override
    public int getItemCount() {
        return toCityArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_departFromTime,tv_departToTime,tv_returnToTime,tv_returnFromTime,tv_flightDesc,tv_cost,tv_departureToCity,
                tv_departureFromCity,tv_returnToCity,tv_returnFromCity;
        ImageView imgFlightIcon;
        RelativeLayout toAndFromCities,secondFlight;

        @TargetApi(Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tv_departFromTime = (TextView) view.findViewById(R.id.tv_multiDepartFromTime);
            tv_departToTime=(TextView)view.findViewById(R.id.tv_multiDepartToime);
            tv_returnToTime=(TextView)view.findViewById(R.id.tv_multiReturnToTime) ;
            tv_returnFromTime=(TextView)view.findViewById(R.id.tv_multiReturnFromTime);
            tv_flightDesc=(TextView)view.findViewById(R.id.multiFlihtsSaving);
            tv_cost=(TextView)view.findViewById(R.id.tv_cost);

            toAndFromCities=(RelativeLayout)view.findViewById(R.id.multiFirstRL);
            tv_departureFromCity=(TextView)toAndFromCities.findViewById(R.id.tv_customFromCity);
            //tv_departureToCity=(TextView)toAndFromCities.findViewById(R.id.tv_customToCity);

            secondFlight=(RelativeLayout)view.findViewById(R.id.multiSecondFlight);
           //tv_returnToCity=(TextView)secondFlight.findViewById(R.id.tv_customToCity);
            tv_returnFromCity=(TextView)secondFlight.findViewById(R.id.tv_customFromCity);




            imgFlightIcon=(ImageView)view.findViewById(R.id.multiAirLineImg);

            //wcIcon=(ImageView)view.findViewById(R.id.dropdownTravaller);

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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.multicitycustomrv, parent, false);
        return new MyViewHolder(itemView);


    }

    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view,int position)throws ClassNotFoundException,IOException,DecoderException;
    }

    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener){
        this.listner=listener;

    }
}
