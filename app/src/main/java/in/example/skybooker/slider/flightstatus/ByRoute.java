package in.example.skybooker.slider.flightstatus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.flightsearch.FromListOfFlightsActivity;
import in.example.skybooker.flightsearch.ToListOfFlightActivity;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;

public class ByRoute extends AppCompatActivity {

    TextView toolname;
    ImageView toolIcon;
    TextInputLayout byRouteArrivalLayout,byRouteDepartLayout;
    public static EditText et_byRouteArrival,et_byRouteDepart;
    Button btByRoute;
    String str_byRouteArrival,str_byRouteDepart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_route);

        setToolBar();

      /*  RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.byRouteToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

        toolname.setText("Route");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
        byRouteArrivalLayout=(TextInputLayout)findViewById(R.id.text_input_byRouteArrivalAirportLayout);
        byRouteDepartLayout=(TextInputLayout)findViewById(R.id.text_input_byRouteDepartLayout);

        et_byRouteArrival=(EditText)findViewById(R.id.et_btRouteArrivalAirportLayout);
        et_byRouteDepart=(EditText)findViewById(R.id.et_byRoutedepartLayout);
        btByRoute=(Button)findViewById(R.id.btbyRouteDepartAirport);
        btByRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationByRoute();
            }
        });

        et_byRouteDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByRoute.this, FromListOfFlightsActivity.class);
                intent.putExtra("fragment","byRouteFromAirport");
                startActivity(intent);
            }
        });

        et_byRouteArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ByRoute.this, ToListOfFlightActivity.class);
                intent.putExtra("fragment","byRouteToAirport");
                startActivity(intent);
            }
        });

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.byRouteToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Route");
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
                startActivity(new Intent(ByRoute.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(ByRoute.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(ByRoute.this);
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



    private void validationByRoute() {

        str_byRouteArrival=et_byRouteArrival.getText().toString();
        str_byRouteDepart=et_byRouteDepart.getText().toString();

        if (str_byRouteDepart.length() == 0 || str_byRouteDepart.length() == ' ') {
            byRouteDepartLayout.setErrorEnabled(true);
            byRouteDepartLayout.setError("Please select depart airport");
        }else if(str_byRouteArrival.length()==0||str_byRouteArrival.length()==' '){
            byRouteArrivalLayout.setError("Please select arrival airport");
            byRouteArrivalLayout.setErrorEnabled(true);
        }
        else if(str_byRouteDepart.equals(str_byRouteArrival)){
            AlertDialog.Builder dialog = new AlertDialog.Builder(ByRoute.this);
            dialog.setTitle(" Oops! The airport you depart from and return to can't be the same.")
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                        }

                    });
            dialog.create().show();
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ByRoute.this);
            dialog.setTitle(" .")
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                        }

                    });
            dialog.create().show();
        }
    }
}
