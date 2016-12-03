package in.example.skybooker.myaccount;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;

import in.example.skybooker.R;


/**
 * Created by nbhag on 10/22/2016.
 */
 public class RecyclerTitlesAdapter extends RecyclerView.Adapter<RecyclerTitlesAdapter.MyViewHolder> {
    ArrayList<String> TitleGroup=new ArrayList();
    AdapterView.OnItemClickListener mItemClickListener;
    OnSecurityQnClickListener listener;


    Context context;

    public RecyclerTitlesAdapter(Context context, ArrayList<String> questionArray) {
        this.TitleGroup = questionArray;
        this.context = context;

    }

    public interface OnSecurityQnClickListener{
        public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException,DecoderException;
    }

    public void setOnSecurityQnClickListener(OnSecurityQnClickListener listener){
        this.listener =  listener;
    }





    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvname;

        @TargetApi(Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);

            tvname = (TextView) view.findViewById(R.id.rvName);
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclertitlegroup, parent, false);
        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvname.setText(TitleGroup.get(position));
    }


    @Override
    public int getItemCount() {
        return TitleGroup.size();
    }
}
