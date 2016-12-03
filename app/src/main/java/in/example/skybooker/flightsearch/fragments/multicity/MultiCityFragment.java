package in.example.skybooker.flightsearch.fragments.multicity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import in.example.skybooker.CoachAdapter;
import in.example.skybooker.R;
import in.example.skybooker.RecyclerAdapter;
import in.example.skybooker.Serializer;
import in.example.skybooker.SingleTon;
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
public class MultiCityFragment extends Fragment implements View.OnClickListener, CoachAdapter.OnSecurityQnClickListener{

    /* RelativeLayout flight1Lay,flight2Lay,addFlight,deleteFLight,customFlight1,customFlight1Date,cFt2FromCity,cF2ToCityLay,cF2DateLay,customFlightFromLay
            ,customFlightToLay,customFlightDateLay,CF1ToLay,CF1DateLay,travelersLay,classLay,viewpagerrl;
    ImageView addFlightImg,deletFlightImg;

    public static  TextView tv_Flightno,tv_CFlightFromCity,tv_CFlightToCity,tv_CFlightDay,tv_CFlightDate,tv_deleteFlight,tv_travelers,tv_traveler_count,tv_class,tv_classType,tv_optionalFrom,tv_OptionalTo;*/
    RelativeLayout addFlight,deleteFLight,travelersLay,classLay,multi_viewpager_rel,customFlightFromLay,customFlightToLay,customFlightDateLay,viewpagerrl;
    LinearLayout linearLayoutForm;//,addFlightLinearLay,spinnermultiLayout;
    ImageView deletFlightImg,spIcon,down,img_class,img_OptionalCity;
    public static TextView tv_deleteFlight,tv_travelers,tv_traveler_count,tv_class,tv_classType,tv_Flightno,tv_optionalFrom,tv_OptionalTo,tv_CFlightFromCity,tv_CFlightDate,tv_CFlightToCity,tv_CFlightDay;
    //ImageView img_CF1ToCity,img_CF2ToCity,img_OptionalCity,img_class;
    View viewList;
    Button done,searchFlights;
    SharedPreferences.Editor editor;
    SharedPreferences sp;
    public static int passCount=0;
    public transient  View newView;
    public transient View readView;
    DBHelper db;
    static byte[] empRegByte;
    Serializer serial;
    ContentValues values;

    public HashMap<String, Integer> passengers;
    int layNo=1;
    public static AlertDialog ad;
    RecyclerView rvTraveller,rvClass;
    RecyclerAdapter mAdapter;
    CoachAdapter adpt;
    ArrayList<String> travellerTypeArray,coachArray;
    String fight1_tocity;
    private ViewPager viewPager;
    PagerContainer mContainer;
    ImageView viewpagerimage;
    TravellersObject traveller;
    ArrayList<ViewPagerEnum> viewpagerArray;

    ArrayList<String> toArray,fromArray,dateArray;
    int flightCount =0;
    Gson gson ;

    public MultiCityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup r = (ViewGroup) inflater.inflate(R.layout.frag_multicity, null);

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


        mContainer = (PagerContainer) r.findViewById(R.id.Mpager_container);


        viewpagerrl = (RelativeLayout) r.findViewById(R.id.multisecondrl);
        // viewPager = (ViewPager) root.findViewById(R.id.viewpageronw);
        // viewPager.setAdapter(new ViewPagerAdapter(getActivity(),viewpagerArray));
        viewpagerimage = (ImageView) r.findViewById(R.id.viewpager_toAndFromIcon);


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







