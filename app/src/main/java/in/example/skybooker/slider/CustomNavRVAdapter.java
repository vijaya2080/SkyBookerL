package in.example.skybooker.slider;

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
 * Created by siris on 10/19/2016.
 */
public class CustomNavRVAdapter extends RecyclerView.Adapter<CustomNavRVAdapter.MyViewHolder> {

    ArrayList<String> navDrawerRvTitles,navDrawerRvSubTitles;
    Context c;
    OnSecurityQnClickListener listner;
    private LayoutInflater inflater;
    ImageView wcIcon;
    ArrayList<Integer> imagesArray;
    Integer[] ImageName;

    public CustomNavRVAdapter(Context c, ArrayList<String> navDrawerRvTitles, ArrayList<String> navDrawerRvSubTitles, Integer[] ImageName) {
        this.navDrawerRvTitles = navDrawerRvTitles;
        this.navDrawerRvSubTitles=navDrawerRvSubTitles;
        this.c = c;
        this.ImageName=ImageName;
        inflater = LayoutInflater.from(c);

    }

    @Override
    public void onBindViewHolder(CustomNavRVAdapter.MyViewHolder holder, int position) {
        holder.tv_navRvTitle.setText(navDrawerRvTitles.get(position));
        holder.tv_navRvSubTitle.setText(navDrawerRvSubTitles.get(position));
        holder.customrv.setImageResource(ImageName[position]);

    }

    @Override
    public int getItemCount() {
        return navDrawerRvTitles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_navRvTitle,tv_navRvSubTitle;
        ImageView customrv;
        @TargetApi(Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tv_navRvTitle = (TextView) view.findViewById(R.id.tv_customRvTitle);
            tv_navRvSubTitle=(TextView)view.findViewById(R.id.tv_customRvSubTitle);
            customrv=(ImageView)view.findViewById(R.id.img_customRv);
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
