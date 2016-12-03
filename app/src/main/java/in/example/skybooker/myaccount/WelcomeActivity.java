package in.example.skybooker.myaccount;

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
import in.example.skybooker.myaccount.billing.BillingDetailsActivity;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;
import in.example.skybooker.myaccount.cotravelers.CoTravellersActivity;
import in.example.skybooker.myaccount.mybookings.MyBookings;
import in.example.skybooker.myaccount.mydetails.MyDetailsActivity;
import in.example.skybooker.myaccount.mywhishlist.MyWishList;
import in.example.skybooker.myaccount.password.PasswordActivity;
import in.example.skybooker.myaccount.rewards.RewardsActivity;
import in.example.skybooker.recycler.DividerItemDecoration;

public class WelcomeActivity extends AppCompatActivity implements WelcomeAdpter.OnSecurityQnClickListener {

    TextView toolname;
    ImageView toolIcon;
    RecyclerView welcomeRv;
    ArrayList<String> welcomeArray;
    ArrayList<Integer> welcomeIcons;
    WelcomeAdpter adp;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setToolBar();
      /*  RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.welcomeToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

        toolname.setText("Welcome");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        welcomeArray=new ArrayList<>();
        welcomeArray.add("My Details");
        welcomeArray.add("Billing Details");
        welcomeArray.add("My Rewards");
        welcomeArray.add("Co- Travelers");
        welcomeArray.add("My Bookings");
        welcomeArray.add("My Password");
        welcomeArray.add("My Wishlist");

        Integer ImageName[] = {
                R.mipmap.profile,
                R.mipmap.billing,
                R.mipmap.welcomerewards,
                R.mipmap.cotravellers,
                R.mipmap.bookings,
                R.mipmap.welpwd,
                R.mipmap.wishlist
        };


        welcomeIcons=new ArrayList<>();

        welcomeRv=(RecyclerView)findViewById(R.id.welcomeRecycler);
        adp=new WelcomeAdpter(welcomeArray,ImageName,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        welcomeRv.setLayoutManager(layoutManager);
        welcomeRv.setItemAnimator(new DefaultItemAnimator());
        adp.setOnSecurityQnClickListener(WelcomeActivity.this);
        welcomeRv.setAdapter(adp);
        welcomeRv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private void setToolBar() {

        Toolbar tb = (Toolbar) findViewById(R.id.welcomeToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Welcome");
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
                startActivity(new Intent(WelcomeActivity.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(WelcomeActivity.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                //onBackPressed();    //Call the back button's method
                this.finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }
    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(WelcomeActivity.this);
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
        //Toast.makeText(getApplicationContext(),"clicked on"+position+" ",Toast.LENGTH_SHORT).show();
        index=position;
        displayView(index);
    }

    private void displayView(int index) {

        if(index==0){
            startActivity(new Intent(WelcomeActivity.this,MyDetailsActivity.class));
        }
        else if(index==1){
            startActivity(new Intent(WelcomeActivity.this,BillingDetailsActivity.class));
        }
        else if(index==2){
            startActivity(new Intent(WelcomeActivity.this,RewardsActivity.class));
        }
        else if(index==3){
            startActivity(new Intent(WelcomeActivity.this,CoTravellersActivity.class));
        }else if(index==4){
           startActivity(new Intent(WelcomeActivity.this,MyBookings.class));
        }
        else  if(index==5)
        {
            startActivity(new Intent(WelcomeActivity.this,PasswordActivity.class));
        }
        else if(index==6){
            startActivity(new Intent(WelcomeActivity.this,MyWishList.class));
        }

    }

}
