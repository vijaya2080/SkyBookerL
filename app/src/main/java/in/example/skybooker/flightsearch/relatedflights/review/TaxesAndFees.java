package in.example.skybooker.flightsearch.relatedflights.review;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.example.skybooker.R;

public class TaxesAndFees extends AppCompatActivity {
TextView toolname;
    ImageView toolIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxex_and_fees);
       // setToolBar();
        RelativeLayout toollayout=(RelativeLayout)findViewById(R.id.taxesTool_bar);
        toolname = (TextView) toollayout.findViewById(R.id.toolBarTitle);
        toolIcon = (ImageView) toollayout.findViewById(R.id.toolBackImg);
        toolname.setText("Taxes And Fees");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

   /* private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.taxesTool_bar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Taxes And Fees");
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
                startActivity(new Intent(TaxesAndFees.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(TaxesAndFees.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(TaxesAndFees.this);
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
    }*/
}
