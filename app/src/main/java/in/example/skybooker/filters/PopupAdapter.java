package in.example.skybooker.filters;

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

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.fragments.roundtrip.RoundTripFlights;

/**
 * Created by siris on 10/28/2016.
 */
public class PopupAdapter  extends RecyclerView.Adapter<PopupAdapter.MyViewHolder>  {
    ArrayList<String> PopupArray;
    AdapterView.OnItemClickListener mItemClickListener;
    OnSecurityQnClickListener listener;
    Context context;
    public int selectedItem;
    SharedPreferences.Editor editor;
    SharedPreferences sp;
    String key="";
    public PopupAdapter(Context context, ArrayList<String> PopupArray,String key) {
        this.PopupArray = PopupArray;
        this.context = context;
        this.key=key;
        sp = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sp.edit();
        selectedItem = sp.getInt("type",0);
    }


    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener){
        this.listener =  listener;
    }

    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException,DecoderException;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_PopupText;
        public RelativeLayout rlPopupSelected;
        @TargetApi(Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tv_PopupText = (TextView) view.findViewById(R.id.tv_PopupText);
            rlPopupSelected = (RelativeLayout)view.findViewById(R.id.rlPopup);
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_popup, parent, false);
        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if(position == selectedItem) {
            holder.tv_PopupText.setTextColor(Color.parseColor("#125688"));
            holder.rlPopupSelected.setBackgroundResource(R.color.colorPrimary);
        } else {
            holder.tv_PopupText.setTextColor(Color.BLACK); //actually you should set to the normal text color
            holder.rlPopupSelected.setBackgroundResource(0);
        }
        holder.tv_PopupText.setText(PopupArray.get(position));
        holder.tv_PopupText.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {


                if(key.equals("popup"))
                {
                    editor.putInt("type",position);
                    editor.commit();
                    RoundTripFlights.popupWindow.dismiss();
                    //OneWayFragment.tvCoachType.setText(classTypeArray.get(position));
                    // holder.tvClassType.setTextColor(context.getColor(R.color.colorPrimary));
                    //holder.rlImageAndTextCoach.setSelected(true);
                }





               /*if(position==0){
                   Toast.makeText(context,"firstposition",Toast.LENGTH_SHORT).show();
                    //holder.tv_PopupText.setTextColor(Color.parseColor("#00aaff"));
                }else if(position==1){
                   Toast.makeText(context,"secondposition",Toast.LENGTH_SHORT).show();
                    //holder.tv_PopupText.setTextColor(Color.parseColor("#00aaff"));
                }else if(position==2){
                   Toast.makeText(context,"thirdposition",Toast.LENGTH_SHORT).show();
                    //holder.tv_PopupText.setTextColor(Color.parseColor("#00aaff"));
                }else if(position==3){
                   Toast.makeText(context,"fourthposition",Toast.LENGTH_SHORT).show();
                    //holder.tv_PopupText.setTextColor(Color.parseColor("#00aaff"));
                }
                else if(position==4){
                   Toast.makeText(context,"fifth position",Toast.LENGTH_SHORT).show();
               }
                editor.putInt("type",position);
                editor.commit();





*/
              /*  *//**//*if(key.equals("oneway"))
                {
                    OneWayFragment.ad.dismiss();
                    OneWayFragment.tvCoachType.setText(classTypeArray.get(position));
                    // holder.tvClassType.setTextColor(context.getColor(R.color.colorPrimary));
                    //holder.rlImageAndTextCoach.setSelected(true);
                } else if (key.equals("roundtrip")) {
                    RoundTripFragment.ad.dismiss();
                    RoundTripFragment.tvCoachType.setText(classTypeArray.get(position));
                }*//**//**/
            }
        });
    }


    @Override
    public int getItemCount() {
        return PopupArray.size();
    }
}
