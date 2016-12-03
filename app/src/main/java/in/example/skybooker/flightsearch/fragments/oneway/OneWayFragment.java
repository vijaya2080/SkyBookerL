package in.example.skybooker.flightsearch.fragments.oneway;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import in.example.skybooker.CoachAdapter;
import in.example.skybooker.R;
import in.example.skybooker.RecyclerAdapter;
import in.example.skybooker.Serializer;
import in.example.skybooker.flightsearch.DBHelper;
import in.example.skybooker.flightsearch.FromListOfFlightsActivity;
import in.example.skybooker.flightsearch.SelectDateActivity;
import in.example.skybooker.flightsearch.ToListOfFlightActivity;
import in.example.skybooker.flightsearch.TravellersObject;
import in.example.skybooker.flightsearch.ViewPagerAdapter;
import in.example.skybooker.flightsearch.ViewPagerEnum;
import in.example.skybooker.flightsearch.fragments.PagerContainer;

/**
 * Created by siris on 9/26/2016.
 */
public class OneWayFragment extends Fragment implements CoachAdapter.OnSecurityQnClickListener{


    public static TextView tvFrom,tvFromAirport,tvTo,tvToAirport,tvDepart,tvChooseDate,tvDay,tvNoTravller,tvTravellerCount,tvCoach,tvCoachType;
    RelativeLayout r1,r2,r3,travallerSpinner,coachSpinner,viewpagerrl;
    LinearLayout spLayout;
    RecyclerView rvTraveller,rvClass;
    RecyclerAdapter mAdapter;
    CoachAdapter adpt;
    ArrayList<String> travellerTypeArray,coachArray;
    // ArrayList<Integer>travellerCount;
    ArrayList<ViewPagerEnum> viewpagerArray;
    Button done,searchFlights;
    public static int passCount=0;
    SharedPreferences.Editor editor;
    SharedPreferences sp;
    public HashMap<String, Integer> passengers;
    TravellersObject traveller;
    DBHelper db;
    static byte[] empRegByte;
    Serializer serial;
    ContentValues values;
    PagerContainer mContainer;
    public static AlertDialog ad;
    ImageView viewpagerimage;
    private ViewPager viewPager;
    String fromcity,tocity,dept,returndate;
    SpannableString styledString;
    ImageView img_toCity,img_class;
    //CirclePageIndicator mIndicator;

