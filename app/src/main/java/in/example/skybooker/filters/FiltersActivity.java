package in.example.skybooker.filters;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.example.skybooker.R;

public class FiltersActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView toolname,tv_matchedNo,tv_cheapestPrice,tv_cheapetPriceText;
    ImageView toolIcon;

    RelativeLayout cheapestPriceLay,matchedResultsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.filtersToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolSearchImg) ;

        toolname.setText("Fliters");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cheapestPriceLay=(RelativeLayout)findViewById(R.id.cheapestPriceLayout);
        matchedResultsLayout=(RelativeLayout)findViewById(R.id.matchedFlightsLayout);

        tv_matchedNo=(TextView)matchedResultsLayout.findViewById(R.id.filterNumber);

        tv_cheapestPrice=(TextView)cheapestPriceLay.findViewById(R.id.filterNumber);
        tv_cheapetPriceText=(TextView)cheapestPriceLay.findViewById(R.id.filterText);
        tv_cheapetPriceText.setText("Cheapest Price");





        viewPager = (ViewPager) findViewById(R.id.filterViewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.FiltersTabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PriceFilters(), "PRICE");
        adapter.addFragment(new StopsFilters(), "STOPS");
        adapter.addFragment(new TimeFilters(), "TIME");
        adapter.addFragment(new AirlineFilters(), "AIRLINE");
        adapter.addFragment(new AirportFilters(), "AIRPORT");
        viewPager.setAdapter(adapter);

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
