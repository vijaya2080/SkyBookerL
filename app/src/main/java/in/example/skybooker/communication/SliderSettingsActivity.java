package in.example.skybooker.communication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import in.example.skybooker.R;
import in.example.skybooker.communication.settings.SettingsCountry;
import in.example.skybooker.communication.settings.SettingsCurrency;
import in.example.skybooker.communication.settings.SettingsRvAdapter;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;
import in.example.skybooker.recycler.DividerItemDecoration;

public class SliderSettingsActivity extends AppCompatActivity implements SettingsRvAdapter.OnSecurityQnClickListener{

    TextView toolname,dummy;
    ImageView toolIcon;
    RecyclerView sliderSettingsRecycler;
    SettingsRvAdapter adapter;
    int index;
    ArrayList<String> settingsCountryTitle = new ArrayList<>();
    //ArrayList<String> settingsCountry = new ArrayList<>();
    ArrayList<String> countryNameList = new ArrayList<>();
    ArrayList<String> currencyNameList = new ArrayList<>();
    ArrayList<String> settingsCountry = new ArrayList<>();
    SharedPreferences sp;
    private static final int MY_REQUEST = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_settings);

        sp= getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        setToolBar();

       /* RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.sliderSettingsToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;
        toolname.setText("Settings");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
*/
        //settingsCountryTitle= new String[]{"Country","Currency"};
        //settingsCountry= new String[]{"United States","USD ($)"};

        String[] countryName = getResources().getStringArray(R.array.settings_country);
        String[] currencyName = getResources().getStringArray(R.array.settings_currency);

        countryNameList = new ArrayList<String>(Arrays.asList(countryName));
        currencyNameList = new ArrayList<String>(Arrays.asList(currencyName));

        Log.i("pref...",sp.getInt("currency",0)+"");
        settingsCountryTitle.add("Country");
        settingsCountryTitle.add("Currency");

        settingsCountry.add(countryNameList.get(sp.getInt("currency",0)));
        settingsCountry.add(currencyNameList.get(sp.getInt("currency",0)));


        //settingsCurrency.add(countryNameList.get(sp.getInt("currency",0)));
        //settingsCurrency.add(currencyNameList.get(sp.getInt("currency",0)));
        //settingsCountry=new ArrayList<>();
        // settingsCountryTitle=new ArrayList<>();
        // settingsCountryTitle["Country"];
        // settingsCountryTitle.add("Currency");
        //  settingsCountry.add("United States");
        //  settingsCountry.add("USD ($)");


        // customcountry=new ArrayList<Event>();
        // customcurrency=new ArrayList<Event>();
       /* for (String value : settingsCountryTitle) {

            customcountry.add(new Event(value));

        }
        for (int i = 0; i < customcountry.size(); i++) {
            Log.i("ARRAY", customcountry.get(i).getName() + "");

        }
        for (String value : settingsCountry) {

            customcurrency.add(new Event(value));

        }
        for (int i = 0; i < customcountry.size(); i++) {
            Log.i("ARRAY", customcurrency.get(i).getName() + "");

        }


*/
        sliderSettingsRecycler=(RecyclerView)findViewById(R.id.sliderRv) ;
        adapter=new SettingsRvAdapter(this,settingsCountryTitle,settingsCountry);

        sliderSettingsRecycler=(RecyclerView)findViewById(R.id.sliderSettingsRv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        sliderSettingsRecycler.setLayoutManager(layoutManager);
        sliderSettingsRecycler.setItemAnimator(new DefaultItemAnimator());
        sliderSettingsRecycler.setAdapter(adapter);
        adapter.setOnSecurityQnClickListener(this);
        sliderSettingsRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private void setToolBar() {

        Toolbar tb = (Toolbar) findViewById(R.id.sliderSettingsToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Review");
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case  R.id.action_settings: showDialog();
                return true;
            case R.id.terms:
                startActivity(new Intent(SliderSettingsActivity.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(SliderSettingsActivity.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(SliderSettingsActivity.this);
        dialog.setMessage("Want to make a call?");
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
        });
        dialog.show();
    }



    @Override
    public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException {
        //   Toast.makeText(SliderSettingsActivity.this,position,Toast.LENGTH_LONG).show();
        index=position;
        displayView(index);

    }

    private void displayView(int index) {

        if(index==0){
            Intent intentGetMessage=new Intent(SliderSettingsActivity.this,SettingsCountry.class);
            startActivityForResult(intentGetMessage,20);

        } else if(index==1){
            Intent i = new Intent(SliderSettingsActivity.this, SettingsCurrency.class);
            startActivityForResult(i,40);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.i("Slider settings Act","onActivityResult");
        Log.i("pref...",sp.getInt("currency",0)+"");
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(requestCode+"",resultCode+"");
        Log.i((requestCode==20)+"",(resultCode == RESULT_OK)+"");
        Log.i((requestCode==40)+"",(resultCode == RESULT_OK)+"");
        // check if the request code is same as what is passed  here it is 2
        if(requestCode == 20 ) {
            settingsCountry.clear();
            settingsCountry.add(countryNameList.get(sp.getInt("currency",0)));
            settingsCountry.add(currencyNameList.get(sp.getInt("currency",0)));
            Log.i("psettingsCountryref...",settingsCountry+"");
            adapter.notifyDataSetChanged();
            finish();
        } else if(requestCode==40 ) {
            settingsCountry.clear();

            settingsCountry.add(countryNameList.get(sp.getInt("currency",0)));
            settingsCountry.add(currencyNameList.get(sp.getInt("currency",0)));
            Log.i("psettingsCurrencyref...",settingsCountry+"");
            adapter.notifyDataSetChanged();

        }
    }
}