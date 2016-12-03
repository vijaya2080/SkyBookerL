package in.example.skybooker.myaccount.mydetails;

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
import java.util.ArrayList;
import java.util.Calendar;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.myaccount.RecyclerTitlesAdapter;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;

public class MyDetailsActivity extends AppCompatActivity implements RecyclerTitlesAdapter.OnSecurityQnClickListener {
    TextView toolname;
    ImageView toolIcon,addtextimage,adddetailsImg,adddetails1Img,minus;
    static final int DATE_DIALOG_ID = 999;
    private int mYear, mMonth, mDay;
    RelativeLayout layout1, layout2;
    LinearLayout linear_airline;
    AlertDialog alert;
    RecyclerView titles_list;
    RecyclerTitlesAdapter mAdapter;
    // ArrayAdapter<String> title_arrayadapter;
    ArrayList<String> titles_Array;
    ArrayList<String> gender_Array;
    ArrayList<String> phoneCode_Array;
    ArrayList<String> unitedstates_Array;
    ArrayList<String> states_Array;
    ArrayList<String> seatpref_Array;
    ArrayList<String> mealpref_Array;
    ArrayList<String> specialservices_Array,airline_Array;
    Integer index;

    Button btnChangeDate,btaddCo;

    String spinnerKey = "";
    TextInputLayout myDetilsTitleLayout, myDetailsFirstLayout, myDetailsMiddleLayout, myDetailsLastLayout, myDetailsDOBLayout,
            myDetailsGenderLayout, myDetailsPhoneCodeLayout, myDetailsPhoneLayout, myDetailsAddress1Layout, myDetailsAddress2Layout,
            myDetailsCityLayout, myDetailsCityZipLayout, myDetailsCountryLayout, myDetailsStateLayout, myDetailsAdditionalLayout,
            myDetailsSeatPrefLayout, myDetailsMealPrefLayout, myDetailsSpclServicesLayout, myDetailsTSALayout, myDetailsFreqFlyerInfoLayout,
            myDetailsAirlineLayout, myDetailsFreqFlyLayout;
    EditText et_myDetilsTitle, et_myDetailsFirst, et_myDetailsMiddle, et_myDetailsLast, et_myDetailsDOB,
            et_myDetailsGender, et_myDetailsPhoneCode, et_myDetailsPhone, et_myDetailsAddress1, et_myDetailsAddress2,
            et_myDetailsCity, et_myDetailsCityZip, et_myDetailsCountry, et_myDetailsState, et_myDetailsAdditional,
            et_myDetailsSeatPref, et_myDetailsMealPref, et_myDetailsSpclServices, et_myDetailsTSA, et_myDetailsFreqFlyer,
            et_myDetailsAirline, et_myDetailsFreqFly;
    Button btMyDeatils;
    String str_myDetilsTitle, str_myDetailsFirst, str_myDetailsMiddle, str_myDetailsLast, str_myDetailsDOB,
            str_myDetailsGender, str_myDetailsPhoneCode, str_myDetailsPhone, str_myDetailsAddress1, str_myDetailsAddress2,
            str_myDetailsCity, str_myDetailsCityZip, str_myDetailsCountry, str_myDetailsState, str_myDetailsAdditional,
            str_myDetailsSeatPref, str_myDetailsMealPref, str_myDetailsSpclServices, str_myDetailsTSA, str_myDetailsFreqFlyer,
            str_myDetailsAirline, str_myDetailsFreqFly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);

        setToolBar();

        titles_Array = new ArrayList<>();
        gender_Array = new ArrayList<>();
        phoneCode_Array=new ArrayList<>();
        unitedstates_Array=new ArrayList<>();
        states_Array=new ArrayList<>();
        seatpref_Array=new ArrayList<>();
        mealpref_Array=new ArrayList<>();
        specialservices_Array=new ArrayList<>();
        airline_Array=new ArrayList<>();

       /* RelativeLayout toolbarlayout = (RelativeLayout) findViewById(R.id.myDetailsToolBar);
        toolname = (TextView) toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon = (ImageView) toolbarlayout.findViewById(R.id.toolBackImg);

        toolname.setText("My Details");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
        myDetilsTitleLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsTitleLayout);
        myDetilsTitleLayout.setHintEnabled(false);
        myDetailsFirstLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsFirstnameLayout);

        myDetailsMiddleLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsMiddlenameLayout);

        myDetailsLastLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsLastNameLayout);
        myDetailsDOBLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsDOBLayout);
        myDetilsTitleLayout.setHintEnabled(false);
        myDetailsGenderLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsGenderLayout);
        myDetailsGenderLayout.setHintEnabled(false);
        myDetailsPhoneCodeLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsPhoneCodeLayout);
        myDetailsPhoneCodeLayout.setHintEnabled(false);
        myDetailsPhoneLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsPhoneLayout);
        myDetailsAddress1Layout = (TextInputLayout) findViewById(R.id.text_input_myDetailsAddress1Layout);
        myDetailsAddress2Layout = (TextInputLayout) findViewById(R.id.text_input_myDetailsAddress2Layout);
        myDetailsCityLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsCityLayout);
        myDetailsCityZipLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsCityZipLayout);
        myDetailsCountryLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsCountryLayout);
        myDetailsCountryLayout.setHintEnabled(false);
        myDetailsStateLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsStateLayout);
        myDetailsStateLayout.setHintEnabled(false);
        myDetailsAdditionalLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsAdditionalLayout);
        myDetailsSeatPrefLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsSeatPrefLayout);
        myDetailsSeatPrefLayout.setHintEnabled(false);
        myDetailsMealPrefLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsMealPrefLayout);
        myDetailsMealPrefLayout.setHintEnabled(false);
        myDetailsSpclServicesLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsSpecialServicesLayout);
        myDetailsSpclServicesLayout.setHintEnabled(false);
        myDetailsTSALayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsTSARedressLayout);
        myDetailsFreqFlyerInfoLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsFrequentFlyerInfoLayout);
        myDetailsAirlineLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsAirlineLayout);
        myDetailsAirlineLayout.setHintEnabled(false);
        myDetailsFreqFlyLayout = (TextInputLayout) findViewById(R.id.text_input_myDetailsFrequentFlyerLayout);

        et_myDetilsTitle = (EditText) findViewById(R.id.et_myDetailsTitleLayout);
        et_myDetailsFirst = (EditText) findViewById(R.id.et_myDetailsFirstnameLayout);
        et_myDetailsMiddle = (EditText) findViewById(R.id.et_myDetailsMiddlenameLayout);
        et_myDetailsLast = (EditText) findViewById(R.id.et_myDetailsLastNameLayout);
        et_myDetailsDOB = (EditText) findViewById(R.id.myDetailsDOBLayout);
        et_myDetailsGender = (EditText) findViewById(R.id.et_myDetailsGenderLayout);
        et_myDetailsPhoneCode = (EditText) findViewById(R.id.et_myDetailsPhoneCodeLayout);
        et_myDetailsPhone = (EditText) findViewById(R.id.et_myDetailsPhoneLayout);
        et_myDetailsAddress1 = (EditText) findViewById(R.id.et_myDetailsAddress1Layout);
        et_myDetailsAddress2 = (EditText) findViewById(R.id.et_myDetailsAddress2Layout);
        et_myDetailsCity = (EditText) findViewById(R.id.et_myDetailsCityLayout);
        et_myDetailsCityZip = (EditText) findViewById(R.id.et_myDetailsCityZipLayout);
        et_myDetailsCountry = (EditText) findViewById(R.id.et_myDetailsCountryLayout);
        et_myDetailsState = (EditText) findViewById(R.id.et_myDetailsStateLayout);
        et_myDetailsAdditional = (EditText) findViewById(R.id.et_myDetailsAdditionalLayout);
        et_myDetailsSeatPref = (EditText) findViewById(R.id.et_myDetailsSeatPrefLayout);
        et_myDetailsMealPref = (EditText) findViewById(R.id.et_text_input_myDetailsMealPrefLayout);
        et_myDetailsSpclServices = (EditText) findViewById(R.id.et_myDetailsSpecialServicesLayout);
        et_myDetailsTSA = (EditText) findViewById(R.id.et_myDetailsTSARedressLayout);
        et_myDetailsFreqFlyer = (EditText) findViewById(R.id.et_myDetailsFrequentFlyerInfoLayout);
        et_myDetailsAirline = (EditText) findViewById(R.id.et_myDetailsAirlineLayout);
        et_myDetailsFreqFly = (EditText) findViewById(R.id.et_myDetailsFrequentFlyerLayout);
        minus=(ImageView)findViewById(R.id.MyDetails_minus) ;
        addtextimage=(ImageView)findViewById(R.id.MyDeatailsAddFlyerInfo);
        linear_airline=(LinearLayout)findViewById(R.id.linear_frequent);
        adddetails1Img = (ImageView) findViewById(R.id.MyDetailsup);
        layout1 = (RelativeLayout) findViewById(R.id.RL_AdditionalMyDeatils);
        adddetailsImg = (ImageView) findViewById(R.id.MyDetailsdown);

        addtextimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_airline.setVisibility(View.VISIBLE);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_airline.setVisibility(View.GONE);
            }
        });

        adddetailsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.VISIBLE);
                adddetails1Img.setVisibility(View.VISIBLE);
                adddetailsImg.setVisibility(View.GONE);
            }
        });
        adddetails1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.GONE);
                adddetails1Img.setVisibility(View.GONE);
                adddetailsImg.setVisibility(View.VISIBLE);
            }
        });

        btMyDeatils = (Button) findViewById(R.id.btupdatemydetailsSearch);
        btMyDeatils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateMyDetails();
            }
        });

        et_myDetailsGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(MyDetailsActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyDetailsActivity.this);
                alertDialogBuilder.setView(promptView1);

                // setup a dialog window
                // alertDialogBuilder.setCancelable(false);

                // create an alert dialog
                alert = alertDialogBuilder.create();
                alert.show();
                gender_Array.clear();
                gender_Array.add("Male");
                gender_Array.add("Female");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titleslist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey="gender";
                mAdapter = new RecyclerTitlesAdapter(MyDetailsActivity.this, gender_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(MyDetailsActivity.this);
                titles_list.setAdapter(mAdapter);

            }

        });
        et_myDetailsPhoneCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(MyDetailsActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyDetailsActivity.this);
                alertDialogBuilder.setView(promptView1);

                // setup a dialog window
                // alertDialogBuilder.setCancelable(false);

                // create an alert dialog
                alert = alertDialogBuilder.create();
                alert.show();
                phoneCode_Array.clear();
                phoneCode_Array.add("+01(US)");
                phoneCode_Array.add("+01(CA)");
                phoneCode_Array.add("+54(AR)");
                phoneCode_Array.add("+61(AU)");
                phoneCode_Array.add("+01(AI)");
                phoneCode_Array.add("+43(AT)");
                titles_list = (RecyclerView) promptView1.findViewById(R.id.titleslist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey= "phonecode";
                mAdapter = new RecyclerTitlesAdapter(MyDetailsActivity.this, phoneCode_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(MyDetailsActivity.this);
                titles_list.setAdapter(mAdapter);

            }
        });

        et_myDetilsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDetilsTitleLayout.setErrorEnabled(false);
                LayoutInflater layoutInflater = LayoutInflater.from(MyDetailsActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyDetailsActivity.this);
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
                titles_list = (RecyclerView) promptView1.findViewById(R.id.titleslist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey="title";
                mAdapter = new RecyclerTitlesAdapter(MyDetailsActivity.this, titles_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(MyDetailsActivity.this);
                titles_list.setAdapter(mAdapter);

            }
        });
        et_myDetailsCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(MyDetailsActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyDetailsActivity.this);
                alertDialogBuilder.setView(promptView1);

                // setup a dialog window
                // alertDialogBuilder.setCancelable(false);

                // create an alert dialog
                alert = alertDialogBuilder.create();
                alert.show();
                unitedstates_Array.clear();
                unitedstates_Array.add("Canada");
                unitedstates_Array.add("United Kingdom");
                unitedstates_Array.add("Australia");
                unitedstates_Array.add("Mexico");
                unitedstates_Array.add("Albania");
                unitedstates_Array.add("Argentina");
                titles_list = (RecyclerView) promptView1.findViewById(R.id.titleslist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey="unitedstates";
                mAdapter = new RecyclerTitlesAdapter(MyDetailsActivity.this, unitedstates_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(MyDetailsActivity.this);
                titles_list.setAdapter(mAdapter);
            }
        });
        et_myDetailsState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(MyDetailsActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyDetailsActivity.this);
                alertDialogBuilder.setView(promptView1);

                // setup a dialog window
                // alertDialogBuilder.setCancelable(false);

                // create an alert dialog
                alert = alertDialogBuilder.create();
                alert.show();
                states_Array.clear();
                states_Array.add("AK-ALASKHA,US");
                states_Array.add("AK-ALBAMA,US");
                states_Array.add("AK-ARZONA,US");
                states_Array.add("AK-COLORANDO,US");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titleslist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey="states";
                mAdapter = new RecyclerTitlesAdapter(MyDetailsActivity.this, states_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(MyDetailsActivity.this);
                titles_list.setAdapter(mAdapter);
            }
        });
        et_myDetailsSeatPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(MyDetailsActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyDetailsActivity.this);
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

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titleslist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey="seatpref";
                setrecycle(seatpref_Array);

            }
        });
        et_myDetailsMealPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(MyDetailsActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyDetailsActivity.this);
                alertDialogBuilder.setView(promptView1);

                // setup a dialog window
                // alertDialogBuilder.setCancelable(false);

                // create an alert dialog
                alert = alertDialogBuilder.create();
                alert.show();
                mealpref_Array.clear();
                mealpref_Array.add("Any Meal");
                mealpref_Array.add("Asian Vegetarian");
                mealpref_Array.add("Plain");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titleslist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey="mealpref";
                setrecycle(mealpref_Array);
            }
        });
        et_myDetailsSpclServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(MyDetailsActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyDetailsActivity.this);
                alertDialogBuilder.setView(promptView1);

                // setup a dialog window
                // alertDialogBuilder.setCancelable(false);

                // create an alert dialog
                alert = alertDialogBuilder.create();
                alert.show();
                specialservices_Array.clear();
                specialservices_Array.add("Strecher Assistance");
                specialservices_Array.add("Deaf Passenger");
                specialservices_Array.add("Blind Passenger");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titleslist);
               /* title_arrayadapter = new ArrayAdapter(MyDetailsActivity.this, android.R.layout.simple_list_item_1, titles_Group);
                titles_list.setAdapter(title_arrayadapter);
*/
                spinnerKey="specialservices";
                setrecycle(specialservices_Array);
            }
        });


        et_myDetailsAirline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(MyDetailsActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyDetailsActivity.this);
                alertDialogBuilder.setView(promptView1);
                alert = alertDialogBuilder.create();
                alert.show();
                airline_Array.clear();
                airline_Array.add("ADA air");
                airline_Array.add("Air");
                airline_Array.add("Air Luxer");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "airlines";

                mAdapter = new RecyclerTitlesAdapter(MyDetailsActivity.this, airline_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(MyDetailsActivity.this);
                titles_list.setAdapter(mAdapter);
            }
        });

        et_myDetailsDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
                LayoutInflater layoutInflater = LayoutInflater.from(MyDetailsActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.date_picker, null);
                btnChangeDate = (Button) promptView1.findViewById(R.id.btnChangeDate);
                showDate(mYear, mMonth+1, mDay);
                setCurrentDateOnView();
                return;


            }
        });


    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.myDetailsToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("My Details");
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
                startActivity(new Intent(MyDetailsActivity.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(MyDetailsActivity.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(MyDetailsActivity.this);
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
        et_myDetailsDOB.setText(new StringBuilder()
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
            et_myDetailsDOB.setText(new StringBuilder().append(mMonth + 1)
                    .append("-").append(mDay).append("-").append(mYear)
                    .append(" "));

        }
    };



    private void showDate(int year, int month, int day) {
        et_myDetailsDOB.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }





/*
    public void setCurrentDateOnView() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        et_myDetailsDOB.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(mMonth + 1).append("-").append(mDay).append("-")
                .append(mYear).append(" "));
    }

    public void addListenerOnButton() {



        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }*/

    public void setrecycle(ArrayList<String> dataArraylist){
        mAdapter = new RecyclerTitlesAdapter(MyDetailsActivity.this, dataArraylist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        titles_list.setLayoutManager(mLayoutManager);
        titles_list.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnSecurityQnClickListener(MyDetailsActivity.this);
        titles_list.setAdapter(mAdapter);
    }

    private void validateMyDetails() {
        str_myDetilsTitle = et_myDetilsTitle.getText().toString();
        str_myDetailsFirst = et_myDetailsFirst.getText().toString();
        str_myDetailsMiddle = et_myDetailsMiddle.getText().toString();
        str_myDetailsLast = et_myDetailsLast.getText().toString();
        str_myDetailsDOB = et_myDetailsDOB.getText().toString();
        str_myDetailsGender = et_myDetailsGender.getText().toString();
        str_myDetailsPhoneCode = et_myDetailsPhoneCode.getText().toString();
        str_myDetailsPhone = et_myDetailsPhone.getText().toString();
        str_myDetailsAddress1 = et_myDetailsAddress1.getText().toString();
        str_myDetailsAddress2 = et_myDetailsAddress2.getText().toString();
        str_myDetailsCity = et_myDetailsCity.getText().toString();
        str_myDetailsCityZip = et_myDetailsCityZip.getText().toString();
        str_myDetailsCountry = et_myDetailsCountry.getText().toString();
        str_myDetailsState = et_myDetailsState.getText().toString();
        str_myDetailsAdditional = et_myDetailsAdditional.getText().toString();
        str_myDetailsSeatPref = et_myDetailsSeatPref.getText().toString();
        str_myDetailsMealPref = et_myDetailsMealPref.getText().toString();
        str_myDetailsSpclServices = et_myDetailsSpclServices.getText().toString();
        str_myDetailsTSA = et_myDetailsTSA.getText().toString();
        str_myDetailsFreqFlyer = et_myDetailsFreqFlyer.getText().toString();
        str_myDetailsAirline = et_myDetailsAirline.getText().toString();
        str_myDetailsFreqFly = et_myDetailsFreqFly.getText().toString();

        myDetilsTitleLayout.setErrorEnabled(false);
        myDetailsFirstLayout.setErrorEnabled(false);
        myDetailsDOBLayout.setErrorEnabled(false);
        myDetailsGenderLayout.setErrorEnabled(false);
        myDetailsPhoneCodeLayout.setErrorEnabled(false);
        myDetailsPhoneLayout.setErrorEnabled(false);
        myDetailsAddress1Layout.setErrorEnabled(false);
        myDetailsCityLayout.setErrorEnabled(false);
        myDetailsCityZipLayout.setErrorEnabled(false);
        myDetailsCountryLayout.setErrorEnabled(false);
        myDetailsStateLayout.setErrorEnabled(false);
        myDetailsSeatPrefLayout.setErrorEnabled(false);
        myDetailsMealPrefLayout.setErrorEnabled(false);
        myDetailsAirlineLayout.setErrorEnabled(false);
        myDetailsFreqFlyLayout.setErrorEnabled(false);

        if (str_myDetilsTitle.length() == 0 || str_myDetilsTitle.length() == ' ') {
            myDetilsTitleLayout.setError("Please select title");
            myDetilsTitleLayout.setErrorEnabled(true);
        }
        else if (str_myDetailsFirst.length() == 0 || str_myDetailsFirst.length() == ' ') {
            myDetailsFirstLayout.setError("Please enter firstname");
            myDetailsFirstLayout.setErrorEnabled(true);
        }
       /* else if (str_myDetailsMiddle.length() == 0 || str_myDetailsMiddle.length() == ' ') {
            myDetailsMiddleLayout.setError("Please enter middlename");
            myDetailsMiddleLayout.setErrorEnabled(true);
        } */else if (str_myDetailsLast.length() == 0 || str_myDetailsLast.length() == ' ') {
            myDetailsLastLayout.setError("Please enter lastname");
            myDetailsLastLayout.setErrorEnabled(true);
        } else if (str_myDetailsDOB.length() == 0 || str_myDetailsDOB.length() == ' ') {
            myDetailsDOBLayout.setError("Please enter date of birth");
            myDetailsDOBLayout.setErrorEnabled(true);
        }else if (str_myDetailsGender.length() == 0 || str_myDetailsGender.length() == ' ') {
            myDetailsGenderLayout.setError("Please select gender");
            myDetailsGenderLayout.setErrorEnabled(true);
        } else if (str_myDetailsPhoneCode.length() == 0 || str_myDetailsPhoneCode.length() == ' ') {
            myDetailsPhoneCodeLayout.setError("Please enter phonecode");
            myDetailsPhoneCodeLayout.setErrorEnabled(true);
        } else if (str_myDetailsPhone.length() == 0 || str_myDetailsPhone.length() == ' ') {
            myDetailsPhoneLayout.setError("Please provide a phone number");
            myDetailsPhoneLayout.setErrorEnabled(true);
        } else if (str_myDetailsAddress1.length() == 0 || str_myDetailsAddress1.length() == ' ') {
            myDetailsAddress1Layout.setErrorEnabled(true);
            myDetailsAddress1Layout.setError("Please provide billing address");
        } else if (str_myDetailsCity.length() == 0 || str_myDetailsCity.length() == ' ') {
            myDetailsCityLayout.setError("Please provide a billing city.");
            myDetailsCityLayout.setErrorEnabled(true);
        } else if (str_myDetailsCityZip.length() == 0 || str_myDetailsCityZip.length() == ' ') {
            myDetailsCityZipLayout.setErrorEnabled(true);
            myDetailsCityZipLayout.setError("Please provide billing zip");
        }  else if (str_myDetailsCountry.length() == 0 || str_myDetailsCountry.length() == ' ') {
            myDetailsCountryLayout.setErrorEnabled(true);
            myDetailsCountryLayout.setError("Please select countryname");
        } else if (str_myDetailsState.length() == 0 || str_myDetailsState.length() == ' ') {
            myDetailsStateLayout.setError("Please select your billing state");
            myDetailsStateLayout.setErrorEnabled(true);
        }  /* else if (str_myDetailsSeatPref.length() == 0 || str_myDetailsSeatPref.length() == ' ') {
            myDetailsSeatPrefLayout.setError("Please select seatpref");
            myDetailsSeatPrefLayout.setErrorEnabled(true);
        }  else if (str_myDetailsMealPref.length() == 0 || str_myDetailsMealPref.length() == ' ') {
            myDetailsMealPrefLayout.setError("Please select mealpref");
            myDetailsMealPrefLayout.setErrorEnabled(true);
        }*/else if (str_myDetailsAirline.length() == 0 || str_myDetailsAirline.length() == ' ') {
            myDetailsAirlineLayout.setError("Please select Airline");
            myDetailsAirlineLayout.setErrorEnabled(true);
        } else if (str_myDetailsFreqFly.length() == 0 || str_myDetailsFreqFly.length() == ' ') {
            myDetailsFreqFlyLayout.setErrorEnabled(true);
            myDetailsFreqFlyLayout.setError("Please enter frequent flyer number.");
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MyDetailsActivity.this);
            dialog.setTitle("Your details  have been  updated .")
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                            finish();
                        }

                    });
            dialog.create().show();
        }
    }

    @Override
    public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException {
        //titles_Group.clear();
        //et_myDetilsTitle.setText(titles_Group.get(position));
        if (spinnerKey.equals("title")) {
            et_myDetilsTitle.setText(titles_Array.get(position));
            alert.dismiss();
        } else if (spinnerKey.equals("gender")) {
            et_myDetailsGender.setText(gender_Array.get(position));
            alert.dismiss();
        }
        else if (spinnerKey.equals("phonecode")){
            et_myDetailsPhoneCode.setText(phoneCode_Array.get(position));
            alert.dismiss();
        }
        else if(spinnerKey.equals("unitedstates"))
        {
            et_myDetailsCountry.setText(unitedstates_Array.get(position));
            alert.dismiss();
        }
        else if(spinnerKey.equals("states"))
        {
            et_myDetailsState.setText(states_Array.get(position));
            alert.dismiss();
        }
        else if(spinnerKey.equals("seatpref"))
        {
            et_myDetailsSeatPref.setText(seatpref_Array.get(position));
            alert.dismiss();
        }
        else if(spinnerKey.equals("mealpref"))
        {
            et_myDetailsMealPref.setText(mealpref_Array.get(position));
            alert.dismiss();
        }
        else if(spinnerKey.equals("specialservices"))
        {
            et_myDetailsSpclServices.setText(specialservices_Array.get(position));
            alert.dismiss();
        }
        else if(spinnerKey.equals("airlines"))
        {
            et_myDetailsAirline.setText(airline_Array.get(position));
            alert.dismiss();
        }

    }
}
