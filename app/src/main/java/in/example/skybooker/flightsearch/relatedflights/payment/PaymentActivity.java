package in.example.skybooker.flightsearch.relatedflights.payment;

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
import android.widget.CheckBox;
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

import in.example.skybooker.flightsearch.relatedflights.PriceDetails;
import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.travelers.TravellerActivity;
import in.example.skybooker.myaccount.RecyclerTitlesAdapter;
import in.example.skybooker.myaccount.billing.NortonSecuredSealAcitivity;
import in.example.skybooker.myaccount.billing.PaymentPolicyActivity;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;
import in.example.skybooker.pojo.FlightSegmentPojo;
import in.example.skybooker.pojo.OneWayPojo;

public class PaymentActivity extends AppCompatActivity implements RecyclerTitlesAdapter.OnSecurityQnClickListener {
    TextView FTostop, FFromstop, FDate, FDetails, FReview, FTravelers, FBook, ETRdate,
            ETRdepart, EFTFlname, EtRsave, ETRairF, EFRairDesc, EFRairnbe,
            ETRstart, ERTariline, ETRttrip, ERTaoper, ETRairlineair, ETRaaie,
            PPay, Por, Pcardinfo, subinfo, PPaypolocy, Pprivacy, PBillinfo, PsendmePconfirm, Ptextadd, peditname, pexadd, Ptermscon, Ptotal, Pcost;

    ImageView parrowup, parrowdown, impay, pimtoggle, pbottomim,cvvImg,paymentaAddcardTye;
    RelativeLayout detailslayout;
    LinearLayout layout1;
    Button booknow;
    CheckBox travelcheck;
    Button btnChangeDate;
    int count = 0;
    static final int DATE_DIALOG_ID = 0;
    private int mYear, mMonth, mDay;
    EditText pcardnbr, pnamecard, pexpidate, edcvv, paddess1, paddress2, pcity,
            punited, psates, pzip, psusnbr1, psbillng, psusnbr2, pcontact, pemail, pemailcfm,edittravel,editfName,editmName,editlName;

    String strcardNbr,strtypeofCard, strNOCard, strExDate, strCVV, strAdrees1, strAddress2, strCity, strUnitedSt, strState,
            strZip, strUSn1, strBPhone, strUSn2, strCPhone, strEmail, strCEmail,str_editFname,str_editMName,str_editLName,
            outputDate,inputDate,sdfDate;

    TextInputLayout inputCardNo, inputNameOC, inputEdate, inputCVV, inputAddr1, inputAddr2, inputCity, inputUSt,
            inputSt, inputZip, inputUSN1, inputPhone, inputUSN2, inputCPhn, inputEmail, inputCEmail,
            edit_edTravel,edit_fName,edit_MName,edit_lName;
    String spinnerKey = "";
    AlertDialog.Builder alertDialogBuilder;
    // AlertDialog alert;
    RecyclerView states_list;
    RecyclerTitlesAdapter mAdapter;
    AlertDialog alertDialog;


    int[] myImageList;

    ArrayList<String> States_Array,UStates_Array,USN1_Array,USN2_Array,editName,cardType,locationsArray;
    ArrayList<FlightSegmentPojo> segPojoArr;
    SimpleDateFormat dayFormater,format,sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        myImageList = new int[]{R.mipmap.visa1, R.mipmap.master,R.mipmap.aexpress,R.mipmap.club,
                R.mipmap.discover1,R.mipmap.cartle};



        setToolBar();

        final OneWayPojo oneWayPojo = ((OneWayPojo)getIntent().getExtras().getBinder("travelerActivity"));
        segPojoArr=oneWayPojo.getSegmentArray();

        format = new SimpleDateFormat("ddMMyy");
        dayFormater = new SimpleDateFormat("EEE, MMM dd");
        sdf = new SimpleDateFormat("EEE MM/dd");

        layout1 = (LinearLayout) findViewById(R.id.flightDetailslayoutEx4);

        alertDialogBuilder = new AlertDialog.Builder(PaymentActivity.this);

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


