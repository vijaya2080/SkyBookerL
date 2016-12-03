package in.example.skybooker.communication.settings;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;

import in.example.skybooker.R;

/**
 * Created by vijay on 10/22/2016.
 */
public class SettingsRvAdapter  extends  RecyclerView.Adapter<SettingsRvAdapter.MyViewHolder> {
    Context c;
    OnSecurityQnClickListener listner;
    // private LayoutInflater inflater;
    ArrayList<String> settingsTitle,settingsValue;

    public SettingsRvAdapter(Context c, ArrayList<String> settingsCountryTitle, ArrayList<String> settingsCountry) {

        this.settingsTitle = settingsCountryTitle;
        this.settingsValue = settingsCountry;
        this.c = c;
        //inflater = LayoutInflater.from(c);
    }

    @Override
    public void onBindViewHolder(SettingsRvAdapter.MyViewHolder holder, int position) {
        holder.tv_settigsCountryTitle.setText(settingsTitle.get(position));
        holder.tv_settingsCountry.setText(settingsValue.get(position));
    }

    @Override
    public int getItemCount() {
        return settingsTitle.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_settigsCountryTitle,tv_settingsCountry;

        @TargetApi(Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tv_settingsCountry = (TextView) view.findViewById(R.id.tv_value);
            tv_settigsCountryTitle = (TextView) view.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View v) {
            try {
                listner.onSecurityQnClick(v, getLayoutPosition());
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customrvsettings, parent, false);
        return new MyViewHolder(itemView);
    }


    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view,int position)throws ClassNotFoundException,IOException,DecoderException;
    }

    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener){
        this.listner=listener;
    }
}
