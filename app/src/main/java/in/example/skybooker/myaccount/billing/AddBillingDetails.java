package in.example.skybooker.myaccount.billing;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.myaccount.RecyclerTitlesAdapter;

public class AddBillingDetails extends AppCompatActivity implements RecyclerTitlesAdapter.OnSecurityQnClickListener {

    AlertDialog alert;
    TextView toolname;
    ImageView toolIcon,img_veriSign,img_card;
    CheckBox checkBox;
    Button btAddCard;
    RecyclerView spinnerRv;
    RecyclerTitlesAdapter mAdapter;
    static final int DATE_DIALOG_ID = 0;
    private int mYear, mMonth, mDay;
    Button btnChangeDate;
    int[] myImageList;

    Spinner countrySpinner,stateSpinner,codeSpinner;
    TextInputLayout addBillCardNoLayout,addBillNameOnCardLayout,addBillCardAliasNameLayout,addBillCardExpiryLayout,addBillAddress1Layout,
                     addBillAddress2Layout,addBillCityLayout,addBillZipCodeLayout,addBillPhoneLayout,addBillCountryLayout,addBillStateLayout,
                    addBillCodeLayout;
    EditText et_addBillCardNoLayout,et_addBillNameOnCardLayout,et_addBillCardAliasNameLayout,et_addBillCardExpiryLayout,et_addBillAddress1,
             et_addBillAddress2,et_addBillCity,et_addBillZipCode,et_addBillPhone,et_addBillCountryLayout,et_addBillStateLayout,et_addBillPhoneCodeLayout;
    String strAddBillCardNo,strAddBillNameOnCard,strAddBillCardAliasName,strAddBillCardExpiry,strAddBillAddress1,
            strAddBillAddress2,strAddBillCity,strAddBillZipCode,strAddBillPhone,strDddBillCountry,strAddBillState, strAddBillCodet;
    TextView tv_privacy,tv_payment,tv_veriSign;

