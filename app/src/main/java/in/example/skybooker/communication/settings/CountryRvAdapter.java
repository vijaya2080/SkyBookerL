package in.example.skybooker.communication.settings;

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
 * Created by vijay on 10/20/2016.
 */
public class CountryRvAdapter extends RecyclerView.Adapter<CountryRvAdapter.MyViewHolder> {

    ArrayList<String> navDrawerRvTitles,navDrawerRvSubTitles;
    Context c;
    OnSecurityQnClickListener listner;
    private LayoutInflater inflater;
    ImageView wcIcon;

    public CountryRvAdapter(Context c,ArrayList<String> navDrawerRvTitles,ArrayList<String>navDrawerRvSubTitles) {
        this.navDrawerRvTitles = navDrawerRvTitles;
        this.navDrawerRvSubTitles=navDrawerRvSubTitles;
        this.c = c;
        inflater = LayoutInflater.from(c);

    }

    @Override
    public void onBindViewHolder(CountryRvAdapter.MyViewHolder holder, int position) {
        holder.tv_navRvTitle.setText(navDrawerRvTitles.get(position));
        holder.tv_navRvSubTitle.setText(navDrawerRvSubTitles.get(position));

    }

    @Override
    public int getItemCount() {
        return navDrawerRvTitles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_navRvTitle,tv_navRvSubTitle;

        @TargetApi(Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tv_navRvTitle = (TextView) view.findViewById(R.id.tv_customRvTitle);
            tv_navRvSubTitle=(TextView)view.findViewById(R.id.tv_customRvSubTitle);

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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customsliderrecycler, parent, false);
        return new MyViewHolder(itemView);


    }

    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view,int position)throws ClassNotFoundException,IOException,DecoderException;
    }

    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener){
        this.listner=listener;

    }
}
