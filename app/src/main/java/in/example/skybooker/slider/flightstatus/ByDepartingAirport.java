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

public class ByDepartingAirport extends AppCompatActivity implements RecyclerTitlesAdapter.OnSecurityQnClickListener{

    TextView toolname;
    ImageView toolIcon;
    TextInputLayout byDepartingArrivalLayout,byDepartingDepartLayout,byDepartingAirlineLayout,byDepartingDepartTimeLayout;
    public static EditText et_byDepartingArrival,et_byDepartingDepart,et_byDepartingAirline,et_byDepartingDepartTime;
    Button btByDeparting;
    String str_byDepartingArrival,str_byDepartingDepart,str_byDepartingAirline,str_byDepartingDepartTime;
    ArrayList<String> airlineArray,arrivalTimeArray;
    RecyclerView airlineRv;
    RecyclerTitlesAdapter mAdapter;
    AlertDialog alert;
    String spinnerKey=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_departing_airport);

        setToolBar();

       /* RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.departAirportToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

        toolname.setText("Departing Airport");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        arrivalTimeArray=new ArrayList<>();
        airlineArray=new ArrayList<>();

        byDepartingDepartLayout=(TextInputLayout)findViewById(R.id.text_input_departAirportLayout);
        byDepartingArrivalLayout=(TextInputLayout)findViewById(R.id.text_input_arrivalAirportLayout);
        byDepartingAirlineLayout=(TextInputLayout)findViewById(R.id.text_input_DepartAirlineLayout);
        byDepartingDepartTimeLayout=(TextInputLayout)findViewById(R.id.text_input_departDepartTimeLayout);

        et_byDepartingArrival=(EditText)findViewById(R.id.et_arrivalAirportLayout);
        et_byDepartingDepart=(EditText)findViewById(R.id.et_departAirportLayout);
        et_byDepartingAirline=(EditText)findViewById(R.id.et_departAirlineLayout);
        et_byDepartingDepartTime=(EditText)findViewById(R.id.et_DepartTimeLayout);

        btByDeparting=(Button)findViewById(R.id.btDepartAirport);

        btByDeparting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationByDeparting();
            }
        });

        et_byDepartingDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ByDepartingAirport.this, FromListOfFlightsActivity.class);
                intent.putExtra("fragment","byDepartFromAirport");
                startActivity(intent);
            }
        });
        et_byDepartingArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByDepartingAirport.this, ToListOfFlightActivity.class);
                intent.putExtra("fragment","byDepartToAirport");
                startActivity(intent);
            }
        });

        et_byDepartingAirline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                byDepartingAirlineLayout.setErrorEnabled(false);
                LayoutInflater layoutInflater = LayoutInflater.from(ByDepartingAirport.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ByDepartingAirport.this);
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
                mAdapter = new RecyclerTitlesAdapter(ByDepartingAirport.this, airlineArray);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                airlineRv.setLayoutManager(mLayoutManager);
                airlineRv.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(ByDepartingAirport.this);
                Log.i("TITLESLIST***", airlineRv + "");
                airlineRv.setAdapter(mAdapter);

            }
        });
        et_byDepartingDepartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byDepartingDepartTimeLayout.setErrorEnabled(false);
                LayoutInflater layoutInflater = LayoutInflater.from(ByDepartingAirport.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ByDepartingAirport.this);
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
                mAdapter = new RecyclerTitlesAdapter(ByDepartingAirport.this, arrivalTimeArray);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                airlineRv.setLayoutManager(mLayoutManager);
                airlineRv.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(ByDepartingAirport.this);
                Log.i("TITLESLIST***", airlineRv + "");
                airlineRv.setAdapter(mAdapter);
            }
        });

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.departAirportToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Departing Airport");
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
                startActivity(new Intent(ByDepartingAirport.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(ByDepartingAirport.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(ByDepartingAirport.this);
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



    private void validationByDeparting() {

        str_byDepartingArrival=et_byDepartingArrival.getText().toString();
        str_byDepartingAirline=et_byDepartingArrival.getText().toString();
        str_byDepartingDepartTime=et_byDepartingDepartTime.getText().toString();
        str_byDepartingDepart=et_byDepartingDepart.getText().toString();

        if(str_byDepartingDepart.length()==0||str_byDepartingDepart.length()==' '){
            byDepartingDepartLayout.setErrorEnabled(true);
            byDepartingDepartLayout.setError("Please select depart airport  ");
        }/* else if(str_byDepartingArrival.length()==0||str_byDepartingArrival.length()==' '){
            byDepartingArrivalLayout.setError(" ");
            byDepartingArrivalLayout.setErrorEnabled(true);
        }else if(str_byDepartingAirline.length()==0||str_byDepartingAirline.length()==' '){
            byDepartingAirlineLayout.setError(" ");
            byDepartingAirlineLayout.setErrorEnabled(true);
        }*/else if(str_byDepartingDepartTime.length()==0||str_byDepartingDepartTime.length()==' '){
            byDepartingDepartTimeLayout.setErrorEnabled(true);
            byDepartingDepartTimeLayout.setError(" Please Select depart time");
        }
        else if(str_byDepartingDepart.equals(str_byDepartingArrival)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(ByDepartingAirport.this);
            dialog.setTitle(" Oops! The airport you depart from and return to can't be the same.")
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                        }

                    });
            dialog.create().show();
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ByDepartingAirport.this);
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
            et_byDepartingArrival.setText(airlineArray.get(position));
        }else if(spinnerKey.equals("arrivalTime")){
            et_byDepartingDepartTime.setText(arrivalTimeArray.get(position));
        }
        alert.dismiss();
    }
}
