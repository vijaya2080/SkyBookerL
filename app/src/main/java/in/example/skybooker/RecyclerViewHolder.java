package in.example.skybooker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kundan on 10/26/2015.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView tv1,tv2;
    ImageView imageView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        tv1= (TextView) itemView.findViewById(R.id.tv_country);


    }
}
