package in.example.skybooker.flightsearch.fragments.multicity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;

import in.example.skybooker.FlexibleDates;
import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.review.ReviewActivity;
import in.example.skybooker.filters.FiltersActivity;
import in.example.skybooker.filters.PopupAdapter;

public class MulticityFlights extends AppCompatActivity implements MulticityFlightsAdapter.OnSecurityQnClickListener,PopupAdapter.OnSecurityQnClickListener{
    RecyclerView rvTraveller,rvPopup;
    PopupAdapter adpt;
    ImageView toolIcon,sortImg,filterImg,nearbyImg,flexiImg;
    ArrayList<String> PopupArray;
    TextView tv_fromCity,tv_toCity,tv_fromDate,tv_toDate;
    RelativeLayout toolbarlayout;
    RecyclerView relatedFlightsRv;
    MulticityFlightsAdapter adapter;
    LinearLayout pagerLayout;
    RelativeLayout sortLayout,filtersLayout,nearLayout,flexibleLayout;
    RelativeLayout LowestPriseGrouped,LowestPriseUnGrouped,DepartTime,Stops,CheapOpick;
    TextView tv_PopupText1,tv_PopupText2,tv_PopupText3,tv_PopupText4,tv_PopupText5;
    ArrayList<String> toCityArray,fromCityArray;
    View popupView;
    public static PopupWindow popupWindow;
    String key=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multicity_flights);


        toolbarlayout=(RelativeLayout)findViewById(R.id.multiFlightsToolBar);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

        tv_fromCity=(TextView)toolbarlayout.findViewById(R.id.tv_fromCity);
        tv_toCity=(TextView)toolbarlayout.findViewById(R.id.tv_toCity);
        tv_fromDate=(TextView)toolbarlayout.findViewById(R.id.tv_fromJourneryDate);
        tv_toDate=(TextView)toolbarlayout.findViewById(R.id.tv_fromJourneryDate);

        PopupArray=new ArrayList<>();
        PopupArray.add("Lowest Price(Grouped)");
        PopupArray.add("Lowest Price(UnGrouped)");
        PopupArray.add("Depart Time");
        PopupArray.add("Stops");
        PopupArray.add("CheapOpick(Shortest&Chaeapest)");

        pagerLayout=(LinearLayout)findViewById(R.id.multiFlitersLay);
        sortLayout=(RelativeLayout)pagerLayout.findViewById(R.id.multiSortLayout);
        filtersLayout=(RelativeLayout)pagerLayout.findViewById(R.id.multiFiltersLayout);
        nearLayout=(RelativeLayout)pagerLayout.findViewById(R.id.multiNearbyLayout);
        flexibleLayout=(RelativeLayout)pagerLayout.findViewById(R.id.multiFlexibleLayout);

        sortImg=(ImageView)sortLayout.findViewById(R.id.pagerImg);
        sortImg.setImageResource(R.mipmap.sort);

        filterImg=(ImageView)filtersLayout.findViewById(R.id.pagerImg);
        filterImg.setImageResource(R.mipmap.filter);

        nearbyImg=(ImageView)nearLayout.findViewById(R.id.pagerImg);
        nearbyImg.setImageResource(R.mipmap.nearby);

        flexiImg=(ImageView)flexibleLayout.findViewById(R.id.pagerImg);
        flexiImg.setImageResource(R.mipmap.flexible);


        sortLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"sort",Toast.LENGTH_SHORT).show();
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.popup_recycler, null);

                rvPopup=(RecyclerView)popupView.findViewById(R.id.popuprecycler);
                adpt = new PopupAdapter(getApplicationContext(), PopupArray,"popup");
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                rvPopup.setLayoutManager(layoutManager);
                rvPopup.setItemAnimator(new DefaultItemAnimator());
                adpt.setOnSecurityQnClickListener(MulticityFlights.this);
                rvPopup.setAdapter(adpt);


                popupWindow = new PopupWindow(
                        popupView,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.showAsDropDown(sortLayout, 40, -10);


                flexibleLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MulticityFlights.this,FlexibleDates.class));
                    }
                });




               /* LowestPriseGrouped=(RelativeLayout)popupView.findViewById(R.id.LowestPriseGrouped);
                tv_PopupText1=(TextView)LowestPriseGrouped.findViewById(R.id.tv_PopupText);
                tv_PopupText1.setText("Lowest Price(Grouped)");

                LowestPriseUnGrouped=(RelativeLayout)popupView.findViewById(R.id.LowestPriseUnGrouped);
                tv_PopupText2=(TextView)LowestPriseUnGrouped.findViewById(R.id.tv_PopupText);
                tv_PopupText2.setText("Lowest Price(UnGrouped)");

                DepartTime=(RelativeLayout)popupView.findViewById(R.id.LowestPriseDepartTime);
                tv_PopupText3=(TextView)DepartTime.findViewById(R.id.tv_PopupText);
                tv_PopupText3.setText("Depart Time");

                Stops=(RelativeLayout)popupView.findViewById(R.id.LowestPriseStops);
                tv_PopupText4=(TextView)Stops.findViewById(R.id.tv_PopupText);
                tv_PopupText4.setText("Stops");

                CheapOpick=(RelativeLayout)popupView.findViewById(R.id.LowestPriseCheapOpick);
                tv_PopupText5=(TextView)CheapOpick.findViewById(R.id.tv_PopupText);
                tv_PopupText5.setText("CheapOpick(Shortest&Chaeapest)");*/



            }
        });

        filtersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MulticityFlights.this,FiltersActivity.class));
            }
        });

        toCityArray=new ArrayList<>();
        fromCityArray=new ArrayList<>();

        toCityArray.add("HYD"); toCityArray.add("VJD");toCityArray.add("RJD"); toCityArray.add("TPT");
        fromCityArray.add("HYD");fromCityArray.add("VJD");fromCityArray.add("RJD"); fromCityArray.add("TPT");

        relatedFlightsRv=(RecyclerView)findViewById(R.id.multiFlightsRv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        relatedFlightsRv.setLayoutManager(layoutManager);
        relatedFlightsRv.setItemAnimator(new DefaultItemAnimator());
        adapter=new MulticityFlightsAdapter(MulticityFlights.this,fromCityArray,toCityArray);
        relatedFlightsRv.setAdapter(adapter);
        adapter.setOnSecurityQnClickListener(this);

    }

    @Override
    public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException {


        startActivity(new Intent(MulticityFlights.this, ReviewActivity.class));
    }
}