        toArray=new ArrayList<>();
        fromArray=new ArrayList<>();
        dateArray=new ArrayList<>();
        //viewList = new ArrayList<>();
        gson = new Gson();
/*

        toArray.add("Enter a city or airport");
        toArray.add("Enter a city or airport");
        fromArray.add("Enter a city or airport");
        fromArray.add("Enter a city or airport");
        dateArray.add("Depart");
        dateArray.add("Depart");
*/
        Log.i("status",sp.getBoolean("insertMultiCity",false)+"");
        if(sp.getBoolean("insertMultiCity",false)){

        }else{
            toArray.add("Enter a city or airport");toArray.add("Enter a city or airport");
            fromArray.add("Enter a city or airport");fromArray.add("Enter a city or airport");
            dateArray.add("Choose Date");dateArray.add("Choose Date");

            //traveller.setFrom_City("Enter a city or airport");
            //traveller.setTo_City("Enter a city or airport");
            // traveller.setDeparture_Date("Choose Date");
            // traveller.setViewList(viewList);
            traveller.setCount(2);
            traveller.setFromArray(fromArray);
            traveller.setToArray(toArray);
            traveller.setDateArray(dateArray);
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
            values.put(DBHelper.ADDTRAVELLERKEY, "multicity");
            db.insert(DBHelper.TABLE_AddTraveller,values);

            editor.putBoolean("insertMultiCity",true);
            editor.putInt("typeMulti",0);
            editor.commit();
            Log.i("inserrt ","0inscs");
        }

        //SingleTon.mPref.edit().putInt("MultiLayout",1).commit();

        travellerTypeArray=new ArrayList<>();
        coachArray=new ArrayList<>();

        travellerTypeArray.add("Adult");
        travellerTypeArray.add("Senior(64+)");
        travellerTypeArray.add("Child(2-11)");
        travellerTypeArray.add("Infant on Seat");
        travellerTypeArray.add("Infant on Lap");

        coachArray.add("Coach");
        coachArray.add("Premium Economy");
        coachArray.add("Business");
        coachArray.add("First Class");

        try {
            traveller = db.getObjDetails(DBHelper.TABLE_AddTraveller,"multicity");
            Log.i("travellerobject",traveller+"");
        } catch (DecoderException e) {
            e.printStackTrace();
        }


        // flight1Lay=(RelativeLayout)r.findViewById(R.id.flightOne);
        addFlight=(RelativeLayout)r.findViewById(R.id.addFlight);
        deleteFLight=(RelativeLayout)r.findViewById(R.id.deleteFlight);
        travelersLay=(RelativeLayout)r.findViewById(R.id.multitravellerSp);
        classLay=(RelativeLayout)r.findViewById(R.id.multicoachSp);
        //multi_viewpager_rel=(RelativeLayout)r.findViewById(R.id.multi_viewpager_rel);


        linearLayoutForm = (LinearLayout) r.findViewById(R.id.dynamic_flight);

        //customFlight1=(RelativeLayout)flight1Lay.findViewById(R.id.fromflight1_multicity);
        //CF1ToLay=(RelativeLayout)r.findViewById(R.id.toflight1_multicity);
        //customFlight1Date=(RelativeLayout)r.findViewById(R.id.dateflight1_multicity);
        // flight2Lay=(RelativeLayout)r.findViewById(R.id.flightTwo);
        // cFt2FromCity=(RelativeLayout)r.findViewById(R.id.fromflight2_multicity);
        // cF2ToCityLay=(RelativeLayout)r.findViewById(R.id.toflight2_multicity);
        // cF2DateLay=(RelativeLayout)r.findViewById(R.id.dateflight2_multicity);

       /* tv_CF1No = (TextView) flight1Lay.findViewById(R.id.tv_flight1);
        tv_CF1From = (TextView) customFlight1.findViewById(R.id.tvToAndFromTextBefore);
        tv_CF1FromCity = (TextView) customFlight1.findViewById(R.id.tvToAndFromCityBefore);
        tv_CF1To = (TextView) CF1ToLay.findViewById(R.id.tvToAndFromTextBefore);
        tv_CF1ToCity = (TextView) CF1ToLay.findViewById(R.id.tvToAndFromCityBefore);
        img_CF1ToCity=(ImageView)CF1ToLay.findViewById(R.id.toAndFromIcon) ;
        tv_CF1Day = (TextView) customFlight1Date.findViewById(R.id.tvCalanderDayBefore);
        tv_CF2From = (TextView) cFt2FromCity.findViewById(R.id.tvToAndFromTextBefore);
        tv_CF2FromCity = (TextView) cFt2FromCity.findViewById(R.id.tvToAndFromCityBefore);
        tv_CF2To = (TextView) cF2ToCityLay.findViewById(R.id.tvToAndFromTextBefore);
        tv_CF2ToCity = (TextView) cF2ToCityLay.findViewById(R.id.tvToAndFromCityBefore);
        img_CF2ToCity=(ImageView)cF2ToCityLay.findViewById(R.id.toAndFromIcon) ;
        tv_CF2Day = (TextView) cF2DateLay.findViewById(R.id.tvCalanderDayBefore);*/

