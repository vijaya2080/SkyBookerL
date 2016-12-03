package in.example.skybooker.flightsearch.relatedflights.review;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.example.skybooker.BestPriceGuarantee;
import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.flightsearch.relatedflights.payment.TravelAssist;
import in.example.skybooker.flightsearch.relatedflights.payment.TravelProtection;
import in.example.skybooker.flightsearch.relatedflights.travelers.TravellerActivity;
import in.example.skybooker.myaccount.billing.PrivacyPolicyActivity;
import in.example.skybooker.pojo.FlightSegmentPojo;
import in.example.skybooker.pojo.OneWayPojo;

public class ReviewActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolname,FTostop,FFromstop,FDate,FCost,FReview,FTravelers,FBook,FflightDetailsTitle,ATR,
            FFlightDesc,FtoTime,FfromTime,FflightDetailsToStop,FFlightdetailsFromStop,
            FtotalTime,FchangeAirport,FnightFlight,FairOparetoeFlight,FFlightDetails,
            TTravelSafeTitle,TTravelProtection,TTravelPCost,TtripCancel,TtripDelay,
            TtripInterruptT,TBaggedDelay,TTrvelAssist,TTravelACost,TTAssistance,TSavingCoupon,TPrearranged,TVisaAndPassport,
            PriceDetailsTitle,PSuccessmsg,PSuccesshint,PTrvelers,PBasePrice,PTaxesandFees,
            PSubTotal,PAdult,PBPrice,PTAndF,PSTotal,PTotalDiscount,PTDCost,PRewardPoints,
            PRPoints,PfareRules,PPromoCode,PTotalCharge,PTChargeRS,ETRdate,ETRdepart,EFTFlname,EtRsave,ETRairF,EFRairDesc,EFRairnbe,
            ETRstart,ERTariline,ETRttrip,ERTaoper,ETRairlineair,ETRaaie,text_add;
    Button Countinue;
    View includePrice;
    ImageView toolIcon,imToAndErom,imMultpleAirline,imExtend,imTravelProtection,TravelProtect,imTbalert,
            imTravelAssist,Travelassist,imSuccess,imITandF,imPriview,imTravelers,imBooks,imbottom,imexuparrow,imtripextra;

    RelativeLayout extendRlayout,Layout,customrl,customl1,customl2,customl3,custom4,custom5,custom6,RincludeView,flightsDetailsLay;
    LinearLayout dynamicLayout,customAddLay;
    AlertDialog.Builder alertDialogBuilder;
    String str_promo,key=" ",outputDate,inputDate;
    TextInputLayout promoCode;
    AlertDialog alertDialog;
    SimpleDateFormat dayFormater,format;


    EditText edpromo_code;
    CheckBox imCheck,imCheck1;
    ArrayList<String> locationsArray;
    ArrayList<FlightSegmentPojo> segArray;
    Boolean click=true;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        setToolBar();

       /* RelativeLayout toolbarlayout = (RelativeLayout) findViewById(R.id.SingleFlightToolBar);
        toolname = (TextView) toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon = (ImageView) toolbarlayout.findViewById(R.id.toolBackImg);
        toolname.setText("Review");
*/

        //oneWayPojo= (OneWayPojo) getIntent().getSerializableExtra("oneWayFlightRv");
        final OneWayPojo oneWayPojo = ((OneWayPojo)getIntent().getExtras().getBinder("oneWayFlightRv"));
        Log.i("OnewayObject",oneWayPojo+"");

        segArray = new ArrayList<>();
        segArray = oneWayPojo.getSegmentArray();



        alertDialogBuilder = new AlertDialog.Builder(ReviewActivity.this);

        customrl = (RelativeLayout) findViewById(R.id.layoutWeightTProtectincludeRL);
        imCheck = (CheckBox)customrl.findViewById(R.id.imSingleFlightTravelsafecheck);
        imTravelProtection = (ImageView)customrl.findViewById(R.id.imSingleFlightReviewTravelsafe);
        TravelProtect = (ImageView)customrl.findViewById(R.id.imSingleFlightReviewTravelsafei);

        customl1 = (RelativeLayout) findViewById(R.id.layoutWeightRL1);
        customl2 = (RelativeLayout) findViewById(R.id.layoutWeightRL2);

        customl3 = (RelativeLayout) findViewById(R.id.layoutWeightTAssistRL3);

        imTravelAssist = (ImageView) customl3.findViewById(R.id.imSingleFlightTravelsafeAssist);
        Travelassist = (ImageView) customl3.findViewById(R.id.imSingleFlightTravelsafeassisti);
        imCheck1 = (CheckBox) customl3.findViewById(R.id.imSingleFlightTravelsafecheck1);

        custom4 = (RelativeLayout) findViewById(R.id.weightReviewRL4);
        custom5 = (RelativeLayout) findViewById(R.id.weightReviewRL5);
       // custom6 = (RelativeLayout) findViewById(R.id.layoutWeightRL);

        extendRlayout = (RelativeLayout) findViewById(R.id.flightDetailslayout1);

        // Layout=(RelativeLayout)goneLayout.findViewById(R.id.layout2);

        PTrvelers = (TextView) custom4.findViewById(R.id.tv_SingleFlightTrvelsDetails);
        PBasePrice = (TextView)custom4 .findViewById(R.id.tv_SingleFlightTrvelsPrice);
        PTaxesandFees = (TextView)custom4. findViewById(R.id.tv_SingleFlightTrvelsPriceTax);
        PSubTotal = (TextView) custom4.findViewById(R.id.tv_SingleFlightTrvelsPricesubTotal);

        PAdult = (TextView)custom5. findViewById(R.id.tv_SingleFlightTrvelsDetailsNames);
        PBPrice = (TextView)custom5. findViewById(R.id.tv_SingleFlightTrvelsPricecost);
        PBPrice.setText(oneWayPojo.getAdult());
        PTAndF = (TextView)custom5. findViewById(R.id.tv_SingleFlightTrvelsPriceTaxCost);
        PTAndF.setText(oneWayPojo.getAdultTotalTax());
        PSTotal = (TextView)custom5. findViewById(R.id.tv_SingleFlightTrvelsPricesubTotalCost);
        PSTotal.setText(oneWayPojo.getAdultToatalFare());

        FTostop = (TextView) findViewById(R.id.tv_toSingleFlightname);
        FFromstop = (TextView) findViewById(R.id.tv_fromsingleFlightname);

        FflightDetailsToStop = (TextView) findViewById(R.id.tv_toFlightStopName);
        FFlightdetailsFromStop = (TextView) findViewById(R.id.tv_fromFlightStopName);


        for(int i=0;i<oneWayPojo.getOnewayHP().size();i++){

            locationsArray=new ArrayList<>();
            locationsArray = oneWayPojo.getOnewayHP().get(i);

            for (int j=0;j<locationsArray.size();j++){

                FTostop.setText(locationsArray.get(0));
                FflightDetailsToStop.setText(locationsArray.get(0));
                Log.i("From Stop", locationsArray.get(0) + " ");

                FFromstop.setText(locationsArray.get(locationsArray.size()-1));
                FFlightdetailsFromStop.setText(locationsArray.get(locationsArray.size()-1));
                Log.i("To Stop", locationsArray.get(locationsArray.size()-1) + " ");
            }

        }


        FDate = (TextView) findViewById(R.id.tv_SingleFlightDate);


        for (int k = 0; k < segArray.size() ;k++){

            inputDate = segArray.get(k).getDepartureDate();

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

        FCost = (TextView) findViewById(R.id.tv_SingleElightCost);
        FCost.setText(oneWayPojo.getTotalFare());

        RincludeView = (RelativeLayout) findViewById(R.id.toAndFromSingleFlightRL);
        FReview = (TextView) RincludeView.findViewById(R.id.tv_fromCityFlight);
        FTravelers = (TextView) RincludeView.findViewById(R.id.tv_toCitySingleFlight);
        FBook = (TextView) RincludeView.findViewById(R.id.tv_booktoCitySingleFlight);
       // ATR = (TextView) findViewById(R.id.tv_SinglePriceDetailsExAirnumber);
        text_add = (TextView) findViewById(R.id.tv_ReviewmychangeBaggage);


        FflightDetailsTitle = (TextView) findViewById(R.id.tv_FlightDetails);
        FFlightDesc = (TextView) findViewById(R.id.SingleFlightDetails);
        FtoTime = (TextView) findViewById(R.id.tv_toFlightTimes);
        FfromTime = (TextView) findViewById(R.id.tv_fromFlightTimes);

        for (int k = 0 ; k < segArray.size(); k++){

            FtoTime.setText(segArray.get(k).getDepartureTime());
            Log.i("Departure array",(segArray.get(k).getDepartureTime())+"");

            FfromTime.setText(segArray.get(k).getArrivalTime());
            Log.i("Arrival array",(segArray.get(k).getArrivalTime())+"");
        }



        FtotalTime = (TextView) findViewById(R.id.tv_fromFlightJournyTime);
        FtotalTime.setText(oneWayPojo.getDurationStr());

        FchangeAirport = (TextView) findViewById(R.id.tv_changeAitport);
        FnightFlight = (TextView) findViewById(R.id.tv_overniteFlight);
        FairOparetoeFlight = (TextView) findViewById(R.id.tv_overriteFlightdesc);
        FFlightDetails = (TextView) findViewById(R.id.tv_overriteFlightDetails);

        TTravelSafeTitle = (TextView) findViewById(R.id.tv_SingleTravelSafe);
        TTravelProtection = (TextView) customrl.findViewById(R.id.tv_SingleFlightTrvelSafe);
        TTravelPCost = (TextView) customrl.findViewById(R.id.tv_SingleFlightTrvelSafecost);
        TtripCancel = (TextView) customl1.findViewById(R.id.tv_SingleFlightTrveltripcancel);
        TtripDelay = (TextView) customl1.findViewById(R.id.tv_SingleFlightTrveltripDelay);
        TtripInterruptT = (TextView) customl2.findViewById(R.id.tv_SingleFlightTrveltripInterrupt);
        TBaggedDelay = (TextView) customl2.findViewById(R.id.tv_SingleFlightTrveltripBaggageDelay);
        TTrvelAssist = (TextView) customl3.findViewById(R.id.tv_SingleFlightTrvelSafeAssist);
        TTravelACost = (TextView) customl3.findViewById(R.id.tv_SingleFlightTrvelSafeassistcost);
        // TTAssistance = (TextView) customl1.findViewById(R.id.tv_SingleFlightTrveltripcancel);
        // TSavingCoupon = (TextView) customl1.findViewById(R.id.tv_SingleFlightTrveltripDelay);
        // TPrearranged = (TextView) customl2.findViewById(R.id.tv_SingleFlightTrveltripInterrupt);
        // TVisaAndPassport = (TextView) customl2.findViewById(R.id.tv_SingleFlightTrveltripBaggageDelay);

        PriceDetailsTitle = (TextView) findViewById(R.id.tv_SinglePriceDetails);
        PSuccessmsg = (TextView) findViewById(R.id.tv_SingleFlightPriceSuccess);
        PSuccesshint = (TextView) findViewById(R.id.tv_SingleFlightPriceSuccessGuaranteed);

        PTotalDiscount = (TextView) findViewById(R.id.tv_SingleFlighttotalDiscount);
        PTDCost = (TextView) findViewById(R.id.tv_SingleFlightPriceCost);
        PTDCost.setText(oneWayPojo.getTotalFareTax());

        PRewardPoints = (TextView) findViewById(R.id.tv_SingleFlightReward);
        PRPoints = (TextView) findViewById(R.id.tv_SingleFlightrewordPoints);
        PfareRules = (TextView) findViewById(R.id.tv_SingleFlightFairRules);
        PPromoCode = (TextView) findViewById(R.id.tv_SingleFlightPromoCode);
        PTotalCharge = (TextView) findViewById(R.id.tv_SingleFlightTotalCharge);

        PTChargeRS = (TextView) findViewById(R.id.tv_SingleFlightTotalChargeCost);
        PTChargeRS.setText(oneWayPojo.getTotalFare());

        Countinue = (Button) findViewById(R.id.Bt_SingleFlightCountinue);

        imToAndErom = (ImageView) findViewById(R.id.im_toandfromflight);
        imMultpleAirline = (ImageView) findViewById(R.id.imSingleFlightMulAir);
        imExtend = (ImageView) findViewById(R.id.ElightDetailsExtension);

        imexuparrow = (ImageView) findViewById(R.id.ElightDetailsExtensionup);
        imSuccess = (ImageView) findViewById(R.id.imSingleFlightPricedetails);
        imITandF = (ImageView) findViewById(R.id.imSingleFlightPricedetailscost);

        flightsDetailsLay=(RelativeLayout)findViewById(R.id.ReviewRelativeExLayout);

        imPriview = (ImageView) RincludeView.findViewById(R.id.reviewCitySingleFlight);
        imTravelers = (ImageView) RincludeView.findViewById(R.id.travelersCitySingleFlight);
        imBooks = (ImageView) RincludeView.findViewById(R.id.booktoCitySingleFlight);
        imTbalert=(ImageView)findViewById(R.id.im_rFlightBalert);
        imbottom = (ImageView) findViewById(R.id.im_relatedBaggedim);
        imtripextra=(ImageView)findViewById(R.id.im_TripExtrasReview);

        Countinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Bundle b = new Bundle();
                b.putBinder("reviewActivity", oneWayPojo);
                startActivity(new Intent(ReviewActivity.this, TravellerActivity.class).putExtras(b));
                Log.d("Sent", "original object=" + oneWayPojo);

               // startActivity(new Intent(ReviewActivity.this, TravellerActivity.class));
            }
        });
        text_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "text clicked", Toast.LENGTH_SHORT).show();
            }
        });

       /* toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
        TravelProtect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReviewActivity.this, TravelProtection.class));
            }
        });
        imITandF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReviewActivity.this,TaxesAndFees.class));
            }
        });
        imTravelProtection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imCheck.isChecked())
                    imCheck.setChecked(false);
                else
                    imCheck.setChecked(true);
            }
        });
        imTravelAssist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imCheck1.isChecked())
                    imCheck1.setChecked(false);
                else
                    imCheck1.setChecked(true);
            }
        });


        imTbalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ReviewActivity.this);
                dialog.setTitle("Depart:HYD-RJA The selected airlines don't have a baggege agreement." +
                        "When you land at New Delhi IGI(DEL),you bags and check them in again with Jet .")
                        .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which){
                                dialog.dismiss();

                            }

                        });
                dialog.create().show();
            }
        });

      /*  imExtend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extendRlayout.setVisibility(View.GONE);
                goneLayout.setVisibility(View.VISIBLE);
                imexuparrow.setVisibility(View.VISIBLE);
                imExtend.setVisibility(View.GONE);
            }
        });
        imexuparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goneLayout.setVisibility(View.GONE);
                extendRlayout.setVisibility(View.VISIBLE);
                imexuparrow.setVisibility(View.GONE);
                imExtend.setVisibility(View.VISIBLE);
            }
        });*/

        flightsDetailsLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dynamicLayout = (LinearLayout) findViewById(R.id.flightDetailslayoutEx2);

               /* if(goneLayout.isShown()){
                    goneLayout.setVisibility(View.GONE);
                    extendRlayout.setVisibility(View.VISIBLE);
                    imExtend.setVisibility(View.VISIBLE);
                    imexuparrow.setVisibility(View.GONE);
                }
                else {*/
                if ( click ) {
                    if(count < oneWayPojo.getSegmentArray().size()) {
                        dynamicLayout.setVisibility(View.VISIBLE);
                        extendRlayout.setVisibility(View.GONE);
                        imExtend.setVisibility(View.GONE);
                        imexuparrow.setVisibility(View.VISIBLE);

                        LayoutInflater layoutInflater = LayoutInflater.from(ReviewActivity.this);
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
                        ETRaaie = (TextView) v.findViewById(R.id.tv_overriteExFlightdesc);

                        EFRairnbe.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "AirNumber", Toast.LENGTH_SHORT).show();
                            }
                        });

                        dynamicLayout.addView(v);
                    }
                    click = false;
                }
                else {
                    dynamicLayout.setVisibility(View.GONE);
                    extendRlayout.setVisibility(View.VISIBLE);
                    imExtend.setVisibility(View.VISIBLE);
                    imexuparrow.setVisibility(View.GONE);

                    click = true;
                }
            }

            //}
        });

        PfareRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(ReviewActivity.this,FareRules.class));
            }
        });
        imCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // finish();
            }
        });

        imCheck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
            }
        });

        imtripextra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
            }
        });

        PPromoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(ReviewActivity.this);
                View promptView1 = layoutInflater.inflate(R.layout.promo_code, null);
                edpromo_code = (EditText) promptView1.findViewById(R.id.et_ReviewPromoCodeLayout);
                promoCode = (TextInputLayout) promptView1.findViewById(R.id.text_input_travellerAdditionalPromoLayout);


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ReviewActivity.this);
                alertDialogBuilder.setView(promptView1);
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setMessage("Promo Code")
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
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

        PSuccesshint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(ReviewActivity.this,BestPriceGuarantee.class));
            }
        });

        Travelassist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReviewActivity.this, TravelAssist.class));
            }
        });
        imbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReviewActivity.this,BaggedFees.class));
            }
        });





        //toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.SingleFlightToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Review");
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
                startActivity(new Intent(ReviewActivity.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(ReviewActivity.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(ReviewActivity.this);
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

    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;
        public CustomListener(Dialog dialog) {
            this.dialog = dialog;
        }
        @Override
        public void onClick(View v) {
            // put your code here

            str_promo=edpromo_code.getText().toString();
            promoCode.setErrorEnabled(false);

            if (str_promo.length() == 0 || str_promo.length() == ' ') {
                promoCode.setError(" Please Enter Promo code");
                promoCode.setErrorEnabled(true);

            } else{
                dialog.dismiss();
            }
        }
    }
}
