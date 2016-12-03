package in.example.skybooker.myaccount.cotravelers;

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
import java.util.ArrayList;
import java.util.Calendar;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.myaccount.RecyclerTitlesAdapter;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;

public class AddCoTravelerActivity extends AppCompatActivity implements RecyclerTitlesAdapter.OnSecurityQnClickListener {
    TextView toolname;
    ImageView toolIcon,minus,addtextimage,adddetailsImg,adddetails1Img;

    EditText previousTravelers, tTitle, tFirstName, tMdlName, tLstName, tDob, tGender, aTaddreq, tSeatp,
            tMeal, tspser, tTSA, tfflyer,et_airline,et_freguentflyer;
    TextInputLayout inputPrevious, inputTitle, inputMiddle, inputFName, inputMName, inputLName, inputdob, inputGender,
            inputaddreq, inputseat, inputMeal, inputSpe, inputTsa, inputfrefly,inputairline,inputflyer;

    RelativeLayout layout1, layout2;
    LinearLayout linear_airline;
    AlertDialog alert;
    RecyclerView titles_list;
    RecyclerTitlesAdapter mAdapter;
    Button btnChangeDate,btaddCo;

    ArrayList<String> titles_Array;
    ArrayList<String> gender_Array;
    ArrayList<String> seatpref_Array;
    ArrayList<String> mealpref_Array;
    ArrayList<String> specialservices_Array,airline_Array;

