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

/**
 * Created by siris on 10/27/2016.
 */
public class StopsFilters extends Fragment {
    CheckBox checkBox;
    RecyclerView rvStops;
    FilterStopsAdapter mAdapter;
    ArrayList<String> Stops_Array;
    ArrayList<String> Dollers_Array;
    public StopsFilters() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.stopsfilters_layout, null);



        checkBox=(CheckBox)root.findViewById(R.id.checkBoxStops);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked())
                    Toast.makeText(getActivity(), "checked", Toast.LENGTH_LONG).show();
            }
        });

        Stops_Array=new ArrayList<>();
        Dollers_Array=new ArrayList<>();

        Stops_Array.add("2-Stops");
        Stops_Array.add("3-Stops");
        Dollers_Array.add("$6799");
        Dollers_Array.add("$7676");

        rvStops=(RecyclerView)root.findViewById(R.id.rvStops);

        mAdapter = new FilterStopsAdapter(getActivity(),Stops_Array,Dollers_Array);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvStops.setLayoutManager(layoutManager);
        rvStops.setItemAnimator(new DefaultItemAnimator());
        rvStops.setAdapter(mAdapter);


        return root;
    }
}
