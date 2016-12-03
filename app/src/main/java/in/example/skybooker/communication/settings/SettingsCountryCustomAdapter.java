package in.example.skybooker.communication.settings;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;

import in.example.skybooker.R;

/**
 * Created by vijay on 11/22/2016.
 */
public class SettingsCountryCustomAdapter extends  RecyclerView.Adapter<SettingsCountryCustomAdapter.MyViewHolder> {

    AdapterView.OnItemClickListener mItemClickListener;
    OnSecurityQnClickListener listner;
    private RadioButton lastCheckedRB = null;
    ArrayList<Event> DBtitle = new ArrayList<Event>();
    ArrayList<String> arrC,arrC1;
    public int selectedItem, us,cnd,uk,aus,mx;
    SharedPreferences.Editor editor;
    SharedPreferences sp;
    public static RadioButton checked_rb;
    String key = "";
    Activity a;


    public SettingsCountryCustomAdapter(Activity a, ArrayList<String> arrC,  String key) {
        this.a = a;
        this.arrC = arrC;
        this.key = key;
        //this.arrC1 = arrC1;

        sp = a.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sp.edit();
        selectedItem = sp.getInt("currency", 0);

       /* us = sp.getInt("type1", 1);
        cnd = sp.getInt("type11", 2);
        uk = sp.getInt("type2", 3);
        aus = sp.getInt("type3", 4);
        mx = sp.getInt("type4", 5);*/
    }

    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view,int position)throws ClassNotFoundException,IOException,DecoderException;
    }

    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener) {
        this.listner = listener;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      /*  private SparseBooleanArray selectedItems;


        public RadioGroup priceRadioGroup;*/


        // RadioButton rbutton;
        public RelativeLayout viewrlayout;
        public ImageView img;
        public RadioButton radioButton;
        public TextView country;
        public RadioGroup radioGroup;

        @TargetApi(Build.VERSION_CODES.M)

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);


            viewrlayout = (RelativeLayout) view.findViewById(R.id.viewrlayout);
            country = (TextView) view.findViewById(R.id.tv_name);
            radioButton=(RadioButton)view.findViewById(R.id.r_button);
            img = (ImageView) view.findViewById(R.id.image);
            radioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);

           /* priceRadioGroup = (RadioGroup) view.findViewById(R.id.rGroupid);
            r1 = (RelativeLayout) view.findViewById(R.id.viewrlayout);
            checked_rb = (RadioButton) view.findViewById(R.id.r_button);*/
        }

        @Override
        public void onClick(View v) {
            try {
                listner.onSecurityQnClick(v, getLayoutPosition());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (final Exception ex) {
                Log.i("---", "Exception in thread");
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.country.setText(arrC.get(position));
        holder.viewrlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if(holder.radioButton.isChecked()){
                    holder.radioButton.setChecked(false);
                }else{
                    holder.radioButton.setChecked(true);
                }*/
                holder.radioGroup.clearCheck();
                holder.radioButton.setChecked(true);
                editor.putInt("currency",position).commit();

                Intent in = new Intent();
                a.setResult(20, in);
                //  a.setResult(40,in);
                a.finish();
            }
        });

        if(selectedItem==position){
            holder.country.setTextColor(Color.parseColor("#125688"));
            holder.radioButton.setChecked(true);
        }else{
            holder.country.setTextColor(Color.BLACK);
            holder.radioButton.setChecked(false);
        }

       /* arrC = a.getResources().getStringArray(R.array.settings_country);

        Log.i("STRINGARRAY$$$$$#", arrC[position] + "");
        Log.i("COUNTRYTEST%%%%", holder.country.getText() + "");
        holder.country.setText(arrC[position]);

        Log.i("TEXTVIEW&&&&&", holder.country.getText() + "");*/

/*

        arrC = a.getResources().getStringArray(R.array.settings_country);
      //  for(int i = 0; i < arrC.length; i++) {
           // if (position == 0) {
                SettingsRvAdapter.tv_settigsCountryTitle.setText(arrC[position]);
                Log.i("TEXTVIEW#####", SettingsRvAdapter.tv_settigsCountryTitle.getText() + "");
          //  }
     //   }

*/

/*
        if (position == selectedItem) {
            holder.country.setTextColor(Color.parseColor("#125688"));
            // holder.country.setBackgroundResource(R.color.colorPrimary);
        } else {
            holder.country.setTextColor(Color.BLACK); //actually you should set to the normal text color
            checked_rb.setBackgroundResource(0);
        }*/


/*
        holder.priceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checked_rb = (RadioButton) group.findViewById(checkedId);
                if (lastCheckedRB != null) {
                    checked_rb.setChecked(true);

                    lastCheckedRB.setChecked(false);
                }
                //store the clicked radiobutton
                lastCheckedRB = checked_rb;

            }
        });*/



/*
        holder. r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastCheckedRB != null) {
                    lastCheckedRB.setChecked(false);
                    //SettingsCountryCustomAdapter.checked_rb.setChecked(true);


                    //store the clicked radiobutton
                    // lastCheckedRB.setChecked(true);
                    //SettingsCountryCustomAdapter.checked_rb.setChecked(true);
                }
                if (position == sp.getInt("type", 0)) {
                    arrC = a.getResources().getStringArray(R.array.settings_country);
                    Log.i("ARRAYCOU@@#####", arrC[position] + "");
                    // for(int i = 0; i < arrC.length; i++) {

                    SettingsRvAdapter.tv_settigsCountryTitle.setText(arrC[position]);
                    Log.i("ARRAYCOU@@#####", SettingsRvAdapter.tv_settigsCountryTitle.getText() + "");
                    editor.putInt("type", 0);
                    editor.commit();
                    a.finish();
                }

                }
            });
        }

            */
    }

    @Override
    public int getItemCount() {
        return arrC.size();
    }
}