    public OneWayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.frag_oneway_before_selection, null);
      //  viewPager=(ViewPager)root.findViewById(R.id.viewpager_oneway);
        Log.i("OneWayFragment","onCreateView");
        traveller=new TravellersObject();
        db=new DBHelper(getActivity());
        serial=new Serializer();
        values=new ContentValues();


        viewpagerArray = new ArrayList<ViewPagerEnum>();

        for (int i = 0; i < 15; i++) {
            ViewPagerEnum obj = new ViewPagerEnum("HYD" + i, "SEC" + i, "Date" + i);
            viewpagerArray.add(obj);
        }

        mContainer = (PagerContainer) root.findViewById(R.id.Ppager_container);


        viewpagerrl = (RelativeLayout) root.findViewById(R.id.onewaysecondrl);
        // viewPager = (ViewPager) root.findViewById(R.id.viewpageronw);
        // viewPager.setAdapter(new ViewPagerAdapter(getActivity(),viewpagerArray));
        viewpagerimage = (ImageView) root.findViewById(R.id.viewpager_toAndFromIcon);


        final ViewPager pager = mContainer.getViewPager();
        pager.setAdapter(new ViewPagerAdapter(getActivity(),viewpagerArray));
        //pager.setAdapter(adapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        //pager.setOffscreenPageLimit(adapter.getCount());
        //A little space between pages
        pager.setClipToPadding(false);
        pager.setPageMargin(25);
        // TODO Convert 'px' to 'dp'
        // pager.setPageMarginDrawable(R.color.white);
        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);
        pager.setPadding(48, 8, 48, 8);
        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                int pageWidth = pager.getMeasuredWidth() -
                        pager.getPaddingLeft() - pager.getPaddingRight();
                int pageHeight = pager.getHeight();
                int paddingLeft = pager.getPaddingLeft();
                float transformPos = (float) (page.getLeft() -
                        (pager.getScrollX() + paddingLeft)) / pageWidth;
                int max = pageHeight / 10;

                if (transformPos < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    // page.setAlpha(0.5f);// to make left transparent
                    page.setScaleY(0.85f);
                } else if (transformPos <= 1) { // [-1,1]
                    page.setScaleY(1f);
                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    //  page.setAlpha(0.5f);// to make right transparent
                    page.setScaleY(0.85f);
                }


            }
        });







        sp = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sp.edit();






        if(sp.getBoolean("insertOneway",false)){

        }else{
            Log.i("eles ","else");
            traveller.setFrom_City("Enter a city or airport");
            traveller.setTo_City("Enter a city or airport");
            traveller.setDeparture_Date("Choose Date");
            traveller.setClass_S("Coach");
            passengers=new HashMap<String, Integer>();
            passengers.put("0",1);
            passengers.put("1",0);
            passengers.put("2",0);
            passengers.put("3",0);
            passengers.put("4",0);
            traveller.setPassengers(passengers);
            Log.i(""+passengers.get("0"),passengers.get("2")+"");


            empRegByte = serial.serializeObject(traveller);
            values.put(DBHelper.ADDTRAVELLEROBJ, empRegByte);
            values.put(DBHelper.ADDTRAVELLERKEY, "oneway");
            db.insert(DBHelper.TABLE_AddTraveller,values);

            editor.putBoolean("insertOneway",true);
            editor.putInt("typeOneway",0);
            editor.commit();
            Log.i("inserrt ","0inscs");
        }



        coachArray=new ArrayList<>();
        travellerTypeArray=new ArrayList<>();
        //travellerCount=new ArrayList<>();

        travellerTypeArray.add("Adult");
        travellerTypeArray.add("Senior(64+)");
        travellerTypeArray.add("Child(2-11)");
        travellerTypeArray.add("Infant on Seat");
        travellerTypeArray.add("Infant on Lap");

       /* travellerCount.add(1);
        travellerCount.add(0);
        travellerCount.add(0);
        travellerCount.add(0);
        travellerCount.add(0);*/

        coachArray.add("Coach");
        coachArray.add("Premium Economy");
        coachArray.add("Business");
        coachArray.add("First Class");
        Log.i("tryabove","tryabove");
        try {
            traveller = db.getObjDetails(DBHelper.TABLE_AddTraveller,"oneway");
            Log.i("travellerobject",traveller+"");
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        r1=(RelativeLayout) root.findViewById(R.id.fromBeforeSelectionOneWay);
        r2=(RelativeLayout) root.findViewById(R.id.toBeforeSelectionOneWay);
        r3=(RelativeLayout) root.findViewById(R.id.dateBeforeSelectionOneWay);
        spLayout=(LinearLayout) root.findViewById(R.id.spinnerLayout);
        travallerSpinner=(RelativeLayout)spLayout.findViewById(R.id.travellerSp);
        coachSpinner=(RelativeLayout)spLayout.findViewById(R.id.coachSp);

        if(traveller!=null){

            tvFrom=(TextView)r1.findViewById(R.id.tvToAndFromTextBefore);
            tvFrom.setText("From");

            tvFromAirport=(TextView)r1.findViewById(R.id.tvToAndFromCityBefore);
            tvFromAirport.setText(traveller.getFrom_City());

            tvTo=(TextView)r2.findViewById(R.id.tvToAndFromTextBefore);
            tvTo.setText("To");

            tvToAirport=(TextView)r2.findViewById(R.id.tvToAndFromCityBefore);
            tvToAirport.setText(traveller.getTo_City());
            img_toCity=(ImageView)r2.findViewById(R.id.toAndFromIcon);
            img_toCity.setImageResource(R.mipmap.to);

            tvDepart=(TextView)r3.findViewById(R.id.calanderTextBefore);
            tvDepart.setText("Depart");

            tvDay=(TextView)r3.findViewById(R.id.tvCalanderDayBefore);
            tvChooseDate=(TextView)r3.findViewById(R.id.tv_date);

            if(traveller.getDeparture_Date().contains("Choose Date")){
                tvDay.setText(traveller.getDeparture_Date());
            }else{
                StringTokenizer toknzr = new StringTokenizer(traveller.getDeparture_Date(),"$");
                tvDay.setText(toknzr.nextToken());
                tvChooseDate.setText(toknzr.nextToken());
            }


            // tvDay.setText(date.nextToken());
            //tvChooseDate.setText(traveller.getDeparture_Date());

            tvNoTravller=(TextView)travallerSpinner.findViewById(R.id.spTravellerType) ;
            tvNoTravller.setText("Traveller(s)");


            HashMap<String,Integer> map = traveller.getPassengers();
            passCount=0;
            for(int i=0;i<map.size();i++)
            {
                Log.i(passCount+"",map.get(i+"")+"");
                passCount = passCount+ map.get(i+"");
            }
            tvTravellerCount=(TextView)travallerSpinner.findViewById(R.id.spTravellerCount);
            tvTravellerCount.setText(passCount+"");


            tvCoach=(TextView)coachSpinner.findViewById(R.id.spTravellerType);
            tvCoach.setText("Class");

            tvCoachType=(TextView)coachSpinner.findViewById(R.id.spTravellerCount);
            tvCoachType.setText( coachArray.get(sp.getInt("typeOneway",0)));
            img_class=(ImageView)coachSpinner.findViewById(R.id.spIcon);
            img_class.setImageResource(R.mipmap.coach);
        }


        //  Log.i("aaaaa",traveller.getTo_City());

        searchFlights = (Button)root.findViewById(R.id.btoneWaySearch) ;
        searchFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateDB();

                validationoneway();
            }
        });




        sp = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sp.edit();



        //r3=(RelativeLayout) root.findViewById(R.id.dateBeforeSelectionOneWay);
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"click",Toast.LENGTH_SHORT).show();
                //new DatePickerDialog(getActivity(),mDateSetListener,mYear, mMonth, mDay);

                Intent intent = new Intent(getActivity(), SelectDateActivity.class);
                intent.putExtra("fragment","oneway");
                startActivity(intent);
            }
        });

        travallerSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater in=LayoutInflater.from(getActivity());
                View v=in.inflate(R.layout.drop_down_traveller_recycler,null);
                AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                alert.setView(v);

                final AlertDialog al=alert.create();
                al.show();


                rvTraveller=(RecyclerView)v.findViewById(R.id.travellerRecycler);
                mAdapter = new RecyclerAdapter(getActivity(), travellerTypeArray,traveller.getPassengers(),"oneway");

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                rvTraveller.setLayoutManager(layoutManager);
                rvTraveller.setItemAnimator(new DefaultItemAnimator());
                rvTraveller.setAdapter(mAdapter);

                done=(Button)v.findViewById(R.id.btDone);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // tvTravellerCount.setText(Integer.toString(RecyclerAdapter.MyViewHolder.count));
                        //Toast.makeText(getActivity(), passCount+"", Toast.LENGTH_SHORT).show();

                        tvTravellerCount.setText(passCount+"");
                        updateDB();
                        al.dismiss();
                    }
                });



            }
        });
        coachSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater in=LayoutInflater.from(getActivity());
                View v=in.inflate(R.layout.drop_down_coach_recycler,null);
                AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                alert.setView(v);
                ad=alert.create();
                ad.show();


                rvClass=(RecyclerView)v.findViewById(R.id.rvClass);
                adpt = new CoachAdapter(getActivity(), coachArray,"oneway");

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                rvClass.setLayoutManager(layoutManager);
                rvClass.setItemAnimator(new DefaultItemAnimator());
                adpt.setOnSecurityQnClickListener(OneWayFragment.this);
                rvClass.setAdapter(adpt);

            }
        });

        r1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FromListOfFlightsActivity.class);
                intent.putExtra("fragment","oneway");
                startActivity(intent);

            }
        });

        r2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ToListOfFlightActivity.class);
                intent.putExtra("fragment","oneway");
                startActivity(intent);

            }
        });
      /*  viewpagerArray=new ArrayList<ViewPagerEnum>();

        for(int i=0;i<5;i++) {
            ViewPagerEnum obj = new ViewPagerEnum("HYD"+i,"SEC"+i,"Date"+i);
            viewpagerArray.add(obj);
        }



        viewpagerrl=(RelativeLayout) root.findViewById(R.id.onewaysecondrl) ;
        viewPager = (ViewPager) root.findViewById(R.id.viewpager_oneway);
        viewPager.setAdapter(new ViewPagerAdapter(getActivity(),viewpagerArray));
        viewpagerimage=(ImageView)root.findViewById(R.id.viewpager_toAndFromIcon);
*/

        /*mIndicator = (CirclePageIndicator) root.findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);
        ((CirclePageIndicator) mIndicator).setSnap(true);

        mIndicator
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        Toast.makeText(OneWayFragment.this.getActivity(),
                                "Changed to page " + position,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPageScrolled(int position,
                                               float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });*/
        return root;

    }
    private void validationoneway() {
        fromcity=tvFromAirport.getText().toString().trim();
        tocity=tvToAirport.getText().toString().trim();
        dept=tvChooseDate.getText().toString().trim();
        if( fromcity.equals("Enter a city or airport"))
        {
            new AlertDialog.Builder(getContext())

                    .setMessage("Select the airport you are departing from")
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                }
                            }).create().show();
        }
        else if(tocity.equals("Enter a city or airport"))
        {
            new AlertDialog.Builder(getContext())

                    .setMessage("Select the airport you are travalling to")
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                }
                            }).create().show();
        }
        else if(dept.equals("Depart"))
        {
            new AlertDialog.Builder(getContext())

                    .setMessage("Select the departure date")
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                }
                            }).create().show();
        }
        else {

            startActivity(new Intent(getActivity(), OneWayFlights.class));
        }

    }

    public void updateDB(){

        TravellersObject trvl = null;
        try {
            trvl = db.getObjDetails(DBHelper.TABLE_AddTraveller,"oneway");
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        ContentValues val = new ContentValues();
        trvl.setFrom_City(tvFromAirport.getText().toString().trim());
        trvl.setTo_City(tvToAirport.getText().toString().trim());
        trvl.setDeparture_Date(tvChooseDate.getText().toString().trim());
        trvl.setPassengers(mAdapter.travellerCountArray);
        trvl.setClass_S(tvCoachType.getText().toString().trim());
        empRegByte = serial.serializeObject(trvl);
        val.put(DBHelper.ADDTRAVELLEROBJ, empRegByte);
        db.update(DBHelper.TABLE_AddTraveller,val,"oneway", DBHelper.ADDTRAVELLERKEY);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            traveller = db.getObjDetails(DBHelper.TABLE_AddTraveller,"oneway");
            ContentValues val = new ContentValues();
            traveller.setFrom_City(tvFromAirport.getText().toString().trim());
            traveller.setTo_City(tvToAirport.getText().toString().trim());
            traveller.setDeparture_Date(tvDay.getText().toString().trim()+"$"+tvChooseDate.getText().toString().trim());
            traveller.setPassengers(traveller.getPassengers());
            traveller.setClass_S(tvCoachType.getText().toString().trim());
            empRegByte = serial.serializeObject(traveller);
            val.put(DBHelper.ADDTRAVELLEROBJ, empRegByte);
            db.update(DBHelper.TABLE_AddTraveller,val,"oneway", DBHelper.ADDTRAVELLERKEY);
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        if(!(traveller.getFrom_City().contains("Enter"))){
            SpannableString fromcity = new SpannableString(traveller.getFrom_City());
            fromcity.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // make the text twice as large
            fromcity.setSpan(new RelativeSizeSpan(1.4f), 0, 3, 0);
            // make text bold
            fromcity.setSpan(new StyleSpan(Typeface.BOLD), 0, 3, 0);
            tvFromAirport.setBackgroundColor(Color.WHITE);
            tvFromAirport.setText(fromcity);


            SpannableString tocity = new SpannableString(traveller.getTo_City());
            tocity.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // make the text twice as large
            tocity.setSpan(new RelativeSizeSpan(1.4f), 0, 3, 0);
            // make text bold
            tocity.setSpan(new StyleSpan(Typeface.BOLD), 0, 3, 0);
            tvToAirport.setBackgroundColor(Color.WHITE);
            tvToAirport.setText(tocity);

           /* SpannableString date = new SpannableString(traveller.getDeparture_Date());
            date.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // make the text twice as large
            date.setSpan(new RelativeSizeSpan(1.4f), 0, 2, 0);
            // make text bold
            date.setSpan(new StyleSpan(Typeface.BOLD), 0, 2, 0);
            tvChooseDate.setBackgroundColor(Color.WHITE);*/
            if(traveller.getDeparture_Date().contains("Choose Date")){
                tvDay.setText(traveller.getDeparture_Date());
            }else{
                StringTokenizer toknzr = new StringTokenizer(traveller.getDeparture_Date(),"$");
                tvDay.setText(toknzr.nextToken());
                tvChooseDate.setText(toknzr.nextToken());
            }
//            tvDay.setText(date.nextToken());



        }
    }



    @Override
    public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException {

        tvCoachType.setText(coachArray.get(position));
        killScreen();

    }

    public void onBackPressed(){killScreen();}

    public void  killScreen(){
        //getActivity().finish();
    }
}
