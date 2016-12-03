package in.example.skybooker.flightsearch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.flightsearch.fragments.multicity.MultiCityFragment;
import in.example.skybooker.flightsearch.fragments.oneway.OneWayFragment;
import in.example.skybooker.flightsearch.fragments.roundtrip.RoundTripFragment;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;

public class SearchFlightActivty extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView toolname;
    ImageView toolIcon;
    ActionBar ab;
    public SearchFlightActivty() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);
        setToolBar();

       /* toolname.setText("Flight Search");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

*/
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary));

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.Searchtool_bar);
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        ab.setTitle("Flight Search");
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RoundTripFragment(), "Round Trip");
        adapter.addFragment(new OneWayFragment(), "One Way");
        adapter.addFragment(new MultiCityFragment(), "Multi City");
        viewPager.setAdapter(adapter);

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
                startActivity(new Intent(SearchFlightActivty.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(SearchFlightActivty.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SearchFlightActivty.this);
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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
