package in.example.skybooker.slider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;
import in.example.skybooker.recycler.DividerItemDecoration;
import in.example.skybooker.slider.flightstatus.ByArrivalAiport;
import in.example.skybooker.slider.flightstatus.ByDepartingAirport;
import in.example.skybooker.slider.flightstatus.ByFlight;
import in.example.skybooker.slider.flightstatus.ByRoute;
import in.example.skybooker.slider.flightstatus.FlightStatusCustomAdapter;

public class FlightStatus extends AppCompatActivity implements FlightStatusCustomAdapter.OnSecurityQnClickListener{

    TextView toolname;
    ImageView toolIcon;
    RecyclerView flightStatusRv;

    ArrayList<String> flightStatusArray;
    FlightStatusCustomAdapter adpter;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_status);

        setToolBar();
/*

        RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.flightStatusToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

        toolname.setText("Flight Status");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
*/

        flightStatusArray=new ArrayList<>();
        Integer ImageName[] = {
                R.mipmap.byfly,
                R.mipmap.bydepart,
                R.mipmap.byarrival,
                R.mipmap.byroute,

        };
        flightStatusArray.add("By Flight #"); flightStatusArray.add("By Departing Airort");
        flightStatusArray.add("By Arrival Airport"); flightStatusArray.add("By Route");
        flightStatusRv=(RecyclerView)findViewById(R.id.welcomeRecycler);
        adpter=new FlightStatusCustomAdapter(flightStatusArray,ImageName,this);

        flightStatusRv=(RecyclerView)findViewById(R.id.flightStatusRv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        flightStatusRv.setLayoutManager(layoutManager);
        flightStatusRv.setItemAnimator(new DefaultItemAnimator());
        adpter.setOnSecurityQnClickListener(FlightStatus.this);
        flightStatusRv.setAdapter(adpter);
        flightStatusRv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.flightStatusToolBar);
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
                startActivity(new Intent(FlightStatus.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(FlightStatus.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(FlightStatus.this);
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
        index=position;
        display(index);
    }

    private void display(int index) {
        if(index==0){
            startActivity(new Intent(FlightStatus.this, ByFlight.class));
        }else  if(index==1){
            startActivity(new Intent(FlightStatus.this, ByDepartingAirport.class));
        }else if(index==2){
            startActivity(new Intent(FlightStatus.this, ByArrivalAiport.class));
        }else if(index==3){
            startActivity(new Intent(FlightStatus.this, ByRoute.class));
        }
    }
}
