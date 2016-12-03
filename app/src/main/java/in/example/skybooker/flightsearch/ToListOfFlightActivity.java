package in.example.skybooker.flightsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import in.example.skybooker.AirportNames;
import in.example.skybooker.DataAdapter;
import in.example.skybooker.R;
import in.example.skybooker.flightsearch.fragments.oneway.OneWayFragment;
import in.example.skybooker.flightsearch.fragments.roundtrip.RoundTripFragment;

public class ToListOfFlightActivity extends Activity{
    EditText etSearch;
    TextView toolname;
    ImageView searchImg,toolIcon;
    ArrayList<AirportNames> arraylist;
    DataAdapter adapter;
    String toAirport;
    ListView list;
    ArrayList<String> airportsList;
    ArrayList<String> tempArrayList;
    String key="",toFlightKey="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            key = bundle.getString("fragment");
            toFlightKey=bundle.getString("toFlight");
            Log.i("key",key+"");
        }


        tempArrayList=new ArrayList<>();
        airportsList=new ArrayList();
        airportsList.add("HYD Hyderabad, India");
        airportsList.add("VGA Vijayawada, India");
        airportsList.add("RJA  Rajahmundry, India");
        airportsList.add("NAG Nagpur, India");
        airportsList.add("TIR Tirupati, India");

        RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.toolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolSearchImg);

        toolname.setText("To Airport");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etSearch=(EditText)findViewById(R.id.etSearch);
        searchImg=(ImageView)findViewById(R.id.searchImg);

        list = (ListView) findViewById(R.id.listview);
        arraylist = new ArrayList<>();

        adapter=new DataAdapter(ToListOfFlightActivity.this,airportsList);
        list.setAdapter(adapter);
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
                // When user changed the Text
                String newText = s.toString();
                tempArrayList.clear();
                if (newText.length() >0) {
                    for (int i = 0; i < airportsList.size(); i++) {
                        //String str  = airportsList.get(i);
                        tempArrayList.add(airportsList.get(i));
                    }
                }
                else {
                    tempArrayList.clear();
                }
                adapter.notifyDataSetChanged();
                /*adapter=new DataAdapter(ToListOfFlightActivity.this,tempArrayList);
                list.setAdapter(adapter);*/

                String text = newText;
                adapter.filter(text);
            }



            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

       /* searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText(" ");
            }
        });*/

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // etSearch.setText(tempArrayList.get(position));
                if(tempArrayList.size()>0){
                    etSearch.setText(tempArrayList.get(position));
                }else{
                    etSearch.setText(airportsList.get(position));
                }

                toAirport= etSearch.getText().toString();
                if (key.equals("oneway")) {
                    OneWayFragment.tvToAirport.setText(toAirport);
                } else if (key.equals("roundtrip")) {
                    RoundTripFragment.tvToAirport.setText(toAirport);
                }else if (key.equals("multicity")) {
                   /* if(toFlightKey.equals("flight1")) {

                        MultiCityFragment.tv_CF1ToCity.setText(toAirport);

                    }else if(toFlightKey.equals("flight2")){
                        MultiCityFragment.tv_CF2ToCity.setText(toAirport);

                    }else if(toFlightKey.equals("flightOptionalTo")){
                        MultiCityFragment.tv_CFlightToCity.setText(toAirport);

                    }*/

                }


               /*    // MultiCityFragment.tv_CF2ToCity.setText(toAirport);
                if(FromListOfFlightsActivity.fromAirport.equals(toAirport)){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ToListOfFlightActivity.this);
                    dialog.setMessage("Both the airports are same!");
                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        }
                    });
                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            // TODO Auto-generated method stub
                        }
                    });+
                    dialog.show();
                }
                else {*/
                Intent intent = new Intent(ToListOfFlightActivity.this, SelectDateActivity.class);
                intent.putExtra("fragment", key);
                intent.putExtra("date","departure");
                intent.putExtra("toFlight",toFlightKey);

                // intent.putExtra("date","return");
                startActivity(intent);
                finish();
                //}
            }
        });
    }
}
