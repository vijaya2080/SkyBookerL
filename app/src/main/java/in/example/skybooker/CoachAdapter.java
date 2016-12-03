package in.example.skybooker;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;

import in.example.skybooker.flightsearch.fragments.multicity.MultiCityFragment;
import in.example.skybooker.flightsearch.fragments.oneway.OneWayFragment;
import in.example.skybooker.flightsearch.fragments.roundtrip.RoundTripFragment;


/**
 * Created by siris on 10/1/2016.
 */
public class CoachAdapter extends RecyclerView.Adapter<CoachAdapter.MyViewHolder> {
    ArrayList<String> classTypeArray;
    AdapterView.OnItemClickListener mItemClickListener;
    OnSecurityQnClickListener listener;
    Context context;
    public int selectedItem;
    SharedPreferences.Editor editor;
    SharedPreferences sp;
    String key="";
    public CoachAdapter(Context context, ArrayList<String> coachArray,String key) {
        this.classTypeArray = coachArray;
        this.context = context;
        this.key=key;
        sp = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sp.edit();
        if(key.equals("oneway")){
            selectedItem=sp.getInt("typeOneway",0);
        }else if(key.equals("roundtrip")){
            selectedItem=sp.getInt("typeRound",0);
        }else if(key.equals("multicity")){
            selectedItem=sp.getInt("typeMulti",0);
        }
    }


    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener){
        this.listener =  listener;
    }

    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException,DecoderException;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvClassType;
        public RelativeLayout rlImageAndTextCoach,rlSelected;
        @TargetApi(Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tvClassType = (TextView) view.findViewById(R.id.tv_CoachType);
            rlImageAndTextCoach = (RelativeLayout)view.findViewById(R.id.rlImageAndTextCoach);
            rlSelected = (RelativeLayout)view.findViewById(R.id.rlSelected);
        }

        @Override
        public void onClick(View v) {
            try {
                listener.onSecurityQnClick(v,getLayoutPosition());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DecoderException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_down_coach, parent, false);
        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if(position == selectedItem) {
            holder.tvClassType.setTextColor(Color.parseColor("#125688"));
            holder.rlSelected.setBackgroundResource(R.color.colorPrimary);
        } else {
            holder.tvClassType.setTextColor(Color.BLACK); //actually you should set to the normal text color
            holder.rlSelected.setBackgroundResource(0);
        }
        holder.tvClassType.setText(classTypeArray.get(position));
        holder.rlImageAndTextCoach.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
               /* if(position==0){
                    holder.tvClassType.setTextColor(Color.parseColor("#00aaff"));
                }else if(position==1){
                    holder.tvClassType.setTextColor(Color.parseColor("#00aaff"));
                }else if(position==2){
                    holder.tvClassType.setTextColor(Color.parseColor("#00aaff"));
                }else if(position==3){
                    holder.tvClassType.setTextColor(Color.parseColor("#00aaff"));
                }*/


                if(key.equals("oneway"))
                {
                    editor.putInt("typeOneway",position);
                    editor.commit();
                    OneWayFragment.ad.dismiss();
                    OneWayFragment.tvCoachType.setText(classTypeArray.get(position));
                    // holder.tvClassType.setTextColor(context.getColor(R.color.colorPrimary));
                    //holder.rlImageAndTextCoach.setSelected(true);
                } else if (key.equals("roundtrip")) {
                    editor.putInt("typeRound",position);
                    editor.commit();
                    RoundTripFragment.ad.dismiss();
                    RoundTripFragment.tvCoachType.setText(classTypeArray.get(position));
                }
                else if (key.equals("multicity")) {
                    editor.putInt("typeMulti",position);
                    editor.commit();
                    MultiCityFragment.ad.dismiss();
                    MultiCityFragment.tv_classType.setText(classTypeArray.get(position));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return classTypeArray.size();
    }
}
