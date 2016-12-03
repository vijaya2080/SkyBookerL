package in.example.skybooker.filters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import in.example.skybooker.R;
import in.example.skybooker.RecyclerAdapter;

/**
 * Created by siris on 10/27/2016.
 */
public class AirlineFilters extends Fragment {
    CheckBox checkBox;
    RecyclerView rvAirline;
    FilterAirLineAdapter mAdapter;
    ArrayList<String> AirLines_Array;
    public AirlineFilters() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.airlinefilters_layout, null);

        checkBox=(CheckBox)root.findViewById(R.id.checkBoxAirLine);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked())
                    Toast.makeText(getActivity(), "checked", Toast.LENGTH_LONG).show();
            }
        });

        AirLines_Array=new ArrayList<>();

        AirLines_Array.add("ANA");
        AirLines_Array.add("Air India");
        AirLines_Array.add("Alaska Airlines");
        AirLines_Array.add("Air Ways");

        rvAirline=(RecyclerView)root.findViewById(R.id.rvAirLine);

        mAdapter = new FilterAirLineAdapter(getActivity(),AirLines_Array );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvAirline.setLayoutManager(layoutManager);
        rvAirline.setItemAnimator(new DefaultItemAnimator());
        rvAirline.setAdapter(mAdapter);



        return root;
    }
}
