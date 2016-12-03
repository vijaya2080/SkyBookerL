package in.example.skybooker.slider.flightstatus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.myaccount.RecyclerTitlesAdapter;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;

public class ByFlight extends AppCompatActivity implements RecyclerTitlesAdapter.OnSecurityQnClickListener{

    TextView toolname;
    ImageView toolIcon;
    TextInputLayout byFlightAirlineLayout,byFlightFlyNoLayout;
    EditText et_byFlightAirlineLayout,et_byFlightFlyNoLayout;
    Button btByFlight;
    String str_byFlightAirline,str_byFlightFlyNo;
    AlertDialog alert;
    ArrayList<String> airlineArray;
    RecyclerView airlineRv;
    RecyclerTitlesAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_flight);

        setToolBar();

        airlineArray=new ArrayList<>();

       /* RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.byFlightToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

        toolname.setText("Flight Status");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        byFlightAirlineLayout=(TextInputLayout)findViewById(R.id.text_input_byFlightAirlineLayout);
        byFlightFlyNoLayout=(TextInputLayout)findViewById(R.id.text_input_byFlightFlightNoLayout);

        et_byFlightAirlineLayout=(EditText)findViewById(R.id.et_byFlightAirlineLayout);
        et_byFlightFlyNoLayout=(EditText)findViewById(R.id.et_byFlightFlightNoLayout);

        btByFlight=(Button)findViewById(R.id.btByFlight);


        et_byFlightAirlineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                byFlightAirlineLayout.setErrorEnabled(false);
                LayoutInflater layoutInflater = LayoutInflater.from(ByFlight.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ByFlight.this);
                alertDialogBuilder.setView(promptView1);

                // setup a dialog window
                // alertDialogBuilder.setCancelable(false);

                // create an alert dialog
                alert = alertDialogBuilder.create();
                alert.show();
                airlineArray.clear();
                airlineArray.add("ADA");
                airlineArray.add("AIR");
                airlineArray.add("ALMA");
                airlineArray.add("ANA");
                airlineArray.add("ASKy");
                airlineArray.add("AURORA");
                airlineRv = (RecyclerView) promptView1.findViewById(R.id.titlelist);

                //spinnerKey = "title";
                mAdapter = new RecyclerTitlesAdapter(ByFlight.this, airlineArray);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                airlineRv.setLayoutManager(mLayoutManager);
                airlineRv.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(ByFlight.this);
                Log.i("TITLESLIST***", airlineRv + "");
                airlineRv.setAdapter(mAdapter);

            }
        });

        btByFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateByFlight();
            }
        });

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.byFlightToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Flight Status");
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
                startActivity(new Intent(ByFlight.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(ByFlight.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(ByFlight.this);
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



    private void validateByFlight() {

        str_byFlightAirline=et_byFlightAirlineLayout.getText().toString();
        str_byFlightFlyNo=et_byFlightFlyNoLayout.getText().toString();

        if(str_byFlightAirline.length()==0||str_byFlightAirline.length()==' '){
            byFlightAirlineLayout.setErrorEnabled(true);
            byFlightAirlineLayout.setError("Please select the airline");
        }else if(str_byFlightFlyNo.length()==0||str_byFlightFlyNo.length()==' '){
            byFlightFlyNoLayout.setError("Please enter the flight number");
            byFlightFlyNoLayout.setErrorEnabled(true);
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ByFlight.this);
            dialog.setTitle("  .")
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                        }

                    });
            dialog.create().show();
        }

    }

    @Override
    public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException {
        et_byFlightAirlineLayout.setText(airlineArray.get(position));
        alert.dismiss();
    }
}
