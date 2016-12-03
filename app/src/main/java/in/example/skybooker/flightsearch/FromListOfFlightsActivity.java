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

import in.example.skybooker.DataAdapter;
import in.example.skybooker.R;
import in.example.skybooker.flightsearch.fragments.oneway.OneWayFragment;
import in.example.skybooker.flightsearch.fragments.roundtrip.RoundTripFragment;

/**
 * Created by siris on 9/28/2016.
 */
public class FromListOfFlightsActivity extends Activity {
    EditText etSearch;
    //ArrayList<AirportNames> arraylist;
    DataAdapter adapter;
    public static String fromAirport;
    ListView list;
    ArrayList<String> airportsList;
    ArrayList<String> tempArrayList;
    ImageView searachIcon,toolIcon;
    String key="",fromFlightKey=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            key = bundle.getString("fragment");
            fromFlightKey=bundle.getString("fromFlight");
            Log.i("key",key+"");
        }

        airportsList=new ArrayList();
        tempArrayList=new ArrayList<>();
        airportsList.add("HYD  Hyderabad, India");
        airportsList.add("VGA Vijayawada, India");
        airportsList.add("RJA Rajahmundry, India");
        airportsList.add("NAG Nagpur, India");
        airportsList.add("TPT Tirupati, India");

        RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.toolBar);
        TextView toolname=(TextView)toolbarlayout.findViewById(R.id.toolTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolSearchImg) ;

        toolname.setText("From Airport");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searachIcon=(ImageView)findViewById(R.id.searchImg);

        etSearch=(EditText)findViewById(R.id.etSearch);
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        list = (ListView) findViewById(R.id.listview);

        /**
         * Enabling Search Filter
         * */
        adapter=new DataAdapter(this,airportsList);
        list.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                /*adapter.getFilter().filter(s.toString());*/
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
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
                /*adapter=new DataAdapter(FromListOfFlightsActivity.this,tempArrayList);
                list.setAdapter(adapter);*/

                String text = newText;
                adapter.filter(text);
            }
        });

      /*  searachIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText(" ");
            }
        });*/

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Log.i("Airports",airportsList+"");
                if(tempArrayList.size()>0){
                    etSearch.setText(tempArrayList.get(position));
                }else{
                    etSearch.setText(airportsList.get(position));
                }

                fromAirport=  etSearch.getText().toString();
                Log.i("FromAirport",fromAirport+"");
                if (key.equals("oneway")) {
                    Log.i("keyvalue",key+"");
                    OneWayFragment.tvFromAirport.setText(fromAirport);
                } else if (key.equals("roundtrip")) {
                    RoundTripFragment.tvFromAirport.setText(fromAirport);

                }else if (key.equals("multicity")) {
                   /* if(fromFlightKey.equals("flight1")) {
                        MultiCityFragment.tv_CF1FromCity.setText(fromAirport);
                    }else if(fromFlightKey.equals("flight2")){
                        MultiCityFragment.tv_CF2FromCity.setText(fromAirport);
                    }else if(fromFlightKey.equals("flightOptionalFrom"))
                    {   MultiCityFragment.tv_CFlightFromCity.setText(fromAirport);
                    }*/
                }





                Intent intent = new Intent(FromListOfFlightsActivity.this, ToListOfFlightActivity.class);
                intent.putExtra("fragment",key);
                intent.putExtra("fromFlight",fromFlightKey);
                startActivity(intent);
                finish();

            }

        });
    }


  /*  public void setList() {

        airportsList = new ArrayList<AirportNames>();

        AirportNames item = new AirportNames();
        item.setAirportName("HYD- Hyderabad, India");
        airportsList.add(item);

        item = new AirportNames();
        item.setAirportName("VGA- Vijayawada, India");
        airportsList.add(item);

        item = new AirportNames();
        item.setAirportName("RJA- Rajahmundry, India");
        airportsList.add(item);

        item = new AirportNames();
        item.setAirportName("NAG-Nagpur, India");
        airportsList.add(item);

        item = new AirportNames();
        item.setAirportName("TIR- Tirupati, India");
        airportsList.add(item);

        for (int i = 0; i < 10000; i++) {
            item = new AirportNames();
            item.setAirportName("HYD- Hyderabad, India");
            airportsList.add(item);
        }
    }*/
   /* private void setupSearchView() {
        editsearch.setIconifiedByDefault(false);
        editsearch.setOnQueryTextListener(this);
        editsearch.setSubmitButtonEnabled(true);
        editsearch.setQueryHint("Enter City name");
    }


    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //  int i=0;
        if (newText.length() == 3) {
            for (int i = 0; i < animalNameList.length; i++) {
                AirportNames animalNames = new AirportNames(animalNameList[i]);



                // Binds all strings into an array
                arraylist.add(animalNames);
            }
        }
        else
        {
            arraylist.clear();
        }

        adapter = new DataAdapter(this, arraylist);
        list.setAdapter(adapter);

        String text = newText;
        adapter.filter(text);
        return false;
    }*/
}
