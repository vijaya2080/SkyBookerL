package in.example.skybooker.filters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;

import in.example.skybooker.R;

/**
 * Created by siris on 10/27/2016.
 */
public class FilterStopsAdapter extends RecyclerView.Adapter<FilterStopsAdapter.MyViewHolder>  {

    ArrayList<String> StopsArray;
    ArrayList<String> DollersArray;
    AdapterView.OnItemClickListener mItemClickListener;
    OnSecurityQnClickListener listener;
    Context context;

    SharedPreferences.Editor editor;
    public FilterStopsAdapter(Context context, ArrayList<String> StopsArray,ArrayList<String> DollersArray) {
        this.StopsArray = StopsArray;
        this.DollersArray=DollersArray;
        this.context = context;

    }


    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener){
        this.listener =  listener;
    }

    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException,DecoderException;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView StopsType,DollersType;

        @TargetApi(Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            StopsType = (TextView) view.findViewById(R.id.tv_customStopsText);
            DollersType=(TextView) view.findViewById(R.id.tv_$6799);
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_stops, parent, false);
        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.StopsType.setText(StopsArray.get(position));
        holder.DollersType.setText(DollersArray.get(position));

    }


    @Override
    public int getItemCount() {
        return StopsArray.size();
    }
}