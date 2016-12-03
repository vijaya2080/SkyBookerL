package in.example.skybooker.filters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.example.skybooker.R;

/**
 * Created by siris on 10/27/2016.
 */
public class AirportFilters extends Fragment {

    RelativeLayout fromRL,toRL;
    TextView tv_From,tv_To;
    CheckBox toCheckBox,fromCheckBox;

    public AirportFilters() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.airportsfilter_layout, null);


        fromRL=(RelativeLayout)root.findViewById(R.id.FromAirportLayout);
        tv_From=(TextView)fromRL.findViewById(R.id.tv_AirportText);
        fromCheckBox=(CheckBox)fromRL.findViewById(R.id.checkBox_customStops);

        fromCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fromCheckBox.isChecked())
                    Toast.makeText(getActivity(), "checked", Toast.LENGTH_LONG).show();
            }
        });

        toRL=(RelativeLayout)root.findViewById(R.id.ToAirportLayout);
        tv_To=(TextView)toRL.findViewById(R.id.tv_AirportText);
        tv_To.setText("RJD");
        toCheckBox=(CheckBox)toRL.findViewById(R.id.checkBox_customStops);

        toCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toCheckBox.isChecked())
                    Toast.makeText(getActivity(), "checked", Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }
}
