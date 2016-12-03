package in.example.skybooker.flightsearch.fragments.roundtrip;

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
public class RoundTripFragment extends Fragment implements CoachAdapter.OnSecurityQnClickListener{
    public static TextView tvFrom,tvFromAirport,tvTo,tvToAirport,tvDepart,tvChooseDate,tvDay,tvReturn,tvReturnDate,tvReturnDay,tvNoTravller,tvTravellerCount,tvCoach,tvCoachType;
    RelativeLayout r1,r2,r3,r4,travallerSpinner,coachSpinner,viewpagerrl;
    LinearLayout spLayout;
    RecyclerView rvTraveller,rvClass;
    RecyclerAdapter mAdapter;
    CoachAdapter adpt;
    ArrayList<String> travellerTypeArray,coachArray;
    // ArrayList<Integer>travellerCount;
    Button done,searchFlights;
    public static int passCount=0;
    SharedPreferences.Editor editor;
    SharedPreferences sp;
    public HashMap<String, Integer> passengers;
    TravellersObject traveller;
    DBHelper db;
    static byte[] empRegByte;
    Serializer serial;
    PagerContainer mContainer;
    ContentValues values;
    public static AlertDialog ad;
    private ViewPager viewPager;
    ImageView viewpagerimage;
    ArrayList<ViewPagerEnum> viewpagerArray;
    String fromcity,tocity,dept,returndate;
    ImageView img_toCity,img_class;
    public RoundTripFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.frag_roundtrip, null);

        traveller=new TravellersObject();
        db=new DBHelper(getActivity());
        serial=new Serializer();
        values=new ContentValues();

        sp = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sp.edit();



        viewpagerArray=new ArrayList<ViewPagerEnum>();
        for (int i = 0; i < 15; i++) {
            ViewPagerEnum obj = new ViewPagerEnum("HYD" + i, "SEC" + i, "Date" + i);
            viewpagerArray.add(obj);

            /*if(viewpagerArray.size()==5)
            {

                fm.beginTransaction().replace(R.id.fragment,new RoundTripFragment()).commit();
            }*/
        }


        mContainer = (PagerContainer) root.findViewById(R.id.Rpager_container);


        viewpagerrl = (RelativeLayout) root.findViewById(R.id.roundtripsecondrl);
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












        if(sp.getBoolean("insertRountrip",false)){

        }else{
            traveller.setFrom_City("Enter a city or airport");
            traveller.setTo_City("Enter a city or airport");
            traveller.setDeparture_Date("Choose Date");
            traveller.setReturn_Date("Choose Date");
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
            values.put(DBHelper.ADDTRAVELLERKEY, "roundtrip");
            db.insert(DBHelper.TABLE_AddTraveller,values);

            editor.putBoolean("insertRountrip",true);
            editor.putInt("typeRound",0);
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

        try {
            traveller = db.getObjDetails(DBHelper.TABLE_AddTraveller,"roundtrip");
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        r1 = (RelativeLayout) root.findViewById(R.id.fromBeforeSelectionRoundTrip);
        r2 = (RelativeLayout) root.findViewById(R.id.toBeforeSelectionRoundTrip);
        r3 = (RelativeLayout) root.findViewById(R.id.dateBeforeSelectionRoundTrip);
        r4 = (RelativeLayout) root.findViewById(R.id.dateBeforeSelectionRoundTrip1);
        spLayout=(LinearLayout) root.findViewById(R.id.spinnerLayoutRoundTrip);

        travallerSpinner=(RelativeLayout)spLayout.findViewById(R.id.travellerSp_RoundTrip);
        coachSpinner=(RelativeLayout)spLayout.findViewById(R.id.coachSp_RoundTrip);

        if(traveller!=null) {

            tvFrom = (TextView) r1.findViewById(R.id.tvToAndFromTextBefore);
            tvFrom.setText("From");

            tvFromAirport = (TextView) r1.findViewById(R.id.tvToAndFromCityBefore);
            //Log.i("bbb",traveller.getFrom_City());
            tvFromAirport.setText(traveller.getFrom_City());
            //

            tvTo = (TextView) r2.findViewById(R.id.tvToAndFromTextBefore);
            tvTo.setText("To");

            tvToAirport = (TextView) r2.findViewById(R.id.tvToAndFromCityBefore);
            tvToAirport.setText(traveller.getTo_City());
            img_toCity=(ImageView)r2.findViewById(R.id.toAndFromIcon);
            img_toCity.setImageResource(R.mipmap.to);

            tvDepart = (TextView) r3.findViewById(R.id.calanderTextBefore);
            tvDepart.setText("Depart");

            tvChooseDate = (TextView) r3.findViewById(R.id.tv_date);
            //StringTokenizer date=new StringTokenizer( traveller.getDeparture_Date(),"$") ;
            //tvChooseDate.setText(date.nextToken());
            //  Log.i("aaaaa",traveller.getTo_City());
            tvDay=(TextView)r3.findViewById(R.id.tvCalanderDayBefore);
            //tvDay.setText("day");

            if(traveller.getDeparture_Date().contains("Choose Date")){
                tvDay.setText(traveller.getDeparture_Date());
            }else{
                StringTokenizer toknzr = new StringTokenizer(traveller.getDeparture_Date(),"$");
                tvDay.setText(toknzr.nextToken());
                tvChooseDate.setText(toknzr.nextToken());
            }

            tvReturn = (TextView) r4.findViewById(R.id.calanderTextBefore);
            tvReturn.setText("Return");

            tvReturnDay=(TextView)r4.findViewById(R.id.tvCalanderDayBefore) ;
            tvReturnDate = (TextView) r4.findViewById(R.id.tv_date);
            if(traveller.getReturn_Date().contains("Choose Date")){
                tvReturnDay.setText(traveller.getReturn_Date());
            }else{
                StringTokenizer toknzr = new StringTokenizer(traveller.getReturn_Date(),"$");
                tvReturnDay.setText(toknzr.nextToken());
                tvReturnDate.setText(toknzr.nextToken());
            }

            //tvReturnDate.setText(traveller.getReturn_Date());
        }
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
        //tvCoachType.setText(traveller.getClass_S());
        tvCoachType.setText(coachArray.get(sp.getInt("typeRound",0)));
        img_class=(ImageView)coachSpinner.findViewById(R.id.spIcon);
        img_class.setImageResource(R.mipmap.coach);

        searchFlights = (Button)root.findViewById(R.id.btRoundTripSearch) ;
        searchFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateDB();
                roundtripvalidation();


            }
        });

        sp = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sp.edit();

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SelectDateActivity.class);
                intent.putExtra("fragment","roundtrip");
                intent.putExtra("date","departure");
                startActivity(intent);
            }
        });

        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SelectDateActivity.class);
                intent.putExtra("fragment","roundtrip");
                intent.putExtra("date","return");
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
                mAdapter = new RecyclerAdapter(getActivity(), travellerTypeArray,traveller.getPassengers(),"roundtrip");

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
                adpt = new CoachAdapter(getActivity(), coachArray,"roundtrip");

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                rvClass.setLayoutManager(layoutManager);
                rvClass.setItemAnimator(new DefaultItemAnimator());
                adpt.setOnSecurityQnClickListener(RoundTripFragment.this);
                rvClass.setAdapter(adpt);

            }
        });

        r1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FromListOfFlightsActivity.class);
                intent.putExtra("fragment","roundtrip");
                startActivity(intent);

            }
        });

        r2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ToListOfFlightActivity.class);
                intent.putExtra("fragment","roundtrip");
                startActivity(intent);

            }
        });
       /* viewpagerArray=new ArrayList<ViewPagerEnum>();

        for(int i=0;i<5;i++) {
            ViewPagerEnum obj = new ViewPagerEnum("HYD"+i,"SEC"+i,"Date"+i);
            viewpagerArray.add(obj);
        }



        viewpagerrl=(RelativeLayout) root.findViewById(R.id.roundtripmainlayout) ;
        viewPager = (ViewPager) root.findViewById(R.id.viewpager_roundtrip);
        viewPager.setAdapter(new ViewPagerAdapter(getActivity(),viewpagerArray));
*/





        return root;
    }

    private void roundtripvalidation() {

        fromcity=tvFromAirport.getText().toString().trim();
        tocity=tvToAirport.getText().toString().trim();
        dept=tvChooseDate.getText().toString().trim();
        returndate=tvReturnDate.getText().toString().trim();
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

                    .setMessage("Select your departure date")
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                }
                            }).create().show();
        }
        else if(returndate.equals("Return"))
        {
            new AlertDialog.Builder(getContext())

                    .setMessage("Select your Return Date")
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int which) {
                                }
                            }).create().show();
        }
        else {

            startActivity(new Intent(getActivity(), RoundTripFlights.class));
        }

    }

    public void updateDB(){
        TravellersObject trvl = null;
        try {
            trvl = db.getObjDetails(DBHelper.TABLE_AddTraveller,"roundtrip");
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        ContentValues val = new ContentValues();
        trvl.setFrom_City(tvFromAirport.getText().toString().trim());
        trvl.setTo_City(tvToAirport.getText().toString().trim());
        trvl.setDeparture_Date(tvChooseDate.getText().toString().trim());
        trvl.setReturn_Date(tvReturnDate.getText().toString().trim());
        trvl.setPassengers(mAdapter.travellerCountArray);
        trvl.setClass_S(tvCoachType.getText().toString().trim());
        empRegByte = serial.serializeObject(trvl);
        val.put(DBHelper.ADDTRAVELLEROBJ, empRegByte);
        db.update(DBHelper.TABLE_AddTraveller,val,"roundtrip", DBHelper.ADDTRAVELLERKEY);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            traveller = db.getObjDetails(DBHelper.TABLE_AddTraveller,"roundtrip");
            ContentValues val = new ContentValues();
            traveller.setFrom_City(tvFromAirport.getText().toString().trim());
            traveller.setTo_City(tvToAirport.getText().toString().trim());
            traveller.setDeparture_Date(tvDay.getText().toString().trim()+"$"+tvChooseDate.getText().toString().trim());
            traveller.setReturn_Date(tvReturnDay.getText().toString().trim()+"$"+tvReturnDate.getText().toString().trim());
            traveller.setPassengers(traveller.getPassengers());
            traveller.setClass_S(tvCoachType.getText().toString().trim());
            empRegByte = serial.serializeObject(traveller);
            val.put(DBHelper.ADDTRAVELLEROBJ, empRegByte);
            db.update(DBHelper.TABLE_AddTraveller,val,"roundtrip", DBHelper.ADDTRAVELLERKEY);
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        if(!(traveller.getFrom_City().contains("Enter"))){
            SpannableString styledString = new SpannableString(traveller.getFrom_City());
            styledString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // make the text twice as large
            styledString.setSpan(new RelativeSizeSpan(1.4f), 0, 3, 0);
            // make text bold
            styledString.setSpan(new StyleSpan(Typeface.BOLD), 0, 3, 0);
            tvFromAirport.setBackgroundColor(Color.WHITE);
            tvFromAirport.setText(styledString);

            SpannableString styledString1 = new SpannableString(traveller.getTo_City());
            styledString1.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // make the text twice as large
            styledString1.setSpan(new RelativeSizeSpan(1.4f), 0, 3, 0);
            // make text bold
            styledString1.setSpan(new StyleSpan(Typeface.BOLD), 0, 3, 0);
            tvToAirport.setBackgroundColor(Color.WHITE);
            tvToAirport.setText(styledString1);


            if(traveller.getDeparture_Date().contains("Choose Date")){
                tvDay.setText(traveller.getDeparture_Date());
            }else{
                StringTokenizer toknzr = new StringTokenizer(traveller.getDeparture_Date(),"$");
                tvDay.setText(toknzr.nextToken());
                tvChooseDate.setText(toknzr.nextToken());
            }

            if(traveller.getReturn_Date().contains("Choose Date")){
                tvReturnDay.setText(traveller.getReturn_Date());
            }else{
                StringTokenizer toknzr = new StringTokenizer(traveller.getReturn_Date(),"$");
                tvReturnDay.setText(toknzr.nextToken());
                tvReturnDate.setText(toknzr.nextToken());
            }

            /*SpannableString date = new SpannableString(traveller.getDeparture_Date());
            date.setSpan(new ForegroundColorSpan(Color.BLACK), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            // make the text twice as large
            date.setSpan(new RelativeSizeSpan(1.4f), 0, 2, 0);
            // make text bold
            date.setSpan(new StyleSpan(Typeface.BOLD), 0, 2, 0);
            tvChooseDate.setBackgroundColor(Color.WHITE);
            tvChooseDate.setText(date);*/
            // StringTokenizer date=new StringTokenizer( traveller.getDeparture_Date(),"$") ;
            // tvChooseDate.setText(date.nextToken());
//            tvDay.setText(date.nextToken());
            // StringTokenizer date1=new StringTokenizer( traveller.getReturn_Date(),"$") ;
            //tvReturnDate.setText(date1.nextToken());
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