    ArrayList<String> countryArray,states_Array,phoneCode_Array,cardType;
    String spinnerKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_billing_details);



        myImageList = new int[]{R.mipmap.visa1, R.mipmap.master,R.mipmap.aexpress,R.mipmap.club,
                R.mipmap.discover1,R.mipmap.cartle};




        setToolBar();

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        countryArray=new ArrayList<>();
        states_Array=new ArrayList<>();
        phoneCode_Array=new ArrayList<>();
        cardType=new ArrayList<>();
        checkBox=(CheckBox)findViewById(R.id.checkBoxAddBill);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked())
                    Toast.makeText(AddBillingDetails.this, "checked", Toast.LENGTH_LONG).show();
            }
        });

        img_card=(ImageView)findViewById(R.id.im_BillingNameOnCard);


        addBillCardNoLayout=(TextInputLayout)findViewById(R.id.text_input_updateBillingCardNo);
        addBillNameOnCardLayout=(TextInputLayout)findViewById(R.id.text_input_updateBillingNameOnCard);
        addBillCardAliasNameLayout=(TextInputLayout)findViewById(R.id.text_input_et_updateBillingCardAliasName);
        addBillCardExpiryLayout=(TextInputLayout)findViewById(R.id.text_input_updateBillingExpiry);
        addBillAddress1Layout=(TextInputLayout)findViewById(R.id.text_input_BillingInfoAddress1);
        addBillAddress2Layout=(TextInputLayout)findViewById(R.id.text_input_BillingInfoAddress2);
        addBillCityLayout=(TextInputLayout)findViewById(R.id.text_input_BillingInfoCity);
        addBillCountryLayout=(TextInputLayout)findViewById(R.id.text_input_BillingInfoCountries);
        addBillCountryLayout.setHintEnabled(false);
        addBillStateLayout=(TextInputLayout)findViewById(R.id.text_input_BillingInfoState);
        addBillStateLayout.setHintEnabled(false);
        addBillZipCodeLayout=(TextInputLayout)findViewById(R.id.text_input_BillingInfoZipCode);
        addBillCodeLayout=(TextInputLayout)findViewById(R.id.text_input_BillingInfoCode);
        addBillCodeLayout.setHintEnabled(false);
        addBillPhoneLayout=(TextInputLayout)findViewById(R.id.text_input_BillingInfoBillingPhone);

        et_addBillNameOnCardLayout=(EditText)findViewById(R.id.et_updateBillingNameOnCard);
        et_addBillCardNoLayout=(EditText)findViewById(R.id.et_updateBillingCardNo);
        et_addBillCardAliasNameLayout=(EditText)findViewById(R.id.et_updateBillingCardAliasName);
        et_addBillCardExpiryLayout=(EditText)findViewById(R.id.et_updateBillingExpiry);
        et_addBillAddress1=(EditText)findViewById(R.id.et_BillingInfoAddress1);
        et_addBillAddress2=(EditText)findViewById(R.id.et_BillingInfoAddress2);
        et_addBillCity=(EditText)findViewById(R.id.et_BillingInfoCity);
        et_addBillCountryLayout=(EditText)findViewById(R.id.et_input_BillingInfoCountries);
        et_addBillStateLayout=(EditText)findViewById(R.id.et_input_BillingInfoState);
        et_addBillZipCode=(EditText)findViewById(R.id.et_BillingInfoZipCode);
        et_addBillPhone=(EditText)findViewById(R.id.et_BillingInfoBillingPhone);
        et_addBillPhoneCodeLayout=(EditText)findViewById(R.id.et_BillingInfoCode);

        img_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(AddBillingDetails.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddBillingDetails.this);
                alertDialogBuilder.setView(promptView1);

                alert = alertDialogBuilder.create();
                alert.show();

                spinnerRv = (RecyclerView) promptView1.findViewById(R.id.titleslist);
                spinnerKey = "addcardtype";
                cardType.clear();
                cardType.add("VISA");
                cardType.add("Master Card");
                cardType.add("American Express");
                cardType.add("Diners Club");
                cardType.add("Discover");
                cardType.add("Clear Blanche");
                mAdapter = new RecyclerTitlesAdapter(AddBillingDetails.this, cardType);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                spinnerRv.setLayoutManager(mLayoutManager);
                spinnerRv.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(AddBillingDetails.this);
                spinnerRv.setAdapter(mAdapter);
            }
        });


        et_addBillCountryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(AddBillingDetails.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddBillingDetails.this);
                alertDialogBuilder.setView(promptView1);

                alert = alertDialogBuilder.create();
                alert.show();

                spinnerRv = (RecyclerView) promptView1.findViewById(R.id.titleslist);
                spinnerKey="countries";
                countryArray.clear();
                countryArray.add("India");
                countryArray.add("SriLanka");
                countryArray.add("Bangladesh");
                countryArray.add("Nepal");
                countryArray.add("USA");
                mAdapter = new RecyclerTitlesAdapter(AddBillingDetails.this, countryArray);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                spinnerRv.setLayoutManager(mLayoutManager);
                spinnerRv.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(AddBillingDetails.this);
                spinnerRv.setAdapter(mAdapter);
            }
        });
        et_addBillStateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(AddBillingDetails.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddBillingDetails.this);
                alertDialogBuilder.setView(promptView1);

                alert = alertDialogBuilder.create();
                alert.show();
                states_Array.clear();
                states_Array.add("AK-ALASKHA,US");
                states_Array.add("AK-ALBAMA,US");
                states_Array.add("AK-ARZONA,US");
                states_Array.add("AK-COLORANDO,US");

                spinnerRv = (RecyclerView) promptView1.findViewById(R.id.titleslist);

                spinnerKey="states";
                mAdapter = new RecyclerTitlesAdapter(AddBillingDetails.this, states_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                spinnerRv.setLayoutManager(mLayoutManager);
                spinnerRv.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(AddBillingDetails.this);
                spinnerRv.setAdapter(mAdapter);
            }
        });

        et_addBillPhoneCodeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(AddBillingDetails.this);
                View promptView1 = layoutInflater.inflate(R.layout.titles_grouplist, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddBillingDetails.this);
                alertDialogBuilder.setView(promptView1);

                alert = alertDialogBuilder.create();
                alert.show();
                phoneCode_Array.clear();
                phoneCode_Array.add("+01(US)");
                phoneCode_Array.add("+01(CA)");
                phoneCode_Array.add("+54(AR)");
                phoneCode_Array.add("+61(AU)");
                phoneCode_Array.add("+01(AI)");
                phoneCode_Array.add("+43(AT)");
                spinnerRv = (RecyclerView) promptView1.findViewById(R.id.titleslist);

                spinnerKey= "phonecode";
                mAdapter = new RecyclerTitlesAdapter(AddBillingDetails.this, phoneCode_Array);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                spinnerRv.setLayoutManager(mLayoutManager);
                spinnerRv.setItemAnimator(new DefaultItemAnimator());
                mAdapter.setOnSecurityQnClickListener(AddBillingDetails.this);
                spinnerRv.setAdapter(mAdapter);

            }
        });
        et_addBillCardExpiryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
                LayoutInflater layoutInflater = LayoutInflater.from(AddBillingDetails.this);
                View promptView1 = layoutInflater.inflate(R.layout.date_picker, null);
                btnChangeDate = (Button)promptView1.findViewById(R.id.btnChangeDate);
                setCurrentDateOnView();
                addListenerOnButton();
                return;

            }
        });


        tv_payment=(TextView)findViewById(R.id.tv_addBillPayment);
        tv_privacy=(TextView)findViewById(R.id.tv_addBillPrivacy);
        img_veriSign=(ImageView)findViewById(R.id.img_nortonSeal);

        tv_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddBillingDetails.this,PrivacyPolicyActivity.class));
            }
        });
        tv_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddBillingDetails.this,PaymentPolicyActivity.class));
            }
        });
        img_veriSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(AddBillingDetails.this,NortonSecuredSealAcitivity.class));
            }
        });



        btAddCard=(Button)findViewById(R.id.btBillingAdd);

        btAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCardValidation();
            }
        });

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.billingAddToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Billing Details");
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
                startActivity(new Intent(AddBillingDetails.this, TermsAndConditions.class));
                return true;
            case R.id.privacy:
                startActivity(new Intent(AddBillingDetails.this, PrivacyPolicyActivity.class));
                return true;
            case android.R.id.home:
                onBackPressed();    //Call the back button's method
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(AddBillingDetails.this);
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
        et_addBillCardExpiryLayout.setText(new StringBuilder()
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
            et_addBillCardExpiryLayout.setText(new StringBuilder().append(mMonth + 1)
                    .append("-").append(mDay).append("-").append(mYear)
                    .append(" "));

        }
    };

    private void addCardValidation() {
        strAddBillCardNo=et_addBillCardNoLayout.getText().toString();
        strAddBillNameOnCard=et_addBillNameOnCardLayout.getText().toString();
        strAddBillCardAliasName=et_addBillCardAliasNameLayout.getText().toString();
        strAddBillCardExpiry=et_addBillCardExpiryLayout.getText().toString();
        strAddBillAddress1=et_addBillAddress1.getText().toString();
        strAddBillAddress2=et_addBillAddress2.getText().toString();
        strAddBillCity=et_addBillCity.getText().toString();
        strAddBillState=et_addBillStateLayout.getText().toString();
        strAddBillZipCode=et_addBillZipCode.getText().toString();
        strAddBillPhone=et_addBillPhone.getText().toString();

        addBillCardNoLayout.setErrorEnabled(false);
        addBillNameOnCardLayout.setErrorEnabled(false);
       // addBillCardAliasNameLayout .setErrorEnabled(false);
        addBillCardExpiryLayout.setErrorEnabled(false);
        addBillAddress1Layout.setErrorEnabled(false);
       // addBillAddress2Layout.setErrorEnabled(false);
        addBillCityLayout.setErrorEnabled(false);
        addBillZipCodeLayout.setErrorEnabled(false);
        addBillPhoneLayout.setErrorEnabled(false);
        addBillCountryLayout.setErrorEnabled(false);
        addBillStateLayout.setErrorEnabled(false);


        if(strAddBillCardNo.length()==0 || strAddBillCardNo.length()==' '){
            addBillCardNoLayout.setError(" Please provide card number");
            addBillCardNoLayout.setErrorEnabled(true);
        }
        else if(strAddBillNameOnCard.length()==0 || strAddBillNameOnCard.length()==' '){
            addBillNameOnCardLayout.setErrorEnabled(true);
            addBillNameOnCardLayout.setError("Please provide card name");
        }
        /*else if(strAddBillCardAliasName.length()==0 ||strAddBillCardAliasName.length()==' '){
            addBillCardAliasNameLayout.setError("Please provide card name ");
            addBillCardAliasNameLayout.setErrorEnabled(true);
        }*/
        else if(strAddBillCardExpiry.length()==0 || strAddBillCardExpiry.length()==' '){
            addBillCardExpiryLayout.setError("Please provide card Expiry date");
            addBillCardExpiryLayout.setErrorEnabled(true);
        }
        else if(strAddBillAddress1.length()==0||strAddBillAddress1.length()==' ') {
            addBillAddress1Layout.setError("Please enter address ");
            addBillAddress1Layout.setErrorEnabled(true);
        }
       /* else if(strAddBillAddress2.length()==0||strAddBillAddress2.length()==' '){
            addBillAddress2Layout.setError("Please enter address");
            addBillAddress2Layout.setErrorEnabled(true);
        }*/
        else if(strAddBillState.length()==0||strAddBillState.length()==' '){
            addBillStateLayout.setErrorEnabled(true);
            addBillStateLayout.setError("Please provide state");
        }
        else if(strAddBillCity.length()==0||strAddBillCity.length()==' '){
            addBillCityLayout.setErrorEnabled(true);
            addBillCityLayout.setError("Please provide city name");
        }
        else if(strAddBillZipCode.length()==0||strAddBillZipCode.length()==' '){
            addBillZipCodeLayout.setError(" please provide zip code");
            addBillZipCodeLayout.setErrorEnabled(true);
        }
        else if(strAddBillPhone.length()==0||strAddBillPhone.length()==' '){
            addBillPhoneLayout.setError("Please provide mobile number");
            addBillPhoneLayout.setErrorEnabled(true);
        }
        else {

        }

    }

    @Override
    public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException {
        if (spinnerKey.equals("phonecode")){
            et_addBillPhoneCodeLayout.setText(phoneCode_Array.get(position));
            alert.dismiss();
        }else if(spinnerKey.equals("countries")){
            et_addBillCountryLayout.setText(countryArray.get(position));
            alert.dismiss();
        }else if(spinnerKey.equals("states")){
            et_addBillStateLayout.setText(states_Array.get(position));
            alert.dismiss();
        }
        else if (spinnerKey.equals("addcardtype"))
        {
            img_card.setImageResource(myImageList[position]);
            alert.dismiss();
        }

        alert.dismiss();
    }
}
