package in.example.skybooker.flightsearch.relatedflights.travelers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.flightsearch.relatedflights.payment.PaymentActivity;
import in.example.skybooker.myaccount.RecyclerTitlesAdapter;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;
import in.example.skybooker.pojo.FlightSegmentPojo;
import in.example.skybooker.pojo.OneWayPojo;

public class TravellerActivity extends AppCompatActivity implements RecyclerTitlesAdapter.OnSecurityQnClickListener {
    TextView FTostop, FFromstop, FDate, FDetails, FReview, FTravelers, FBook, Name, TravelerDetails,
            traveler, errorText, TTotalCharge, tTotalCost, ETRdate, ETRdepart, EFTFlname, EtRsave, ETRairF, EFRairDesc, EFRairnbe,
            ETRstart, ERTariline, ETRttrip, ERTaoper, ETRairlineair, ETRaaie;

    public static EditText previousTravelers, tTitle, tFirstName, tMdlName, tLstName, tDob, tGender, aTaddreq, tSeatp,
            tMeal, tspser, tTSA, tfflyer;

    ImageView  adddetails, imalltravel, imbottomf, adddetails1, profiledetails, extendreq, addbottom, addbottomtext;

    RelativeLayout  layout2, RincludeView,detailsLay;
    LinearLayout layout1;
    AlertDialog alert;
    RecyclerView titles_list;
    RecyclerTitlesAdapter mAdapter;
    Button btnChangeDate,travelerCountinue;
    int count=0;

    ArrayList<String> titles_Array,gender_Array,seatpref_Array,mealpref_Array,specialservices_Array,locationsArray;
    ArrayList<FlightSegmentPojo> segPojoArr;

    static final int DATE_DIALOG_ID = 999;
    private int mYear, mMonth, mDay;

    String strTitle, strTraveler, strFirst, strMiddle, strLast, strdob, strgender, stradd,
            strseatpref, strmeal, strstrspecial, strTsa, strfly,spinnerKey = "",outputDate,inputDate;

    TextInputLayout inputPrevious, inputTitle, inputMiddle, inputFName, inputMName, inputLName, inputdob, inputGender,
            inputaddreq, inputseat, inputMeal, inputSpe, inputTsa, inputfrefly;

    SimpleDateFormat dayFormater,format;

