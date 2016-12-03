package in.example.skybooker.myaccount.cotravelers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;

public class CoTravellersActivity extends AppCompatActivity {

    TextView toolname;
    ImageView toolIcon;
    RelativeLayout addLay,removeLay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_travellers);
        setToolBar();

      /*  RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.coTravellerToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

        toolname.setText("Co-Travelers");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
*/
       addLay=(RelativeLayout)findViewById(R.id.addCoTravellerLay);
        addLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CoTravellersActivity.this,AddCoTravelerActivity.class));
            }
        });
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.coTravellerToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Co-Travelers");
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
                startActivity(new Intent(CoTravellersActivity.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(CoTravellersActivity.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(CoTravellersActivity.this);
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
}
