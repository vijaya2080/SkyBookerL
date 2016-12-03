package in.example.skybooker.filters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.example.skybooker.R;

/**
 * Created by siris on 10/27/2016.
 */
public class TimeFilters extends Fragment {
    TextView From,To;
    RelativeLayout rl_time;
    TextView tv_FromTo,tv_From,tv_To;
    public TimeFilters() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.timefilters_layout, null);
        From=(TextView)root.findViewById(R.id.tv_DepartText);
        To=(TextView)root.findViewById(R.id.tv_Return);
        rl_time=(RelativeLayout)root.findViewById(R.id.FiltersTimeDuration);
        tv_FromTo=(TextView)rl_time.findViewById(R.id.tv_FromTo);
        tv_From=(TextView)rl_time.findViewById(R.id.tv_From);
        tv_To=(TextView)rl_time.findViewById(R.id.tv_To);
        return root;
    }
}