        //addFlightImg = (ImageView) addFlight.findViewById(R.id.img_addFly);
        //spIcon = (ImageView) addFlight.findViewById(R.id.spIcon);
        //down = (ImageView) addFlight.findViewById(R.id.down);

        // tvAddFlight = (TextView) deleteFLight.findViewById(R.id.tvAddFlight);
        //tv_travelers = (TextView) deleteFLight.findViewById(R.id.spTravellerType);
        // tv_traveler_count = (TextView) deleteFLight.findViewById(R.id.spTravellerCount);


        //addFlightLinearLay=(LinearLayout) r.findViewById(R.id.addFlightLinearLay);
        //spinnermultiLayout=(LinearLayout) r.findViewById(R.id.spinnermultiLayout);

        if(traveller!=null) {
            flightCount = traveller.getCount();
            for (int i=0;i<flightCount;i++){
                addFlights(i,traveller);
            }


            //img_CF1ToCity.setImageResource(R.mipmap.to);




            // tv_CF2No = (TextView) r.findViewById(R.id.tv_flight2);


            // img_CF2ToCity.setImageResource(R.mipmap.to);




            deletFlightImg = (ImageView) deleteFLight.findViewById(R.id.img_addFly);
            deletFlightImg.setImageResource(R.mipmap.delfly);
            tv_deleteFlight = (TextView) deleteFLight.findViewById(R.id.tvAddFlight);
            tv_deleteFlight.setText("DeletFlight");


            HashMap<String,Integer> map = traveller.getPassengers();
            passCount=0;
            for(int i=0;i<map.size();i++)
            {
                Log.i(passCount+"",map.get(i+"")+"");
                passCount = passCount+ map.get(i+"");
            }
            tv_travelers = (TextView) travelersLay.findViewById(R.id.spTravellerType);
            tv_travelers.setText("Traveler(s)");
            tv_traveler_count = (TextView) travelersLay.findViewById(R.id.spTravellerCount);
            tv_traveler_count.setText(passCount+"");

            tv_class=(TextView)classLay.findViewById(R.id.spTravellerType);
            tv_class.setText("Coach");
            tv_classType=(TextView)classLay.findViewById(R.id.spTravellerCount);
            tv_classType.setText(coachArray.get(SingleTon.mPref.getInt("typeMulti",0)));
            img_class=(ImageView)classLay.findViewById(R.id.spIcon);
            img_class.setImageResource(R.mipmap.coach);
        }

        addFlight.setOnClickListener(this);
        deleteFLight.setOnClickListener(this);
        searchFlights = (Button)r.findViewById(R.id.btMultiCitySearch) ;
        searchFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MulticityFlights.class));
            }
        });

