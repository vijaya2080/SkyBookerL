package in.example.skybooker;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import in.example.skybooker.flightsearch.fragments.multicity.MultiCityFragment;
import in.example.skybooker.flightsearch.fragments.oneway.OneWayFragment;
import in.example.skybooker.flightsearch.fragments.roundtrip.RoundTripFragment;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    public ArrayList<String> travellerTypeArray=new ArrayList<>();
    public HashMap<String,Integer>travellerCountArray=new HashMap<>();

    OnSecurityQnClickListener listener;

    Context context;
    String key="";

    // private android.support.v4.app.Fragment f = null;
    public RecyclerAdapter(Context context, ArrayList<String> travellerTypeArray, HashMap<String,Integer> travellerCount,String key ) {
        Log.i("travellerCount",travellerCount+"");
        this.context =  context;
        this.travellerTypeArray = travellerTypeArray;
        this.travellerCountArray=travellerCount;
        this.key = key;
    }


    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener){
        this.listener = listener;
    }

    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvTravellerType,tvTravellerCount;
        public ImageView increment,decrement;
        public  int count=0;

        @TargetApi(Build.VERSION_CODES.M)

        public MyViewHolder(View view) {
            super(view);

            view.setOnClickListener(this);

            tvTravellerType = (TextView) view.findViewById(R.id.tvTravallerType);
            tvTravellerCount = (TextView) view.findViewById(R.id.tvTravllerNo);

            decrement=(ImageView)view.findViewById(R.id.imgMinus);
            increment=(ImageView)view.findViewById(R.id.imgPlus);


        }

        @Override
        public void onClick(View v) {

            try {
                listener.onSecurityQnClick(v,getLayoutPosition());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            key = bundle.getString("fragment");
            Log.i("key",key+"");
        }*/
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_down_traveller, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvTravellerType.setText(travellerTypeArray.get(position));
        holder.tvTravellerCount.setText(travellerCountArray.get(position+"")+"");

        holder.tvTravellerType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.tvTravellerCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.increment.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v)
            {


                /**//*for(int i=0;i<travellerCountArray.size();i++)
                {
                    Log.i( OneWayFragment.passCount+"",travellerCountArray.get(i+"")+"");
                    OneWayFragment.passCount = OneWayFragment.passCount+ travellerCountArray.get(i+"");
                }*/

                if (key.equals("oneway")) {

                    if(OneWayFragment.passCount<9){
                        travellerCountArray.put(position+"",travellerCountArray.get(position+"")+1);
                        OneWayFragment.passCount = OneWayFragment.passCount+1;
                        holder.tvTravellerCount.setText(travellerCountArray.get(position+"")+"");
                    } else {
                        Toast.makeText(context,"Maximum 9 passengers",Toast.LENGTH_SHORT).show();
                    }


                    // OneWayFragment.tvFromAirport.setText(fromAirport);
                } else if (key.equals("roundtrip")) {

                    if(RoundTripFragment.passCount<9){
                        travellerCountArray.put(position+"",travellerCountArray.get(position+"")+1);
                        RoundTripFragment.passCount = RoundTripFragment.passCount+1;
                        holder.tvTravellerCount.setText(travellerCountArray.get(position+"")+"");
                    } else {
                        Toast.makeText(context,"Maximum 9 passengers",Toast.LENGTH_SHORT).show();
                    }
                }

                else if (key.equals("multicity")) {

                    if(MultiCityFragment.passCount<9){
                        travellerCountArray.put(position+"",travellerCountArray.get(position+"")+1);
                        MultiCityFragment.passCount = MultiCityFragment.passCount+1;
                        holder.tvTravellerCount.setText(travellerCountArray.get(position+"")+"");
                    } else {
                        Toast.makeText(context,"Maximum 9 passengers",Toast.LENGTH_SHORT).show();
                    }
                }




                // RoundTripFragment.tvFromAirport.setText(fromAirport);
                //MultiCityFragment.






                /*if(OneWayFragment.passCount<9){
                    travellerCountArray.put(position+"",travellerCountArray.get(position+"")+1);
                    OneWayFragment.passCount = OneWayFragment.passCount+1;
                    holder.tvTravellerCount.setText(travellerCountArray.get(position+"")+"");
                } else {
                    Toast.makeText(context,"Maximum 9 passengers",Toast.LENGTH_SHORT).show();
                }*/
               /* if(position==0){

                    holder.count++;
                    holder.tvTravellerCount.setText(Integer.toString(holder.count));

                }
                else {
                    holder.count++;
                    holder.tvTravellerCount.setText(Integer.toString(holder.count));
                    Toast.makeText(v.getContext(), position + "incremented by 1", Toast.LENGTH_LONG).show();
                }*/
            }
        });

        holder.decrement.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
               /* if(OneWayFragment.passCount>1)
                {
                    if (travellerCountArray.get(position+"")>0) {
                        travellerCountArray.put(position+"",travellerCountArray.get(position+"")-1);
                        OneWayFragment.passCount = OneWayFragment.passCount-1;
                        holder.tvTravellerCount.setText(travellerCountArray.get(position+"")+"");
                    }else{
                        //Toast.makeText(context,"Minimum 1 passenger",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(context,"Minimum 1 passenger",Toast.LENGTH_SHORT).show();
                }*/

                if (key.equals("oneway")) {

                    if(OneWayFragment.passCount>1)
                    {
                        if (travellerCountArray.get(position+"")>0) {
                            travellerCountArray.put(position+"",travellerCountArray.get(position+"")-1);
                            OneWayFragment.passCount = OneWayFragment.passCount-1;
                            holder.tvTravellerCount.setText(travellerCountArray.get(position+"")+"");
                        }else{
                            //Toast.makeText(context,"Minimum 1 passenger",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(context,"Minimum 1 passenger",Toast.LENGTH_SHORT).show();
                    }


                    // OneWayFragment.tvFromAirport.setText(fromAirport);
                } else if (key.equals("roundtrip")) {

                    if(RoundTripFragment.passCount>1)
                    {
                        if (travellerCountArray.get(position+"")>0) {
                            travellerCountArray.put(position+"",travellerCountArray.get(position+"")-1);
                            RoundTripFragment.passCount = RoundTripFragment.passCount-1;
                            holder.tvTravellerCount.setText(travellerCountArray.get(position+"")+"");
                        }else{
                            //Toast.makeText(context,"Minimum 1 passenger",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(context,"Minimum 1 passenger",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (key.equals("multicity")) {

                    if(MultiCityFragment.passCount>1)
                    {
                        if (travellerCountArray.get(position+"")>0) {
                            travellerCountArray.put(position+"",travellerCountArray.get(position+"")-1);
                            MultiCityFragment.passCount = MultiCityFragment.passCount-1;
                            holder.tvTravellerCount.setText(travellerCountArray.get(position+"")+"");
                        }else{
                            //Toast.makeText(context,"Minimum 1 passenger",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(context,"Minimum 1 passenger",Toast.LENGTH_SHORT).show();
                    }
                }

/*

                if(position==0 ){
                    if(holder.count==1 || holder.count==0) {
                        holder.decrement.setEnabled(false);
                    }
                    else {
                        holder.count--;
                        holder.decrement.setEnabled(true);
                        holder.tvTravellerCount.setText(Integer.toString(holder.count));
                    }
                }
                else {
                    holder.count--;
                    holder.tvTravellerCount.setText(Integer.toString(holder.count));
                    Toast.makeText(v.getContext(), position + "decremented by 1", Toast.LENGTH_LONG).show();
                }*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return travellerCountArray.size();

    }
}