            try {
                Date date1 = format.parse(inputDate);
                outputDate = dayFormater.format(date1);
                sdfDate = sdf.format(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            FDate.setText(outputDate);
        }

        FDetails = (TextView) findViewById(R.id.tv_SingleFlightpCost);

        RelativeLayout RincludeView = (RelativeLayout) findViewById(R.id.toAndFromSingleFlightRL);
        FReview = (TextView) RincludeView.findViewById(R.id.tv_fromCityFlight);
        FTravelers = (TextView) RincludeView.findViewById(R.id.tv_toCitySingleFlight);
        FBook = (TextView) RincludeView.findViewById(R.id.tv_booktoCitySingleFlight);


        States_Array = new ArrayList<>();
        UStates_Array = new ArrayList<>();
        USN1_Array = new ArrayList<>();
        USN2_Array = new ArrayList<>();
        editName =new ArrayList<>();
        cardType=new ArrayList<>();


        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        pcardnbr = (EditText) findViewById(R.id.et_updatePaymentCardNo);
        pnamecard = (EditText) findViewById(R.id.et_updatePaymentNameOnCard);
        pexpidate = (EditText) findViewById(R.id.et_paymentexpired);
        edcvv = (EditText) findViewById(R.id.et_paymentCVVLayout);
        paddess1 = (EditText) findViewById(R.id.et_myDetailsPAddress1Layout);
        paddress2 = (EditText) findViewById(R.id.et_myDetailsAddress2pLayout);
        pcity = (EditText) findViewById(R.id.et_myDetailsCitypLayout);
        punited = (EditText) findViewById(R.id.et_myDetailsPCountryLayout);
        psates = (EditText) findViewById(R.id.et_myDetailsPStateLayout);
        pzip = (EditText) findViewById(R.id.et_myDetailsCityPZipLayout);
        psusnbr1 = (EditText) findViewById(R.id.et_PaymentNuberLayout);
        psbillng = (EditText) findViewById(R.id.et_paymentBillingLayout);
        psusnbr2 = (EditText) findViewById(R.id.et_PaymentNuber1Layout);
        pcontact = (EditText) findViewById(R.id.et_paymentContactLayout);
        pemail = (EditText) findViewById(R.id.et_updatePaymentEmail);
        pemailcfm = (EditText) findViewById(R.id.et_updatePaymentconEmail);

        travelcheck=(CheckBox)findViewById(R.id.check_image) ;

        booknow=(Button)findViewById(R.id.Bt_SingleTravelerCountinue);

        PPay = (TextView) findViewById(R.id.tv_pay);
        Por = (TextView) findViewById(R.id.tv_paymentOR);
        Pcardinfo = (TextView) findViewById(R.id.tv_card);
        subinfo = (TextView) findViewById(R.id.tv_subcard);
        PPaypolocy = (TextView) findViewById(R.id.tv_payPolocy);
        Pprivacy = (TextView) findViewById(R.id.tv_priPolocy);
        PBillinfo = (TextView) findViewById(R.id.tv_pbilling);
        PsendmePconfirm = (TextView) findViewById(R.id.tv_travelCheckDeals);
        Ptextadd = (TextView) findViewById(R.id.tv_personconfirm);
        Ptextadd.setText("Please confirm that the dates, times of flight departures are accurate : Depart ("+ sdfDate + ")");
        Ptermscon = (TextView) findViewById(R.id.tv_termsconditions);
        Ptotal = (TextView) findViewById(R.id.tv_TravelerBottomChargeText);

        Pcost = (TextView) findViewById(R.id.tv_TravelerBottomTextTotalCost);
        Pcost.setText(oneWayPojo.getTotalFare());

        peditname = (TextView) findViewById(R.id.tv_editName);
        peditname.setText("Please also confirm that the names of traveler are accurate:" +
                TravellerActivity.tFirstName.getText().toString() + TravellerActivity.tLstName.getText().toString());

        pexadd = (TextView) findViewById(R.id.tv_textadddesc);

        detailslayout=(RelativeLayout)findViewById(R.id.detailsLayout);
        parrowup = (ImageView) findViewById(R.id.im_detailspextend);
        cvvImg=(ImageView)findViewById(R.id.cvvImg);
        parrowdown = (ImageView) findViewById(R.id.im_detailspextendup);
        impay = (ImageView) findViewById(R.id.imPay);
        pimtoggle = (ImageView) findViewById(R.id.imPayPolocy);
        pbottomim = (ImageView) findViewById(R.id.im_lasttravelBottomim);
        paymentaAddcardTye=(ImageView)findViewById(R.id.im_paymentNameOnCard);




        inputCardNo = (TextInputLayout) findViewById(R.id.text_input_updatePayBillingCardNo);
        inputCardNo.setErrorEnabled(false);
        inputNameOC = (TextInputLayout) findViewById(R.id.text_input_updatePaymentNameOnCard);
        inputNameOC.setErrorEnabled(false);
        //  inputMiddle=(TextInputLayout)findViewById(R.id.text_input_travellerMiddlenameLayout);
        inputEdate = (TextInputLayout) findViewById(R.id.text_input_paymentexpiredLayout);
        inputEdate.setErrorEnabled(false);
        inputCVV = (TextInputLayout) findViewById(R.id.text_input_paymentCvvLayout);
        inputCVV.setErrorEnabled(false);
        inputAddr1 = (TextInputLayout) findViewById(R.id.text_input_myDetailsAddress1Layout);
        inputAddr1.setErrorEnabled(false);
        inputAddr2 = (TextInputLayout) findViewById(R.id.text_input_myDetailsAddress2Layout);
        inputAddr2.setErrorEnabled(false);
        inputCity = (TextInputLayout) findViewById(R.id.text_input_myDetailsCityLayout);
        inputCity.setErrorEnabled(false);
        inputUSt = (TextInputLayout) findViewById(R.id.text_input_myDetailsCountryLayout);
        inputUSt.setErrorEnabled(false);
        inputUSt.setHintEnabled(false);
        inputSt = (TextInputLayout) findViewById(R.id.text_input_myDetailsStateLayout);
        inputSt.setErrorEnabled(false);
        inputSt.setHintEnabled(false);
        inputZip = (TextInputLayout) findViewById(R.id.text_input_myDetailsCityZipLayout);
        inputZip.setErrorEnabled(false);
        inputUSN1 = (TextInputLayout) findViewById(R.id.text_input_PaymentStateNbrLayoutnbr);
        inputUSN1.setErrorEnabled(false);
        inputUSN1.setHintEnabled(false);
        inputPhone = (TextInputLayout) findViewById(R.id.text_input_paymentBillingLayout);
        inputPhone.setErrorEnabled(false);


        inputUSN2 = (TextInputLayout) findViewById(R.id.text_input_PaymentStateNbr1Layoutnbr);
        inputUSN2.setErrorEnabled(false);
        inputUSN2.setHintEnabled(false);
        inputCPhn = (TextInputLayout) findViewById(R.id.text_input_paymentContactLayout);
        inputCPhn.setErrorEnabled(false);
        inputEmail = (TextInputLayout) findViewById(R.id.text_input_updatePaymentEmail);
        inputEmail.setErrorEnabled(false);
        inputCEmail = (TextInputLayout) findViewById(R.id.text_input_updatePaymentConEmail);
        inputCEmail.setErrorEnabled(false);
        Ptermscon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, TermsAndConditions.class));
            }
        });

        PPaypolocy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, PaymentPolicyActivity.class));
            }
        });

        Pprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, PrivacyPolicyActivity.class));
            }
        });

        pimtoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this,NortonSecuredSealAcitivity.class));
            }
        });

        detailslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (layout1.isShown()) {
                    layout1.setVisibility(View.GONE);
                    parrowup.setVisibility(View.VISIBLE);
                    parrowdown.setVisibility(View.GONE);
                } else {
                    if (count < oneWayPojo.getSegmentArray().size()) {

                        layout1.setVisibility(View.VISIBLE);
                        parrowup.setVisibility(View.GONE);
                        parrowdown.setVisibility(View.VISIBLE);

                        LayoutInflater layoutInflater = LayoutInflater.from(PaymentActivity.this);
                        View v = layoutInflater.inflate(R.layout.custom_extend_details, null);

                        count = count + 1;

                        ETRdepart = (TextView) v.findViewById(R.id.tv_SinglePriceDetailsExDepart);
                        EtRsave = (TextView) v.findViewById(R.id.tv_bgsaveexflexibleDate);
                        ETRdate = (TextView) v.findViewById(R.id.text_input_paymentexpiredLayout);
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

                        layout1.addView(v);
                    }
                }
            }
        });


        travelcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (travelcheck.isChecked())
                    Toast.makeText(PaymentActivity.this, "checked", Toast.LENGTH_LONG).show();
            }
        });
      /*  parrowdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setVisibility(View.GONE);
                parrowup.setVisibility(View.VISIBLE);
                parrowdown.setVisibility(View.GONE);
            }
        });
*/
        cvvImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(PaymentActivity.this);
                dialog.setTitle("For your protection, we require a credit card verification number for all purchases made online with a" +
                        "Visa, MasterCard, American Express, Diners Club, Discover or Carte Blanche Card.")
                        .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which){
                                dialog.dismiss();
                                finish();
                            }

                        });
                dialog.create().show();
            }
        });


        impay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
            }
        });

        pbottomim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(PaymentActivity.this,PriceDetails.class));
            }
        });
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
            }
        });
        paymentaAddcardTye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(PaymentActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PaymentActivity.this);
                alertDialogBuilder.setView(promptView1);

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                states_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "addcardtype";
                cardType.clear();
                cardType.add("VISA");
                cardType.add("Master Card");
                cardType.add("American Express");
                cardType.add("Diners Club");
                cardType.add("Discover");
                cardType.add("Clear Blanche");

                mAdapter = new RecyclerTitlesAdapter(PaymentActivity.this, cardType);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                states_list.setLayoutManager(mLayoutManager);
                states_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(PaymentActivity.this);
                states_list.setAdapter(mAdapter);
            }
        });


        punited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(PaymentActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PaymentActivity.this);
                alertDialogBuilder.setView(promptView1);

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                states_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "countries";
                UStates_Array.clear();
                UStates_Array.add("India");
                UStates_Array.add("SriLanka");
                UStates_Array.add("Bangladesh");
                UStates_Array.add("Nepal");
                UStates_Array.add("USA");
                mAdapter = new RecyclerTitlesAdapter(PaymentActivity.this, UStates_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                states_list.setLayoutManager(mLayoutManager);
                states_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(PaymentActivity.this);
                states_list.setAdapter(mAdapter);
            }
        });
        psates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(PaymentActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PaymentActivity.this);
                alertDialogBuilder.setView(promptView1);

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                states_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "states";
                States_Array.clear();
                States_Array.add("A.P");
                States_Array.add("Telangana");
                States_Array.add("Karnataka");
                States_Array.add("Maharastra");
                States_Array.add("Delhi");
                mAdapter = new RecyclerTitlesAdapter(PaymentActivity.this, States_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                states_list.setLayoutManager(mLayoutManager);
                states_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(PaymentActivity.this);
                states_list.setAdapter(mAdapter);
            }
        });
        psusnbr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(PaymentActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PaymentActivity.this);
                alertDialogBuilder.setView(promptView1);

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                states_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "USn1";
                USN1_Array.clear();


                USN1_Array.add("+01(US)");
                USN1_Array.add("+01(CA)");
                USN1_Array.add("+54(AR)");
                USN1_Array.add("+61(AU)");
                USN1_Array.add("+01(AI)");
                USN1_Array.add("+43(AT)");
                mAdapter = new RecyclerTitlesAdapter(PaymentActivity.this, USN1_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                states_list.setLayoutManager(mLayoutManager);
                states_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(PaymentActivity.this);
                states_list.setAdapter(mAdapter);
            }
        });
        pexpidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
                LayoutInflater layoutInflater = LayoutInflater.from(PaymentActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.date_picker, null);
                btnChangeDate = (Button)promptView1.findViewById(R.id.btnChangeDate);
                setCurrentDateOnView();
                addListenerOnButton();
                return;


            }
        });

        psusnbr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(PaymentActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PaymentActivity.this);
                alertDialogBuilder.setView(promptView1);

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                states_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                spinnerKey = "USn2";
                USN2_Array.clear();
                USN2_Array.add("+01(US)");
                USN2_Array.add("+01(CA)");
                USN2_Array.add("+54(AR)");
                USN2_Array.add("+61(AU)");
                USN2_Array.add("+01(AI)");
                USN2_Array.add("+43(AT)");
                mAdapter = new RecyclerTitlesAdapter(PaymentActivity.this, USN2_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                states_list.setLayoutManager(mLayoutManager);
                states_list.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(PaymentActivity.this);
                states_list.setAdapter(mAdapter);
            }
        });
        peditname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(PaymentActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.edit_names, null);
                states_list = (RecyclerView) promptView1.findViewById(R.id.titleslist);

                edittravel=(EditText)promptView1.findViewById(R.id.et_RevieweditLayout);
                editfName = (EditText)promptView1.findViewById(R.id.et_travellereditFirstnameLayout);
                editmName = (EditText)promptView1.findViewById(R.id.et_travellermiddleeditNameLayout);
                editlName = (EditText)promptView1.findViewById(R.id.et_RevieweditLastLayout);

                edit_edTravel=(TextInputLayout)promptView1.findViewById(R.id.text_input_travellereditAdditionalLayout);
                edit_fName=(TextInputLayout)promptView1.findViewById(R.id.text_input_travellereditFirstnameLayout);
                edit_MName=(TextInputLayout)promptView1.findViewById(R.id.text_input_travellereditMiddleNameLayout);
                edit_lName=(TextInputLayout)promptView1.findViewById(R.id.text_input_travellerEditLastLayout);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PaymentActivity.this);
                alertDialogBuilder.setView(promptView1);
                alertDialogBuilder.setCancelable(false);


                edittravel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LayoutInflater layoutInflater = LayoutInflater.from(PaymentActivity.this);
                        View promptView1 = layoutInflater.inflate(R.layout.rec_title, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PaymentActivity.this);
                        alertDialogBuilder.setView(promptView1);

                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        spinnerKey = "edit";
                        editName.clear();
                        editName.add("traveler1");
                        editName.add("traveler2");
                        states_list = (RecyclerView) promptView1.findViewById(R.id.titlelist);
                        mAdapter = new RecyclerTitlesAdapter(PaymentActivity.this, editName);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        states_list.setLayoutManager(mLayoutManager);
                        states_list.setItemAnimator(new DefaultItemAnimator());
                        mAdapter.setOnSecurityQnClickListener(PaymentActivity.this);
                        states_list.setAdapter(mAdapter);

                    }
                });


                alertDialogBuilder.setMessage("Edit names")
                        .setPositiveButton("Applay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });

                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                theButton.setOnClickListener(new CustomListener(alertDialog));
            }

        });
        //    alert.setMessage("Apply");


    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.paymentToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Payment");
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
                startActivity(new Intent(PaymentActivity.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(PaymentActivity.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(PaymentActivity.this);
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


    public void setCurrentDateOnView() {

        // tvDisplayDate = (TextView) findViewById(R.id.tvDate);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        pexpidate.setText(new StringBuilder()
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

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                DatePickerDialog _date =   new DatePickerDialog(this, datePickerListener, mYear,mMonth,
                        mDay){
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        if (year < mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (monthOfYear < mMonth && year == mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                            view.updateDate(mYear, mMonth, mDay);

                    }
                };
                return _date;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            mYear = selectedYear;
            mMonth = selectedMonth;
            mDay = selectedDay;

            // set selected date into textview
            pexpidate.setText(new StringBuilder().append(mMonth + 1)
                    .append("-").append(mDay).append("-").append(mYear)
                    .append(" "));

        }
    };

    public void Validation() {
        strcardNbr = pcardnbr.getText().toString();
        strNOCard = pnamecard.getText().toString();
        strExDate = pexpidate.getText().toString();
        strCVV = edcvv.getText().toString();
        strAdrees1 = paddess1.getText().toString();
        strAddress2 = paddress2.getText().toString();
        strCity = pcity.getText().toString();
        strUnitedSt = punited.getText().toString();
        strState = psates.getText().toString();
        strZip = pzip.getText().toString();
        strUSn1 = psusnbr1.getText().toString();
        strBPhone = psbillng.getText().toString();
        strUSn2 = psusnbr2.getText().toString();
        strCPhone = pcontact.getText().toString();
        strEmail = pemail.getText().toString();
        strCEmail = pemailcfm.getText().toString();

        inputCardNo.setErrorEnabled(false);
        inputNameOC.setErrorEnabled(false);
        inputEdate.setErrorEnabled(false);
        inputCVV.setErrorEnabled(false);
        inputAddr1.setErrorEnabled(false);

        inputCity.setErrorEnabled(false);
        inputUSt.setErrorEnabled(false);
        inputSt.setErrorEnabled(false);
        inputZip.setErrorEnabled(false);
        inputUSN1.setErrorEnabled(false);
        inputPhone.setErrorEnabled(false);
        inputUSN2.setErrorEnabled(false);
        inputCPhn.setErrorEnabled(false);
        inputEmail.setErrorEnabled(false);
        inputCEmail.setErrorEnabled(false);

        if (strcardNbr.length() == 0 || strcardNbr.length() == ' ') {
            inputCardNo.setError(" Please provide card number");
            inputCardNo.setErrorEnabled(true);
        }
        else if (strNOCard.length() == 0 || strNOCard.length() == ' ') {
            inputNameOC.setErrorEnabled(true);
            inputNameOC.setError("Please provide card holder name");
        } else if (strExDate.length() == 0 || strExDate.length() == ' ') {
            inputEdate.setError("Please select the card expiration date ");
            inputEdate.setErrorEnabled(true);
        } else if (strCVV.length() == 0 || strCVV.length() == ' ') {
            inputCVV.setError("Please provide card verification number");
            inputCVV.setErrorEnabled(true);
        } else if (strAdrees1.length() == 0 || strAdrees1.length() == ' ') {
            inputAddr1.setError("Please enter billing address ");
            inputAddr1.setErrorEnabled(true);
        }/* else if (strAddress2.length() == 0 || strAddress2.length() == ' ') {
            inputAddr2.setError("Please enter address");
            inputAddr2.setErrorEnabled(true);
        } */else if (strCity.length() == 0 || strCity.length() == ' ') {
            inputCity.setError("Please provide a billing city.");
            inputCity.setErrorEnabled(true);
        } else if (strUnitedSt.length() == 0 || strUnitedSt.length() == ' ') {
            inputUSt.setErrorEnabled(true);
            inputUSt.setError("Please provide US state");
        } else if (strState.length() == 0 || strState.length() == ' ') {
            inputSt.setErrorEnabled(true);
            inputSt.setError("Please provide state");
        } else if (strZip.length() == 0 || strZip.length() == ' ') {
            inputZip.setErrorEnabled(true);
            inputZip.setError("Please provide billing zip");
        } else if (strUSn1.length() == 0 || strUSn1.length() == ' ') {
            inputUSN1.setError("Please provide US St Nbr1");
            inputUSN1.setErrorEnabled(true);
        } else if (strBPhone.length() == 0 || strBPhone.length() == ' ') {
            inputPhone.setError("Please provide mobile number");
            inputPhone.setErrorEnabled(true);
        } else if (strUSn2.length() == 0 || strUSn2.length() == ' ') {
            inputUSN2.setError("Please provide US St nbr");
            inputUSN2.setErrorEnabled(true);
        } else if (strCPhone.length() == 0 || strCPhone.length() == ' ') {
            inputCPhn.setError("Please provide  contact mobile number");
            inputCPhn.setErrorEnabled(true);
        } else if (strEmail.length() == 0 || strEmail.length() == ' ') {
            inputEmail.setError("Please provide an email address");
            inputEmail.setErrorEnabled(true);
        } else if (strCEmail.length() == 0 || strCEmail.length() == ' ') {
            inputCEmail.setError("Please confirm  email");
            inputCEmail.setErrorEnabled(true);
        }


    }
    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;

        public CustomListener(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            // put your code here


            str_editFname = editfName.getText().toString();
            str_editMName = editmName.getText().toString();
            str_editLName = editlName.getText().toString();

            edit_edTravel.setErrorEnabled(false);
            edit_fName.setErrorEnabled(false);
            edit_MName.setErrorEnabled(false);
            edit_lName.setErrorEnabled(false);

            if (str_editFname.length() == 0 || str_editFname.length() == ' ') {
                edit_fName.setError(" Please Enter Promo code");
                edit_fName.setErrorEnabled(true);

            }
            else if (str_editMName.length() == 0 || str_editMName.length() == ' ') {
                edit_MName.setError(" Please Enter Promo code");
                edit_MName.setErrorEnabled(true);
            }
            else if (str_editLName.length() == 0 || str_editLName.length() == ' ') {
                edit_lName.setError(" Please Enter Promo code");
                edit_lName.setErrorEnabled(true);
            }

            else {
                dialog.dismiss();
            }
        }
    }

    @Override
    public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException {
//        Toast.makeText(PaymentActivity.this,position,Toast.LENGTH_LONG).show();
        if (spinnerKey.equals("countries")) {
            punited.setText(UStates_Array.get(position));
            alertDialog.dismiss();
        }
        else if (spinnerKey.equals("states")) {
            psates.setText(States_Array.get(position));
            alertDialog.dismiss();
        }
        else if (spinnerKey.equals("USn1")){
            psusnbr1.setText(USN1_Array.get(position));
            alertDialog.dismiss();
        }
        else if(spinnerKey.equals("USn2"))
        {
            psusnbr2.setText(USN2_Array.get(position));
            alertDialog.dismiss();
        }
        else if(spinnerKey.equals("edit"))
        {
            edittravel.setText(editName.get(position));
            alertDialog.dismiss();
        }
        else if (spinnerKey.equals("addcardtype"))
        {
            paymentaAddcardTye.setImageResource(myImageList[position]);
            alertDialog.dismiss();
        }


    }
}