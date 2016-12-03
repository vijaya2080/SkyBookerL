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
import in.example.skybooker.flightsearch.FromListOfFlightsActivity;
import in.example.skybooker.flightsearch.ToListOfFlightActivity;
import in.example.skybooker.myaccount.RecyclerTitlesAdapter;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;

public class ByArrivalAiport extends AppCompatActivity implements RecyclerTitlesAdapter.OnSecurityQnClickListener {

    TextView toolname;
    ImageView toolIcon;
    TextInputLayout byArrivalArrivalLayout,byArrivalDepartLayout,byArrivalAirlineLayout,byArrivalDepartTimeLayout;
    public static EditText et_byArrivalArrival,et_byArrivalDepart,et_byArrivalAirline,et_byArrivalDepartTime;
    Button btByArrival;
    String str_byArrivalArrival,str_byArrivalDepart,str_byArrivalAirline,str_byArrivalDepartTime;
    AlertDialog alert;
    ArrayList<String> airlineArray,arrivalTimeArray;
    RecyclerView airlineRv;
    RecyclerTitlesAdapter mAdapter;
    String spinnerKey=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_departing_airport);

        setToolBar();

        arrivalTimeArray=new ArrayList<>();
        airlineArray=new ArrayList<>();

       /* RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.departAirportToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

        toolname.setText("Arrival Airport");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        byArrivalArrivalLayout=(TextInputLayout)findViewById(R.id.text_input_arrivalAirportLayout);
        byArrivalDepartLayout=(TextInputLayout)findViewById(R.id.text_input_departAirportLayout);
        byArrivalAirlineLayout=(TextInputLayout)findViewById(R.id.text_input_DepartAirlineLayout);
        byArrivalDepartTimeLayout=(TextInputLayout)findViewById(R.id.text_input_departDepartTimeLayout);

        et_byArrivalArrival=(EditText)findViewById(R.id.et_arrivalAirportLayout);
        et_byArrivalDepart=(EditText)findViewById(R.id.et_departAirportLayout);
        et_byArrivalAirline=(EditText)findViewById(R.id.et_departAirlineLayout);
        et_byArrivalDepartTime=(EditText)findViewById(R.id.et_DepartTimeLayout);

        btByArrival=(Button)findViewById(R.id.btDepartAirport);
        btByArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationByArrival();
            }
        });

        et_byArrivalArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByArrivalAiport.this, FromListOfFlightsActivity.class);
                intent.putExtra("fragment","byArrivalFromAirport");
                startActivity(intent);
            }
        });
        et_byArrivalDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByArrivalAiport.this, ToListOfFlightActivity.class);
                intent.putExtra("fragment","byArrivalToAirport");
                startActivity(intent);
            }
        });

        et_byArrivalAirline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                byArrivalAirlineLayout.setErrorEnabled(false);
                LayoutInflater layoutInflater = LayoutInflater.from(ByArrivalAiport.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ByArrivalAiport.this);
                alertDialogBuilder.setView(promptView1);

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

                spinnerKey = "airline";
                mAdapter = new RecyclerTitlesAdapter(ByArrivalAiport.this, airlineArray);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                airlineRv.setLayoutManager(mLayoutManager);
                airlineRv.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(ByArrivalAiport.this);
                Log.i("TITLESLIST***", airlineRv + "");
                airlineRv.setAdapter(mAdapter);

            }
        });
        et_byArrivalDepartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byArrivalDepartTimeLayout.setErrorEnabled(false);
                LayoutInflater layoutInflater = LayoutInflater.from(ByArrivalAiport.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ByArrivalAiport.this);
                alertDialogBuilder.setView(promptView1);

                alert = alertDialogBuilder.create();
                alert.show();
                arrivalTimeArray.clear();
                arrivalTimeArray.add("Crazy early before 05:00 am");
                arrivalTimeArray.add("Early morning 05:00 am - 08:00 am");
                arrivalTimeArray.add("Morning 08:00 am - 11:00 am");
                arrivalTimeArray.add("Noon 11:00 am - 01:00 pm");
                arrivalTimeArray.add("Afternoon 01:00 pm - 04:00 pm");
                arrivalTimeArray.add("Evening 04:00 pm - 08:00 pm");
                arrivalTimeArray.add("Late Night after 08:00 pm");
                airlineRv = (RecyclerView) promptView1.findViewById(R.id.titlelist);

                spinnerKey = "arrivalTime";
                mAdapter = new RecyclerTitlesAdapter(ByArrivalAiport.this, arrivalTimeArray);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                airlineRv.setLayoutManager(mLayoutManager);
                airlineRv.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(ByArrivalAiport.this);
                Log.i("TITLESLIST***", airlineRv + "");
                airlineRv.setAdapter(mAdapter);
            }
        });

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.departAirportToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Arrival Airport");
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
                startActivity(new Intent(ByArrivalAiport.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(ByArrivalAiport.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(ByArrivalAiport.this);
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



    private void validationByArrival() {
        str_byArrivalArrival=et_byArrivalArrival.getText().toString();
        str_byArrivalDepart=et_byArrivalDepart.getText().toString();
        str_byArrivalAirline=et_byArrivalAirline.getText().toString();
        str_byArrivalDepartTime=et_byArrivalDepartTime.getText().toString();

       /* if(str_byArrivalDepart.length()==0||str_byArrivalDepart.length()==' '){
            byArrivalDepartLayout.setError("Please select arrival airport ");
            byArrivalDepartLayout.setErrorEnabled(true);
        }*/
        if(str_byArrivalArrival.length()==0||str_byArrivalArrival.length()==' '){
            byArrivalArrivalLayout.setError("Please select depart airport ");
            byArrivalArrivalLayout.setErrorEnabled(true);
        }/*else if(str_byArrivalAirline.length()==0||str_byArrivalAirline.length()==' '){

            byArrivalAirlineLayout.setErrorEnabled(true);
            byArrivalAirlineLayout.setError(" ");
        }*/else if(str_byArrivalDepartTime.length()==0||str_byArrivalDepartTime.length()==' '){
            byArrivalDepartTimeLayout.setError("Please select arrival time ");
            byArrivalDepartTimeLayout.setErrorEnabled(true);
        }
        else if(str_byArrivalArrival.equals(str_byArrivalDepart)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(ByArrivalAiport.this);
            dialog.setTitle(" Oops! The airport you depart from and return to can't be the same.")
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                        }

                    });
            dialog.create().show();
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ByArrivalAiport.this);
            dialog.setTitle(" .")
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
        if(spinnerKey.equals("airline")) {
            et_byArrivalAirline.setText(airlineArray.get(position));
        }else if(spinnerKey.equals("arrivalTime")){
            et_byArrivalDepartTime.setText(arrivalTimeArray.get(position));
        }
        alert.dismiss();
    }
}