/*
        customFlight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FromListOfFlightsActivity.class);
                intent.putExtra("fragment","multicity");
                intent.putExtra("fromFlight","flight1");
                startActivity(intent);

            }
        });

        CF1ToLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ToListOfFlightActivity.class);
                intent.putExtra("fragment","multicity");
                intent.putExtra("toFlight","flight1");
                startActivity(intent);
            }
        });

        customFlight1Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SelectDateActivity.class);
                intent.putExtra("fragment","multicity");
                intent.putExtra("multicityDate","flight1");
                startActivity(intent);
            }
        });

        cF2ToCityLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ToListOfFlightActivity.class);
                intent.putExtra("fragment","multicity");
                intent.putExtra("toFlight","flight2");
                startActivity(intent);
            }
        });
        cFt2FromCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FromListOfFlightsActivity.class);
                intent.putExtra("fragment","multicity");
                intent.putExtra("fromFlight","flight2");
                startActivity(intent);
            }
        });
        cF2DateLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SelectDateActivity.class);
                intent.putExtra("fragment","multicity");
                intent.putExtra("multicityDate","flight2");
                startActivity(intent);
            }
        });*/
        travelersLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater in=LayoutInflater.from(getActivity());
                View v=in.inflate(R.layout.drop_down_traveller_recycler,null);
                AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                alert.setView(v);

                final AlertDialog al=alert.create();
                al.show();


                rvTraveller=(RecyclerView)v.findViewById(R.id.travellerRecycler);
                mAdapter = new RecyclerAdapter(getActivity(), travellerTypeArray,traveller.getPassengers(),"multicity");

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
                        tv_traveler_count.setText(passCount+"");
                        //    updateDB();
                        al.dismiss();
                    }
                });


            }
        });

        classLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater in=LayoutInflater.from(getActivity());
                View v=in.inflate(R.layout.drop_down_coach_recycler,null);
                AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                alert.setView(v);
                ad=alert.create();
                ad.show();

                rvClass=(RecyclerView)v.findViewById(R.id.rvClass);
                adpt = new CoachAdapter(getActivity(), coachArray,"multicity");

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                rvClass.setLayoutManager(layoutManager);
                rvClass.setItemAnimator(new DefaultItemAnimator());
                adpt.setOnSecurityQnClickListener(MultiCityFragment.this);
                rvClass.setAdapter(adpt);
            }
        });




      /*  viewpagerArray=new ArrayList<ViewPagerEnum>();

        for(int i=0;i<5;i++) {
            ViewPagerEnum obj = new ViewPagerEnum("HYD"+i,"SEC"+i,"Date"+i);
            viewpagerArray.add(obj);
        }



        multi_viewpager_rel=(RelativeLayout) r.findViewById(R.id.multi_viewpager_rel) ;
        viewPager = (ViewPager) r.findViewById(R.id.viewpager_multicity);
        viewPager.setAdapter(new ViewPagerAdapter(getActivity(),viewpagerArray));*/

        return r;
    }

  /*  public void updateDB(){
        TravellersObject trvl = null;
        try {
            trvl = db.getObjDetails(DBHelper.TABLE_AddTraveller,"multicity");
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        ContentValues val = new ContentValues();
        trvl.setFrom_City(tv_CF1FromCity.getText().toString().trim());
        trvl.setTo_City(tv_CF1ToCity.getText().toString().trim());
        trvl.setDeparture_Date(tv_CF1Day.getText().toString().trim());
        trvl.setPassengers(mAdapter.travellerCountArray);
        trvl.setClass_S(tv_classType.getText().toString().trim());
        empRegByte = serial.serializeObject(trvl);
        val.put(DBHelper.ADDTRAVELLEROBJ, empRegByte);
        db.update(DBHelper.TABLE_AddTraveller,val,"multicity",DBHelper.ADDTRAVELLERKEY);
    }
    public void onResume() {
        try {
            traveller = db.getObjDetails(DBHelper.TABLE_AddTraveller,"multicity");
            ContentValues val = new ContentValues();
            traveller.setFrom_City(tv_CF1FromCity.getText().toString().trim());
            traveller.setTo_City(tv_CF1ToCity.getText().toString().trim());
            traveller.setDeparture_Date(tv_CF1Day.getText().toString().trim());
            traveller.setPassengers(traveller.getPassengers());
            traveller.setClass_S(tv_classType.getText().toString().trim());
            empRegByte = serial.serializeObject(traveller);
            val.put(DBHelper.ADDTRAVELLEROBJ, empRegByte);
            db.update(DBHelper.TABLE_AddTraveller,val,"multicity",DBHelper.ADDTRAVELLERKEY);
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        super.onResume();
    }*/



    @Override
    public void onClick(View view) {

        if(view == addFlight){
            Log.i("if condition","outer");
            Log.i("if condition",+sp.getInt("MultiLayout", 0)+" ");
            // if(SingleTon.mPref.getInt("MultiLayout", 0)<4) {
            int pos = traveller.getCount()+1;
            if(pos<6) {
                Log.i("if condition","entered");
                newView = (View) getActivity().getLayoutInflater().inflate(R.layout.custom_multicity, null);
                newView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                //pos = pos;

                tv_Flightno=(TextView)newView.findViewById(R.id.tv_flight1);
                customFlightFromLay=(RelativeLayout)newView.findViewById(R.id.fromflight1_multicity);
                customFlightToLay=(RelativeLayout)newView.findViewById(R.id.toflight1_multicity);
                customFlightDateLay=(RelativeLayout)newView.findViewById(R.id.dateflight1_multicity);

                tv_optionalFrom=(TextView)customFlightFromLay.findViewById(R.id.tvToAndFromTextBefore);
                tv_CFlightFromCity=(TextView) customFlightFromLay.findViewById(R.id.tvToAndFromCityBefore);
                tv_OptionalTo=(TextView)customFlightToLay.findViewById(R.id.tvToAndFromTextBefore);
                tv_CFlightToCity=(TextView)customFlightToLay.findViewById(R.id.tvToAndFromCityBefore);
                tv_CFlightDay=(TextView)customFlightDateLay.findViewById(R.id.tvCalanderDayBefore);
                tv_CFlightDate=(TextView)customFlightDateLay.findViewById(R.id.tv_date);

                img_OptionalCity=(ImageView)customFlightToLay.findViewById(R.id.toAndFromIcon);
                img_OptionalCity.setImageResource(R.mipmap.to);

                tv_Flightno.setText("Flight "+pos);
                tv_optionalFrom.setText("From");
                tv_OptionalTo.setText("To");

                tv_CFlightFromCity.setText(traveller.getToArray().get(traveller.getToArray().size()-1));
                tv_CFlightToCity.setText("Enter a city or airport");
                tv_CFlightDay.setText("Choose Date");





                customFlightFromLay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), FromListOfFlightsActivity.class);
                        intent.putExtra("fragment","multicity");
                        intent.putExtra("fromFlight","flightOptionalFrom");
                        startActivity(intent);
                    }
                });

                customFlightToLay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ToListOfFlightActivity.class);
                        intent.putExtra("fragment","multicity");
                        intent.putExtra("toFlight","flightOptionalTo");
                        startActivity(intent);
                    }
                });

                customFlightDateLay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), SelectDateActivity.class);
                        intent.putExtra("fragment","multicity");
                        intent.putExtra("multicityDate","flightOptional");
                        startActivity(intent);
                    }
                });

                TravellersObject traveller = new TravellersObject();
                try {
                    traveller = db.getObjDetails(DBHelper.TABLE_AddTraveller,"multicity");
                    Log.i("travellerobject",traveller+"");
                } catch (DecoderException e) {
                    e.printStackTrace();
                }

               /* ArrayList<View> viewList = traveller.getViewList();
                Log.i("viewList",viewList.size()+"");
                viewList.add(newView);
                Log.i("viewList",viewList.size()+"");*/
                // traveller.setViewList(viewList);
                //  traveller.getFromArray().add(traveller.getToArray().get(traveller.getToArray().size()-1));
                //  traveller.getToArray().add("Enter a city or airport");
                //  traveller.getDateArray().add("Choose Date");


                /*traveller.setViewList(viewList);
                traveller.setCount(2);
                traveller.setFromArray(fromArray);
                traveller.setToArray(toArray);
                traveller.setDateArray(dateArray);
                traveller.setClass_S("Coach");*/

                // traveller.setViewList(viewList);

                //sp.getString("viewType","");
               /* Type type = new TypeToken<ArrayList<View>>(){}.getType();
                ArrayList<View> viewList = gson.fromJson(sp.getString("viewType",null), type);*/

                ViewSerializer viewObj = new ViewSerializer();
                String  fileName = "ViewType";
                if(InternalStorage.isFileExist(getActivity(),fileName)){
                    Log.i("fileName...",fileName+" exist");
                    try {
                        viewObj = (ViewSerializer) InternalStorage.readCache(getActivity(),fileName);
                        // Log.i("sizeee...",viewObj.getView().size()+" ***");
                        viewList = viewObj.getView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    //objDate = addDoseObj.getDate();
                    //renderUIFromInternal();
                }else{
                    Log.i("fileName...",fileName+" not exist");
                    //viewList = new ArrayList<>();
                    //View view;
                    //Log.i("viewList",viewList.size()+"");
                }
                // view.add(newView);
                viewObj.setView(newView);

                try {
                    InternalStorage.saveCache(getActivity(), viewObj,fileName);
                    Log.i("success","save");
                    //InternalStorage.writeObject(this, key, addDoseObj);
                } catch (IOException e) {
                    e.printStackTrace();
                }




                ArrayList<String> fromArray = traveller.getFromArray();
                ArrayList<String> toArray = traveller.getToArray();
                ArrayList<String> dateArray = traveller.getDateArray();

                fromArray.add(toArray.get(toArray.size()-1));
                toArray.add("Enter a city or airport");
                dateArray.add("Choose Date");

                traveller.setCount(pos);
                traveller.setFromArray(fromArray);
                traveller.setToArray(toArray);
                traveller.setDateArray(dateArray);
                traveller.setPassengers(traveller.getPassengers());
                traveller.setClass_S(tv_classType.getText().toString().trim());
                // Log.i(""+passengers.get("0"),passengers.get("2")+"");


                empRegByte = serial.serializeObject(traveller);
                ContentValues val = new ContentValues();
                val.put(DBHelper.ADDTRAVELLEROBJ, serial.serializeObject(traveller));
                db.update(DBHelper.TABLE_AddTraveller,val,"multicity",DBHelper.ADDTRAVELLERKEY);


                //traveller.getToArray().get(traveller.getToArray().size()-1);

                linearLayoutForm.addView(newView);
                //viewList.add(newView);
                //pos = SingleTon.mPref.getInt("MultiLayout", 0);
                Log.i("Lay no",pos+"");
                // SingleTon.mPref.edit().putInt("MultiLayout", pos + 1).commit();

            }

        } else if(view == deleteFLight){
            //layNo = SingleTon.mPref.getInt("MultiLayout", 0);



            TravellersObject traveller = new TravellersObject();
            try {
                traveller = db.getObjDetails(DBHelper.TABLE_AddTraveller, "multicity");
                Log.i("travellerobject", traveller + "");
            } catch (DecoderException e) {
                e.printStackTrace();
            }


            if(traveller.getCount()>=3) {
                // Log.i("ArrayList Size", viewList.size() + "");

               /* Type type = new TypeToken<ArrayList<View>>(){}.getType();
                ArrayList<View> viewList = gson.fromJson(sp.getString("viewType",""), type);
                Log.i("viewList",viewList.size()+"");

                linearLayoutForm.removeView(viewList.get(traveller.getCount()-3));
                viewList.remove(traveller.getCount() - 3);

                String viewString = gson.toJson(viewList);
                Log.d("TAG","jsonCars = " + viewString);
                editor.putString("viewType", viewString);
                editor.commit();*/




                ViewSerializer viewObj = new ViewSerializer();
                String  fileName = "ViewType";
                if(InternalStorage.isFileExist(getActivity(),fileName)){
                    Log.i("fileName...",fileName+" exist");
                    try {
                        viewObj = (ViewSerializer) InternalStorage.readCache(getActivity(),fileName);

                        readView = viewObj.getView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    //objDate = addDoseObj.getDate();
                    //renderUIFromInternal();
                }else{
                    Log.i("fileName...",fileName+" not exist");
                    //viewList = new ArrayList<>();
                    // Log.i("viewList",viewList.size()+"");
                }
                linearLayoutForm.removeView(readView);
                //viewList.remove(traveller.getCount() - 3);




                //viewList.add(newView);
                //viewObj.setView(viewList);



                //InternalStorage.saveCache(getActivity(), viewObj,fileName);

                // String  fileName2 = strTitle+"time";
                InternalStorage.deleteFile(getActivity(),fileName);




                // traveller.setViewList(traveller.getViewList());
                ArrayList<String> fromArray = traveller.getFromArray();
                ArrayList<String> toArray = traveller.getToArray();
                ArrayList<String> dateArray = traveller.getDateArray();

                fromArray.remove(fromArray.get(fromArray.size()-1));
                toArray.remove(toArray.get(toArray.size()-1));
                dateArray.remove(dateArray.get(dateArray.size()-1));

                traveller.setCount(traveller.getCount() - 1);
                traveller.setFromArray(fromArray);
                traveller.setToArray(toArray);
                traveller.setDateArray(dateArray);
                traveller.setPassengers(traveller.getPassengers());
                traveller.setClass_S(tv_classType.getText().toString().trim());
                // Log.i(""+passengers.get("0"),passengers.get("2")+"");

                //empRegByte = serial.serializeObject(traveller);
                ContentValues val = new ContentValues();
                val.put(DBHelper.ADDTRAVELLEROBJ,  serial.serializeObject(traveller));
                db.update(DBHelper.TABLE_AddTraveller,val,"multicity",DBHelper.ADDTRAVELLERKEY);
               /* if(layNo==2)
                    tv_CF2No.setText("Flight "+(layNo));
                else
                    tv_CF2No.setText("Flight "+(layNo)+" (Optional)");
                layNo = layNo-1;*/
                //SingleTon.mPref.edit().putInt("MultiLayout", layNo - 1).commit();
            }
            else{
                Log.i("ifstatement...", traveller.getCount() + "count");
            }
        }
    }

    private void addFlights(int pos,TravellersObject trvlr){
        //Log.i("if condition","outer");
        //Log.i("if condition",+SingleTon.mPref.getInt("MultiLayout", 0)+" ");
        // if(SingleTon.mPref.getInt("MultiLayout", 0)<4) {
        if(pos<6) {
            Log.i("addFlights",pos+"");
            final View newView = (View) getActivity().getLayoutInflater().inflate(R.layout.custom_multicity, null);
            newView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            pos = pos;

            tv_Flightno=(TextView)newView.findViewById(R.id.tv_flight1);

            customFlightFromLay=(RelativeLayout)newView.findViewById(R.id.fromflight1_multicity);
            customFlightToLay=(RelativeLayout)newView.findViewById(R.id.toflight1_multicity);
            customFlightDateLay=(RelativeLayout)newView.findViewById(R.id.dateflight1_multicity);

            tv_optionalFrom=(TextView)customFlightFromLay.findViewById(R.id.tvToAndFromTextBefore);
            tv_CFlightFromCity=(TextView) customFlightFromLay.findViewById(R.id.tvToAndFromCityBefore);
            tv_OptionalTo=(TextView)customFlightToLay.findViewById(R.id.tvToAndFromTextBefore);
            tv_CFlightToCity=(TextView)customFlightToLay.findViewById(R.id.tvToAndFromCityBefore);
            tv_CFlightDay=(TextView)customFlightDateLay.findViewById(R.id.tvCalanderDayBefore);
            tv_CFlightDate=(TextView)customFlightDateLay.findViewById(R.id.tv_date);

            img_OptionalCity=(ImageView)customFlightToLay.findViewById(R.id.toAndFromIcon);
            img_OptionalCity.setImageResource(R.mipmap.to);

            //linearLayoutForm=(LinearLayout)newView.findViewById(R.id.dynamic_flight);

            tv_Flightno.setText("Flight "+(pos+1));
            tv_optionalFrom.setText("From");
            tv_OptionalTo.setText("To");

            tv_CFlightFromCity.setText(trvlr.getFromArray().get(pos));
            tv_CFlightToCity.setText(trvlr.getToArray().get(pos));
            tv_CFlightDay.setText(trvlr.getDateArray().get(pos));





            customFlightFromLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), FromListOfFlightsActivity.class);
                    intent.putExtra("fragment","multicity");
                    intent.putExtra("fromFlight","flightOptionalFrom");
                    startActivity(intent);
                }
            });

            customFlightToLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ToListOfFlightActivity.class);
                    intent.putExtra("fragment","multicity");
                    intent.putExtra("toFlight","flightOptionalTo");
                    startActivity(intent);
                }
            });

            customFlightDateLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), SelectDateActivity.class);
                    intent.putExtra("fragment","multicity");
                    intent.putExtra("multicityDate","flightOptional");
                    startActivity(intent);
                }
            });
            linearLayoutForm.addView(newView);

            //pos = SingleTon.mPref.getInt("MultiLayout", 0);
            Log.i("Lay no",pos+"");
            // SingleTon.mPref.edit().putInt("MultiLayout", pos + 1).commit();

        }

    }


    @Override
    public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException {

        tv_classType.setText(coachArray.get(position));
        killScreen();

    }
           public void onBackPressed()
        {
          killScreen();
         }
    private void killScreen() {
    }


}
