package in.example.skybooker.communication.settings;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;
import in.example.skybooker.recycler.DividerItemDecoration;

public class SettingsCountry extends AppCompatActivity implements SettingsCountryCustomAdapter.OnSecurityQnClickListener {

    TextView toolname;
    static TextView title;
    ImageView toolIcon;
    RecyclerView countryRv;
    EditText eddummy;
    SettingsCountryCustomAdapter adapter;
    static ArrayList<String> countryArrayList;
    Boolean select = false;
    RelativeLayout r1;
    SharedPreferences.Editor editor;
    SharedPreferences sp;
    int requestCode;
    String[] arrC;

    public int us,cnd,uk,aus,mx;
    private RadioButton lastCheckedRB = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_country);
        setToolBar();

       /* RelativeLayout toolbarlayout = (RelativeLayout) findViewById(R.id.settingCountryToolBar);
        toolname = (TextView) toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon = (ImageView) toolbarlayout.findViewById(R.id.toolBackImg);
        toolname.setText("Country");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
*/
        //r1 = (RelativeLayout) toolbarlayout.findViewById(R.id.mainLay);



       /* sp =getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sp.edit();
        us = sp.getInt("type", 0);
        cnd = sp.getInt("type1", 1);
        uk = sp.getInt("type2", 2);
        aus = sp.getInt("type3", 3);
        mx = sp.getInt("type4", 4);
*/


        arrC = getResources().getStringArray(R.array.settings_country);
        ArrayList<String> countryNameList = new ArrayList<String>(Arrays.asList(arrC));
        /*titles = new ArrayList<>();

        titles.add("UnitedStates");
        titles.add("Canada");
        titles.add("Ustralia");
        titles.add("UnitedKingdom");
        titles.add("Mexico");
        for (String value : titles) {

            customList.add(new Event(value));

        }
        for (int i = 0; i < customList.size(); i++) {
            Log.i("ARRAY", customList.get(i).getName() + "");

        }*/
        countryRv = (RecyclerView) findViewById(R.id.settingsCountryRv);
        countryRv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new SettingsCountryCustomAdapter(SettingsCountry.this, countryNameList,"cnames");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        countryRv.setLayoutManager(mLayoutManager);
        countryRv.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnSecurityQnClickListener(SettingsCountry.this);
        countryRv.setAdapter(adapter);



    }

    private void setToolBar() {

        Toolbar tb = (Toolbar) findViewById(R.id.settingCountryToolBar);
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
                startActivity(new Intent(SettingsCountry.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(SettingsCountry.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(SettingsCountry.this);
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





/*
        if (lastCheckedRB != null) {
            lastCheckedRB.setChecked(false);
        //SettingsCountryCustomAdapter.checked_rb.setChecked(true);
        }

            //store the clicked radiobutton
           // lastCheckedRB.setChecked(true);
            //SettingsCountryCustomAdapter.checked_rb.setChecked(true);

//if(position==sp.getInt("type",0)) {
        arrC = getResources().getStringArray(R.array.settings_country);


            Log.i("ARRAYCOU@@#####", arrC[position] + "");
            // for(int i = 0; i < arrC.length; i++) {

        SettingsRvAdapter.tv_settigsCountryTitle.setText(arrC[position]);
            Log.i("ARRAYCOU@@#####", SettingsRvAdapter.tv_settigsCountryTitle.getText() + "");
            editor.putInt("type", 0);
            editor.commit();

            finish();
         SettingsRvAdapter.tv_settigsCountryTitle.setText(arrC[position]);
//}

       Log.i("POS%%%%%",position+"");


      *//*  Intent intentMessage = new Intent();

        // put the message in Intent
        intentMessage.putExtra("MESSAGE", customList.get(position).getName());
        // Set The Result in Intent
        setResult(20, intentMessage);
        Log.i("DBTITLE%%%%", customList.get(position).getName());
        Log.i("POSITION%%%%", position + "");
        finish();*//*
*/
    }



}

