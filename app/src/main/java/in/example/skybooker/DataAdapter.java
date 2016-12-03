package in.example.skybooker;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.zip.Inflater;

public class DataAdapter extends BaseAdapter {
    public  static String dataNames;
    ArrayList<String> airportsList;
    ArrayList<String> tempAirportsList;
    public static ArrayList<String> FilteredArrayNames;
    Context c;
    LayoutInflater inflater;
    MyViewHolder mViewHolder;
    String characters="";

    public DataAdapter(Context c, ArrayList<String> airportsList){
        this.c=c;
        this.airportsList=airportsList;
        tempAirportsList = new ArrayList<>();
        tempAirportsList.addAll(airportsList);
        inflater = LayoutInflater.from(this.c);
    }


    @Override
    public int getCount() {
        return airportsList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        String filter = characters;
        String itemValue = airportsList.get(position);

        Log.i("airportsList",airportsList+"");

        if(filter.length()>0){
            Log.i("filter",filter+"");
            int startPos = itemValue.toLowerCase(Locale.US).indexOf(filter.toLowerCase(Locale.US));
            int endPos = startPos + filter.length();

            if (startPos != -1) // This should always be true, just a sanity check
            {
                Spannable spannable = new SpannableString(itemValue);
                ColorStateList blueColor = new ColorStateList(new int[][] { new int[] {}}, new int[] { Color.BLUE });
                TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null);

                spannable.setSpan(highlightSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mViewHolder.tvTitle.setText(spannable);
            }
            else
                mViewHolder.tvTitle.setText(itemValue);
        }else{

            mViewHolder.tvTitle.setText(airportsList.get(position));
        }
        return convertView;
    }


    public void filter(String charText) {
        characters = (String) charText;
        charText = charText.toLowerCase(Locale.getDefault());
        airportsList.clear();
        if (charText.length() == 0) {
            airportsList.addAll(tempAirportsList);
        } else {
            for (String wp : tempAirportsList) {
                if (wp.toLowerCase(Locale.getDefault()).contains(charText)) {
                    airportsList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    private class MyViewHolder {
        TextView tvTitle;

        public MyViewHolder(View item) {
            tvTitle = (TextView) item.findViewById(R.id.nameLabel);

        }
    }

}

