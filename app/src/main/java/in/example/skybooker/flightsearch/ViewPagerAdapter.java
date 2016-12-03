package in.example.skybooker.flightsearch;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viewpagerindicator.IconPagerAdapter;

import java.util.ArrayList;

import in.example.skybooker.R;

public class ViewPagerAdapter extends PagerAdapter implements
        IconPagerAdapter
    {
    // Declare Variables
    Context context;
    ArrayList<ViewPagerEnum> pagerArrayList;
    LayoutInflater inflater;
//        private int mCount = pagerArrayList.size();
    public ViewPagerAdapter(Context context,ArrayList<ViewPagerEnum> pagerArrayList ){
        this.context = context;
       this.pagerArrayList=pagerArrayList;
        Log.i("pagerArrayList",pagerArrayList+"");
    }

        @Override
        public int getIconResId(int index) {
            return pagerArrayList.size();
        }


        @Override
    public int getCount() {
        return pagerArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }



        @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView from_city;
        TextView to_city;
        TextView day;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.customviewpager_oneway, container, false);

        // Locate the TextViews in viewpager_item.xml
        from_city = (TextView) itemView.findViewById(R.id.viewpager_tvToAndFromTextBefore);
        to_city = (TextView) itemView.findViewById(R.id.tvTo_viewpager);
        day = (TextView) itemView.findViewById(R.id.viewpager_day);

        final ViewPagerEnum viewpagerenum = pagerArrayList.get(position);

        // Capture position and set to the TextViews
        from_city.setText(viewpagerenum.getFrom_City());
        to_city.setText(viewpagerenum.getTo_City());
        day.setText(viewpagerenum.getDate());

        // Locate the ImageView in viewpager_item.xml

        // Capture position and set to the ImageView
        //imgflag.setImageResource(flag[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}