    OneWayPojo oneWayPojo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traveller);

        setToolBar();


        oneWayPojo = ((OneWayPojo)getIntent().getExtras().getBinder("reviewActivity"));
        segPojoArr=oneWayPojo.getSegmentArray();

        titles_Array = new ArrayList<>();
        gender_Array = new ArrayList<>();

        seatpref_Array = new ArrayList<>();
        mealpref_Array = new ArrayList<>();
        specialservices_Array = new ArrayList<>();


        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        FTostop = (TextView) findViewById(R.id.tv_toSingleFlightname);
        FFromstop = (TextView) findViewById(R.id.tv_fromsingleFlightname);

        for(int i = 0;i < oneWayPojo.getOnewayHP().size(); i++ ){

            locationsArray = new ArrayList<>();
            locationsArray = oneWayPojo.getOnewayHP().get(i);

            for (int j = 0; j < locationsArray.size(); j++ ){

                FTostop.setText(locationsArray.get(0));
                FFromstop.setText(locationsArray.get(locationsArray.size()-1));

            }

        }
        FDate = (TextView) findViewById(R.id.tv_SingleFlightDate);

        for (int k = 0; k < segPojoArr.size() ;k++){

            inputDate = segPojoArr.get(k).getDepartureDate();

            format = new SimpleDateFormat("ddMMyy");
            dayFormater = new SimpleDateFormat("EEE, MMM dd");
            try {
                Date date1 = format.parse(inputDate);
                outputDate = dayFormater.format(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            FDate.setText(outputDate);
        }

        detailsLay=(RelativeLayout)findViewById(R.id.travelerDetailsLay) ;
        FDetails = (TextView) findViewById(R.id.tv_SingleFlightDetails);

        RincludeView = (RelativeLayout) findViewById(R.id.toAndFromSingleFlightRL);
        FReview = (TextView) RincludeView.findViewById(R.id.tv_fromCityFlight);
        FTravelers = (TextView) RincludeView.findViewById(R.id.tv_toCitySingleFlight);
        FBook = (TextView) RincludeView.findViewById(R.id.tv_booktoCitySingleFlight);

        Name = (TextView) findViewById(R.id.tv_ProfileType);
        TravelerDetails = (TextView) findViewById(R.id.tv_TravellerDetails);
        traveler = (TextView)findViewById(R.id.tv_adult);
        errorText = (TextView) findViewById(R.id.tv_TravelerBottomText);

        TTotalCharge = (TextView) findViewById(R.id.tv_TravelerBottomChargeText);
        tTotalCost = (TextView) findViewById(R.id.tv_TravelerBottomTextTotalCost);
        tTotalCost.setText(oneWayPojo.getTotalFare());

        travelerCountinue = (Button) findViewById(R.id.Bt_SingleTravelerCountinue);


        layout1 = (LinearLayout) findViewById(R.id.flightDetailslayoutEx3);
        adddetails = (ImageView) findViewById(R.id.im_detailsextend);
        layout2 = (RelativeLayout) findViewById(R.id.RelativeTravelLayout1);
        adddetails1 = (ImageView) findViewById(R.id.im_detailsextendup);



        addbottomtext = (ImageView) findViewById(R.id.bottom_textimage);
        addbottom = (ImageView) findViewById(R.id.im_lasttravelerBottomim);
        profiledetails = (ImageView) findViewById(R.id.ProfileTravaller);
        imalltravel = (ImageView) findViewById(R.id.ElightDetailsTravelerExtension);
        imbottomf = (ImageView) findViewById(R.id.ElightDetailsTravelerExtensionup);

        previousTravelers = (EditText) findViewById(R.id.et_myDetailsPreviousTravellersLayout);
        tTitle = (EditText) findViewById(R.id.et_TravellersTitleLayout);

        tFirstName = (EditText) findViewById(R.id.et_TravellerFirstnameLayout);
        tMdlName = (EditText) findViewById(R.id.et_travellerMiddlenameLayout);
        tLstName = (EditText) findViewById(R.id.et_travellerLastNameLayout);
        tDob = (EditText) findViewById(R.id.et_travellerDOBLayout);
        tGender = (EditText) findViewById(R.id.et_travellerGenderLayout);
        aTaddreq = (EditText) findViewById(R.id.et_travellerAdditionalLayout);
        tSeatp = (EditText) findViewById(R.id.et_travellerSeatLayout);
        tMeal = (EditText) findViewById(R.id.et_travellerMealLayout);
        tspser = (EditText) findViewById(R.id.et_myDetailsPreviousTravellersExLayout);
        tTSA = (EditText) findViewById(R.id.et_myDetailsPreviousTravelerAdderExLayout);
        tfflyer = (EditText) findViewById(R.id.et_travellerFrquentFlyerLayout);

        inputPrevious = (TextInputLayout) findViewById(R.id.text_input_myDetailsSpecialServicesLayout);
        inputTitle = (TextInputLayout) findViewById(R.id.text_input_TravellersTitleLayout);
        inputTitle.setHintEnabled(false);
        //  inputMiddle=(TextInputLayout)findViewById(R.id.text_input_travellerMiddlenameLayout);
        inputFName = (TextInputLayout) findViewById(R.id.text_input_TravellerFirstnameLayout);
        inputMName = (TextInputLayout) findViewById(R.id.text_input_travellerMiddlenameLayout);
        inputLName = (TextInputLayout) findViewById(R.id.text_input_travellerLastNameLayout);
        inputdob = (TextInputLayout) findViewById(R.id.text_input_travellerDOBLayout);
        inputdob.setHintEnabled(false);
        inputGender = (TextInputLayout) findViewById(R.id.text_input_travellerGenderLayout);
        inputGender.setHintEnabled(false);
        inputaddreq = (TextInputLayout) findViewById(R.id.text_input_travellerAdditionalLayout);
        inputseat = (TextInputLayout) findViewById(R.id.text_input_travellerSeatPrefeLayout);
        inputseat.setHintEnabled(false);
        inputMeal = (TextInputLayout) findViewById(R.id.text_input_travellerMealLayout);
        inputMeal.setHintEnabled(false);
        inputSpe = (TextInputLayout) findViewById(R.id.text_input_myDetailsSpecialServicesExLayout);
        inputSpe.setHintEnabled(false);
        inputTsa = (TextInputLayout) findViewById(R.id.text_input_myDetailsAddrExLayout);
        inputTsa.setHintEnabled(false);
        inputfrefly = (TextInputLayout) findViewById(R.id.text_input_travelerFlyerFrequentLayout);


//extendreq=(ImageView)findViewById(R.id.ElightDetailsTravelerExtensionup);


        addbottomtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "image", Toast.LENGTH_SHORT).show();
            }
        });


        addbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "image", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TravellerActivity.this,TSAPrivacyPolicy.class));
            }
        });

        travelerCountinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addCardValidation();
            }
        });


        imbottomf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout2.setVisibility(View.GONE);
                imalltravel.setVisibility(View.VISIBLE);
                imbottomf.setVisibility(View.GONE);
                additionalValidation();
            }
        });
        adddetails1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.GONE);
                adddetails1.setVisibility(View.GONE);
                adddetails.setVisibility(View.VISIBLE);
            }
        });
       /* toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/


        imalltravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout2.setVisibility(View.VISIBLE);
                imalltravel.setVisibility(View.GONE);
                imbottomf.setVisibility(View.VISIBLE);
            }
        });


        detailsLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(layout1.isShown()){
                    layout1.setVisibility(View.GONE);
                    adddetails1.setVisibility(View.GONE);
                    adddetails.setVisibility(View.VISIBLE);
                }
                else {
                    if(count < oneWayPojo.getSegmentArray().size()) {

                        // if ( click ) {

                        layout1.setVisibility(View.VISIBLE);
                        adddetails1.setVisibility(View.VISIBLE);
                        adddetails.setVisibility(View.GONE);

                        LayoutInflater layoutInflater = LayoutInflater.from(TravellerActivity.this);
                        View v = layoutInflater.inflate(R.layout.custom_extend_details, null);

                        count = count + 1;
                        ETRdepart = (TextView) v.findViewById(R.id.tv_SinglePriceDetailsExDepart);
                        EtRsave = (TextView) v.findViewById(R.id.tv_bgsaveexflexibleDate);
                        ETRdate = (TextView) v.findViewById(R.id.tv_SinglePriceDetailsExDepartDate);
                        ETRairF = (TextView) v.findViewById(R.id.tv_SinglePriceDetailsExFlightName);
                        EFRairDesc = (TextView) v.findViewById(R.id.tv_SinglePriceDetailsExAirDesc);
                        EFRairnbe = (TextView) v.findViewById(R.id.tv_SinglePriceDetailsExAirnumber);
                        ETRstart = (TextView) v.findViewById(R.id.tv_changeExstartAitport);
                        EFTFlname = (TextView) v.findViewById(R.id.tv_FlightNameExFlight);
                        ERTariline = (TextView) v.findViewById(R.id.tv_changeExAitportAlliance);
                        ETRttrip = (TextView) v.findViewById(R.id.tv_changeExTotalTripTime);
                        ERTaoper = (TextView) v.findViewById(R.id.tv_ReviewFlexibleDate);
                        //ETRairlineair=(TextView)goneLayout.findViewById(R.id.tv_SinglePriceDetailsExDepart);
                        ETRaaie = (TextView) v.findViewById(R.id.tv_overriteExFlightdesc);
                        EFRairnbe.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "AirNumber", Toast.LENGTH_SHORT).show();
                            }
                        });

                        layout1.addView(v);
                        // }
                        // click=false;
                    }
                }

            }
        });
        tTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputTitle.setErrorEnabled(false);
                LayoutInflater layoutInflater = LayoutInflater.from(TravellerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TravellerActivity.this);
                alertDialogBuilder.setView(promptView1);

                // setup a dialog window
                // alertDialogBuilder.setCancelable(false);

                // create an alert dialog
                alert = alertDialogBuilder.create();
                alert.show();
                titles_Array.clear();
                titles_Array.add("Mr.");
                titles_Array.add("Mrs.");
                titles_Array.add("Miss.");
                titles_Array.add("Ms.");
                titles_Array.add("Dr.");
                titles_Array.add("Rev.");
                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey = "title";
                mAdapter = new RecyclerTitlesAdapter(TravellerActivity.this, titles_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(TravellerActivity.this);
                Log.i("TITLESLIST***", titles_list + "");
                titles_list.setAdapter(mAdapter);

            }
        });
        tGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(TravellerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TravellerActivity.this);
                alertDialogBuilder.setView(promptView1);

                // setup a dialog window
                // alertDialogBuilder.setCancelable(false);

                // create an alert dialog
                alert = alertDialogBuilder.create();
                alert.show();
                gender_Array.clear();
                gender_Array.add("Male");
                gender_Array.add("Female");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey = "gender";
                mAdapter = new RecyclerTitlesAdapter(TravellerActivity.this, gender_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(TravellerActivity.this);
                titles_list.setAdapter(mAdapter);

            }

        });

        tSeatp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(TravellerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TravellerActivity.this);
                alertDialogBuilder.setView(promptView1);

                // setup a dialog window
                // alertDialogBuilder.setCancelable(false);

                // create an alert dialog
                alert = alertDialogBuilder.create();
                alert.show();
                seatpref_Array.clear();
                seatpref_Array.add("Any Seat");
                seatpref_Array.add("Aisle Seat");
                seatpref_Array.add("Window Seat");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "seatpref";
                mAdapter = new RecyclerTitlesAdapter(TravellerActivity.this, seatpref_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(TravellerActivity.this);
                titles_list.setAdapter(mAdapter);


            }
        });
        tMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(TravellerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TravellerActivity.this);
                alertDialogBuilder.setView(promptView1);

                alert = alertDialogBuilder.create();
                alert.show();
                mealpref_Array.clear();
                mealpref_Array.add("Any Meal");
                mealpref_Array.add("Asian Vegetarian");
                mealpref_Array.add("Plain");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey = "mealpref";
                mAdapter = new RecyclerTitlesAdapter(TravellerActivity.this, mealpref_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(TravellerActivity.this);
                titles_list.setAdapter(mAdapter);

                // setrecycle(mealpref_Array);
            }
        });
        tspser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(TravellerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TravellerActivity.this);
                alertDialogBuilder.setView(promptView1);
                alert = alertDialogBuilder.create();
                alert.show();
                specialservices_Array.clear();
                specialservices_Array.add("Strecher Assistance");
                specialservices_Array.add("Deaf Passenger");
                specialservices_Array.add("Blind Passenger");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "specialservices";

                mAdapter = new RecyclerTitlesAdapter(TravellerActivity.this, specialservices_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(TravellerActivity.this);
                titles_list.setAdapter(mAdapter);


                //setrecycle(specialservices_Array);
            }
        });


        tDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(DATE_DIALOG_ID);
                LayoutInflater layoutInflater = LayoutInflater.from(TravellerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.date_picker, null);
                btnChangeDate = (Button) promptView1.findViewById(R.id.btnChangeDate);
                showDate(mYear, mMonth+1, mDay);
                setCurrentDateOnView();
                return;

            }
        });


        previousTravelers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(TravellerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TravellerActivity.this);
                alertDialogBuilder.setView(promptView1);
                alert = alertDialogBuilder.create();
                alert.show();
                specialservices_Array.clear();
                specialservices_Array.add("Name1");
                specialservices_Array.add("Name2");
                specialservices_Array.add("Name3");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "previuos";

                mAdapter = new RecyclerTitlesAdapter(TravellerActivity.this, specialservices_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(TravellerActivity.this);
                titles_list.setAdapter(mAdapter);
            }
        });
    }



    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.travellersToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Travelers");
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
                startActivity(new Intent(TravellerActivity.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(TravellerActivity.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(TravellerActivity.this);
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


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, datePickerListener, mYear, mMonth, mDay);
        }
        return null;
    }


    public void setCurrentDateOnView() {

        // tvDisplayDate = (TextView) findViewById(R.id.tvDate);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tDob.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(mMonth + 1).append("-").append(mDay).append("-")
                .append(mYear).append(" "));
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            mYear = selectedYear;
            mMonth = selectedMonth;
            mDay = selectedDay;

            // set selected date into textview
            tDob.setText(new StringBuilder().append(mMonth + 1)
                    .append("-").append(mDay).append("-").append(mYear)
                    .append(" "));

        }
    };



    private void showDate(int year, int month, int day) {
        tDob.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    private void additionalValidation() {

        strseatpref = tSeatp.getText().toString();
        strmeal = tMeal.getText().toString();
        strstrspecial = tspser.getText().toString();
        strTsa = tTSA.getText().toString();
        strfly = tfflyer.getText().toString();

        inputseat.setErrorEnabled(false);
        inputMeal.setErrorEnabled(false);
        inputSpe.setErrorEnabled(false);
        inputTsa.setErrorEnabled(false);
        inputfrefly.setErrorEnabled(false);


        if (strseatpref.length() == 0 || strseatpref.length() == ' ') {
            inputseat.setError("Please select seatpref");
            inputseat.setErrorEnabled(true);
        }
        else if (strmeal.length() == 0 || strmeal.length() == ' ') {
            inputMeal.setError("Please select mealpref");
            inputMeal.setErrorEnabled(true);

        }
        else if (strstrspecial.length() == 0 || strstrspecial.length() == ' ') {
            inputSpe.setError("Please select special services");
            inputSpe.setErrorEnabled(true);
        }
        else if (strTsa.length() == 0 || strTsa.length() == ' ') {
            inputTsa.setError("Please select TSA");
            inputTsa.setErrorEnabled(true);
        }
        else if (strfly.length() == 0 || strfly.length() == ' ') {
            inputfrefly.setError("Please select Flyer");
            inputfrefly.setErrorEnabled(true);
        }

    }

    private void addCardValidation() {
        strTitle = tTitle.getText().toString();
        strTraveler = previousTravelers.getText().toString();
        strFirst = tFirstName.getText().toString();
        strMiddle = tMdlName.getText().toString();
        strLast=tLstName.getText().toString();
        strdob = tDob.getText().toString();
        strgender = tGender.getText().toString();
        stradd = aTaddreq.getText().toString();


        inputPrevious.setErrorEnabled(false);
        inputTitle.setErrorEnabled(false);
        // addBillCardAliasNameLayout .setErrorEnabled(false);
        inputFName.setErrorEnabled(false);
        inputMName.setErrorEnabled(false);
        // addBillAddress2Layout.setErrorEnabled(false);
        inputLName.setErrorEnabled(false);
        inputdob.setErrorEnabled(false);
        inputGender.setErrorEnabled(false);
        inputaddreq.setErrorEnabled(false);


        if (strTitle.length() == 0 || strTitle.length() == ' ') {
            inputTitle.setError("Please select title");
            inputTitle.setErrorEnabled(true);

        }
        else if (strTraveler.length() == 0 || strTraveler.length() == ' ') {
            inputPrevious.setError("Please enter Previous Travelers");
            inputPrevious.setErrorEnabled(true);

        }
        else if (strFirst.length() == 0 || strFirst.length() == ' ') {
            inputFName.setError("Please enter firstname");
            inputFName.setErrorEnabled(true);
        }
        else if (strLast.length() == 0 || strLast.length() == ' ') {
            inputLName.setError("Please enter lastname");
            inputLName.setErrorEnabled(true);
        }
        else if (strdob.length() == 0 || strdob.length() == ' ') {
            inputdob.setError("Please enter date of birth");
            inputdob.setErrorEnabled(true);
        }
        else if (strgender.length() == 0 || strgender.length() == ' ') {
            inputGender.setError("Please select gender");
            inputGender.setErrorEnabled(true);
        }

        else {
            final Bundle b1 = new Bundle();
            b1.putBinder("travelerActivity", oneWayPojo);
            startActivity(new Intent(TravellerActivity.this, PaymentActivity.class).putExtras(b1));
            Log.d("Sent", "object" + oneWayPojo);

        }
    }



    @Override
    public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException {
//        Toast.makeText(TravellerActivity.this,position,Toast.LENGTH_LONG).show();
        if (spinnerKey.equals("title")) {
            tTitle.setText(titles_Array.get(position));
            alert.dismiss();
        }
        else if (spinnerKey.equals("previuos")) {
            previousTravelers.setText(specialservices_Array.get(position));
            alert.dismiss();
        }
        else if (spinnerKey.equals("gender")){
            tGender.setText(gender_Array.get(position));
            alert.dismiss();
        }
        else if(spinnerKey.equals("seatpref"))
        {
            tSeatp.setText(seatpref_Array.get(position));
            alert.dismiss();
        }
        else if(spinnerKey.equals("mealpref"))
        {
            tMeal.setText(mealpref_Array.get(position));
            alert.dismiss();
        }
        else if(spinnerKey.equals("specialservices"))
        {
            tspser.setText(specialservices_Array.get(position));
            alert.dismiss();
        }


    }
}