    DatePickerDialog datePickerDialog;
    static final int DATE_DIALOG_ID = 999;
    private int mYear, mMonth, mDay;
    String spinnerKey = "";
    String strTitle, strTraveler, strFirst, strMiddle, strLast, strdob, strgender, stradd,
            strseatpref, strmeal, strstrspecial, strTsa, strfly,airline,frequentflyer;
    private String str_myDetailsAirline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_co_traveler);

        setToolBar();

        /*RelativeLayout toolbarlayout = (RelativeLayout) findViewById(R.id.add_CTToolBar);
        toolname = (TextView) toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon = (ImageView) toolbarlayout.findViewById(R.id.toolBackImg);

        toolname.setText("Add Co-Traveler");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        titles_Array = new ArrayList<>();
        gender_Array = new ArrayList<>();

        seatpref_Array = new ArrayList<>();
        mealpref_Array = new ArrayList<>();
        specialservices_Array = new ArrayList<>();
        airline_Array=new ArrayList<>();

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        btaddCo=(Button)findViewById(R.id.btCTAdd);
        tTitle=(EditText)findViewById(R.id.et_CoTravellersTitleLayout) ;
        tFirstName = (EditText) findViewById(R.id.et_CoTravellerFirstnameLayout);
        tMdlName = (EditText) findViewById(R.id.et_CotravellerMiddlenameLayout);
        tLstName = (EditText) findViewById(R.id.et_CotravellerLastNameLayout);
        tDob = (EditText) findViewById(R.id.et_CotravellerDOBLayout);
        tGender = (EditText) findViewById(R.id.et_CotravellerGenderLayout);
        aTaddreq = (EditText) findViewById(R.id.et_CotravellerAdditionalLayout);
        tSeatp = (EditText) findViewById(R.id.et_CotravellerSeatLayout);
        tMeal = (EditText) findViewById(R.id.et_CotravellerMealLayout);
        tspser = (EditText) findViewById(R.id.et_CTPreviousTravellersExLayout);
        tTSA = (EditText) findViewById(R.id.et_CoTravelerAdderExLayout);
        tfflyer = (EditText) findViewById(R.id.et_CotravellerFrquentFlyerLayout);
        et_airline=(EditText)findViewById(R.id.et_myDetailsAirlineLayout);
        et_freguentflyer=(EditText)findViewById(R.id.et_CTFrequentFlyerLayout) ;

        inputPrevious = (TextInputLayout) findViewById(R.id.text_input_CTSpecialServicesExLayout);
        inputTitle = (TextInputLayout) findViewById(R.id.text_input_CoTravellersTitleLayout);
        inputTitle.setHintEnabled(false);
        inputFName = (TextInputLayout) findViewById(R.id.text_input_CoTravellerFirstnameLayout);
        inputMName = (TextInputLayout) findViewById(R.id.text_input_CotravellerMiddlenameLayout);
        inputLName = (TextInputLayout) findViewById(R.id.text_input_CotravellerLastNameLayout);
        inputdob = (TextInputLayout) findViewById(R.id.text_input_CotravellerDOBLayout);
        inputdob.setHintEnabled(false);
        inputGender = (TextInputLayout) findViewById(R.id.text_input_CotravellerGenderLayout);
        inputGender.setHintEnabled(false);
        inputaddreq = (TextInputLayout) findViewById(R.id.text_input_CotravellerAdditionalLayout);
        inputseat = (TextInputLayout) findViewById(R.id.text_input_CotravellerSeatPrefeLayout);
        inputseat.setHintEnabled(false);
        inputMeal = (TextInputLayout) findViewById(R.id.text_input_CotravellerMealLayout);
        inputMeal.setHintEnabled(false);
        inputSpe = (TextInputLayout) findViewById(R.id.text_input_CTSpecialServicesExLayout);
        inputSpe.setHintEnabled(false);
        inputTsa = (TextInputLayout) findViewById(R.id.text_input_CTAddrExLayout);
        inputTsa.setHintEnabled(false);
        inputfrefly = (TextInputLayout) findViewById(R.id.text_input_CotravelerFlyerFrequentLayout);
        inputairline=(TextInputLayout)findViewById(R.id.text_input_CoTravellerAirlineLayout) ;
        inputairline.setHintEnabled(false);
        inputflyer=(TextInputLayout)findViewById(R.id.text_input_CTFrequentFlyerLayout) ;
        minus=(ImageView)findViewById(R.id.CT_minus) ;
        addtextimage=(ImageView)findViewById(R.id.AddFlyerInfo);
        linear_airline=(LinearLayout)findViewById(R.id.CoTravellerlinear_frequent);

        layout1 = (RelativeLayout) findViewById(R.id.RelativeCoTravelLayout1);
        adddetailsImg = (ImageView) findViewById(R.id.ElightDetailsCoTravelerExtension);

        btaddCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // finish();
                validation();
            }
        });

        adddetails1Img = (ImageView) findViewById(R.id.ElightDetailsCoTravelerExtensionup);

       /* adddetailsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.VISIBLE);
                adddetails1Img.setVisibility(View.VISIBLE);
                adddetailsImg.setVisibility(View.GONE);
            }
        });*/



        adddetails1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.GONE);
                adddetails1Img.setVisibility(View.GONE);
                adddetailsImg.setVisibility(View.VISIBLE);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_airline.setVisibility(View.GONE);
            }
        });

        addtextimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear_airline.setVisibility(View.VISIBLE);
            }
        });

        tTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputTitle.setErrorEnabled(false);
                LayoutInflater layoutInflater = LayoutInflater.from(AddCoTravelerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddCoTravelerActivity.this);
                alertDialogBuilder.setView(promptView1);

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

                spinnerKey = "title";
                mAdapter = new RecyclerTitlesAdapter(AddCoTravelerActivity.this, titles_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(AddCoTravelerActivity.this);
                Log.i("TITLESLIST***", titles_list + "");
                titles_list.setAdapter(mAdapter);

            }
        });

        tGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(AddCoTravelerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddCoTravelerActivity.this);
                alertDialogBuilder.setView(promptView1);

                alert = alertDialogBuilder.create();
                alert.show();
                gender_Array.clear();
                gender_Array.add("Male");
                gender_Array.add("Female");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);

                spinnerKey = "gender";
                mAdapter = new RecyclerTitlesAdapter(AddCoTravelerActivity.this, gender_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(AddCoTravelerActivity.this);
                titles_list.setAdapter(mAdapter);

            }

        });

        tSeatp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(AddCoTravelerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddCoTravelerActivity.this);
                alertDialogBuilder.setView(promptView1);

                alert = alertDialogBuilder.create();
                alert.show();
                seatpref_Array.clear();
                seatpref_Array.add("Any Seat");
                seatpref_Array.add("Aisle Seat");
                seatpref_Array.add("Window Seat");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "seatpref";
                mAdapter = new RecyclerTitlesAdapter(AddCoTravelerActivity.this, seatpref_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(AddCoTravelerActivity.this);
                titles_list.setAdapter(mAdapter);
            }
        });

        tMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(AddCoTravelerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddCoTravelerActivity.this);
                alertDialogBuilder.setView(promptView1);

                alert = alertDialogBuilder.create();
                alert.show();
                mealpref_Array.clear();
                mealpref_Array.add("Any Meal");
                mealpref_Array.add("Asian Vegetarian");
                mealpref_Array.add("Plain");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);

                spinnerKey = "mealpref";
                mAdapter = new RecyclerTitlesAdapter(AddCoTravelerActivity.this, mealpref_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(AddCoTravelerActivity.this);
                titles_list.setAdapter(mAdapter);

            }
        });
        tspser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(AddCoTravelerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddCoTravelerActivity.this);
                alertDialogBuilder.setView(promptView1);
                alert = alertDialogBuilder.create();
                alert.show();
                specialservices_Array.clear();
                specialservices_Array.add("Strecher Assistance");
                specialservices_Array.add("Deaf Passenger");
                specialservices_Array.add("Blind Passenger");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "specialservices";

                mAdapter = new RecyclerTitlesAdapter(AddCoTravelerActivity.this, specialservices_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(AddCoTravelerActivity.this);
                titles_list.setAdapter(mAdapter);

            }
        });


        et_airline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(AddCoTravelerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddCoTravelerActivity.this);
                alertDialogBuilder.setView(promptView1);
                alert = alertDialogBuilder.create();
                alert.show();
                airline_Array.clear();
                airline_Array.add("ADA air");
                airline_Array.add("Air");
                airline_Array.add("Air Luxer");

                titles_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "airlines";

                mAdapter = new RecyclerTitlesAdapter(AddCoTravelerActivity.this, airline_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                titles_list.setLayoutManager(mLayoutManager);
                titles_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(AddCoTravelerActivity.this);
                titles_list.setAdapter(mAdapter);
            }
        });


        tDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(DATE_DIALOG_ID);
                LayoutInflater layoutInflater = LayoutInflater.from(AddCoTravelerActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.date_picker, null);
                btnChangeDate = (Button)promptView1.findViewById(R.id.btnChangeDate);
                setCurrentDateOnView();
                showDate(mYear, mMonth+1, mDay);
                return;

            }
        });



    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.add_CTToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Add Co-Traveler");
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
                startActivity(new Intent(AddCoTravelerActivity.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(AddCoTravelerActivity.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(AddCoTravelerActivity.this);
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




    public  void validation(){

    strTitle = tTitle.getText().toString();
    strFirst = tFirstName.getText().toString();
    strLast=tLstName.getText().toString();
    strdob = tDob.getText().toString();
    strgender = tGender.getText().toString();
    airline=et_airline.getText().toString();

    strfly = et_freguentflyer.getText().toString();
    inputTitle.setErrorEnabled(false);
    inputFName.setErrorEnabled(false);
    inputLName.setErrorEnabled(false);
    inputdob.setErrorEnabled(false);
    inputGender.setErrorEnabled(false);
    inputairline.setErrorEnabled(false);
    inputflyer.setErrorEnabled(false);

    if (strTitle.length() == 0 || strTitle.length() == ' ') {
        inputTitle.setError("Please select title");
        inputTitle.setErrorEnabled(true);
    }
    else if (strFirst.length() == 0 || strFirst.length() == ' ') {
        inputFName.setError("Please enter firstname");
        inputFName.setErrorEnabled(true);
    } else if (strLast.length() == 0 || strLast.length() == ' ') {
        inputLName.setError("Please enter lastname");
        inputLName.setErrorEnabled(true);
    } else if (strdob.length() == 0 || strdob.length() == ' ') {
        inputdob.setError("Please enter date of birth");
        inputdob.setErrorEnabled(true);
    } else if (strgender.length() == 0 || strgender.length() == ' ') {
        inputGender.setError("Please select gender");
        inputGender.setErrorEnabled(true);
    } else if (airline.length() == 0 || airline.length() == ' ') {
        inputairline.setError("Please select Airline");
        inputairline.setErrorEnabled(true);
    } else if (strfly.length() == 0 || strfly.length() == ' ') {
        inputflyer.setError("Please select Flyer");
        inputflyer.setErrorEnabled(true);
    }
    else
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(AddCoTravelerActivity.this);
        dialog.setTitle("Your co-traveler details has been saved.")
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
        if (spinnerKey.equals("title")) {
            tTitle.setText(titles_Array.get(position));
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

        else  if(spinnerKey.equals("airlines"))
        {
            // startActivity(new Intent(TravellerActivity.this, SelectDateActivity.class));
            et_airline.setText(airline_Array.get(position));
            alert.dismiss() ;
        }

    }
}
