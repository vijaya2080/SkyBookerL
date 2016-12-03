package in.example.skybooker.slider.flightstatus;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;

import in.example.skybooker.R;

/**
 * Created by siris on 10/20/2016.
 */
public class FlightStatusCustomAdapter extends RecyclerView.Adapter<FlightStatusCustomAdapter.MyViewHolder> {

    ArrayList<String> welcomeArray=new ArrayList<>();
    Integer ImageName[];
    Context c;
    OnSecurityQnClickListener listner;
    private LayoutInflater inflater;


    public FlightStatusCustomAdapter(ArrayList<String> welcomeArray, Integer ImageName[],  Context c) {
        this.welcomeArray = welcomeArray;
        this.c = c;
        this.ImageName=ImageName;
        inflater = LayoutInflater.from(c);

    }

    @Override
    public void onBindViewHolder(FlightStatusCustomAdapter.MyViewHolder holder, int position) {
        holder.tvWelcomeText.setText(welcomeArray.get(position));
        holder.wcIcon.setImageResource(ImageName[position]);
    }

    @Override
    public int getItemCount() {
        return welcomeArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvWelcomeText;
        ImageView wcIcon;
        @TargetApi(Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tvWelcomeText = (TextView) view.findViewById(R.id.tv_welcomeTitle);
            wcIcon=(ImageView)view.findViewById(R.id.welcomeImg);

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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.welcomecustom, parent, false);
        return new MyViewHolder(itemView);


    }
    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view,int position)throws ClassNotFoundException,IOException,DecoderException;
    }

    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener){
        this.listner=listener;

    }
}
