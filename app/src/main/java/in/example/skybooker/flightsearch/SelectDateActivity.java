package in.example.skybooker.flightsearch;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.fragments.oneway.OneWayFragment;
import in.example.skybooker.flightsearch.fragments.roundtrip.RoundTripFragment;

public class SelectDateActivity extends AppCompatActivity {
    CalendarView calendar;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID=1;
    // variables to save user selected date and time
    public  int year,month,day,hour,minute;
    // declare  the variables to Show/Set the date and time when Time and  Date Picker Dialog first appears
    private int mYear, mMonth, mDay,mHour,mMinute;
    String key="",dateKey=" ",multicityDateKey="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);
        currentDate();
        showDialog(DATE_DIALOG_ID);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            key = bundle.getString("fragment");
            dateKey=bundle.getString("date");
            multicityDateKey=bundle.getString("multicityDate");
            Log.i("Select date key",key+"");
        }


    }

    @Override
    public Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // create a new DatePickerDialog with values you want to show
                return new DatePickerDialog(SelectDateActivity.this,mDateSetListener,mYear, mMonth, mDay);
           /* case TIME_DIALOG_ID:
                // create a new TimePickerDialog with values you want to show
                return new TimePickerDialog(this,mTimeSetListener, mHour, mMinute, false);*/
        }
        return null;
    }

    // Register  DatePickerDialog listener
    public DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        // the callback received when the user "sets" the Date in the DatePickerDialog
        @SuppressWarnings("deprecation")
        public void onDateSet(DatePicker view, int yearSelected, int monthOfYear, int dayOfMonth) {
            year = yearSelected;
            month = monthOfYear+1;
            day = dayOfMonth;
            String selectedDate = String.format("%02d",day)+"-"+String.format("%02d",month)+"-"+year;
            validation(selectedDate);
            // showDialog(TIME_DIALOG_ID);
            // Toast.makeText(getApplicationContext(), "day "+day+" month  "+month+" year  "+year, Toast.LENGTH_LONG).show();
        }
    };

    public void currentDate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
    }

    public void validation(String userdate){
        currentDate();
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date pickerdate = null;
        Date systemdate  = null;
        //String user_date = day + "-" + (month) + "-" + year;
        try{
            pickerdate = formatter.parse(userdate);
            String str = mDay+"-"+(mMonth+1)+"-"+mYear;
            systemdate = formatter.parse(str);
        }catch (ParseException e){
            e.printStackTrace();
        }

        //formatter.parse(userdate);
        SimpleDateFormat dayFormater = new SimpleDateFormat("MMM-EEE-dd");
        String day=dayFormater.format(pickerdate);
        StringTokenizer tokanizer=new StringTokenizer(day,"-");
        Log.i("dateformat",dayFormater.format(pickerdate)+"");
        if(pickerdate.after(systemdate)){

            if (key.equals("oneway")) {
                Log.i("oneway","entered into oneway loop");
                OneWayFragment.tvDay.setText(tokanizer.nextToken()+"\n"+tokanizer.nextToken());
                OneWayFragment.tvChooseDate.setText(tokanizer.nextToken());
                finish();
            } else if (key.equals("roundtrip")) {

                Log.i("roundtrip","entered into roundtrip loop");
                if(dateKey.equals("departure")) {
                    Log.i("depature","depart");
                    RoundTripFragment.tvDay.setText(tokanizer.nextToken()+"\n"+tokanizer.nextToken());
                    RoundTripFragment.tvChooseDate.setText(tokanizer.nextToken());
                    //RoundTripFragment.tvChooseDate.setText(userdate);
                    returnDate();
                }else if(dateKey.equals("return")){
                    Log.i("return","return");
                    RoundTripFragment.tvReturnDay.setText(tokanizer.nextToken()+"\n"+tokanizer.nextToken());
                    RoundTripFragment.tvReturnDate.setText(tokanizer.nextToken());
                    // RoundTripFragment.tvReturnDate.setText(userdate);
                    finish();
                }

                //MultiCityFragment.
            } else if (key.equals("multicity")) {
              /*  if(multicityDateKey.equals("flight1")){
                    MultiCityFragment.tv_CF1Day.setText(userdate);
                    finish();
                }
                else if(multicityDateKey.equals("flight2")){
                    MultiCityFragment.tv_CF2Day.setText(userdate);
                    finish();
                }else if(multicityDateKey.equals("flightOptional")){
                    MultiCityFragment.tv_CFlightDate.setText(userdate);
                    finish();
                }*/
            }


        }else if(pickerdate.before(systemdate)){ //less0
            new AlertDialog.Builder(SelectDateActivity.this)
                    .setTitle("Alert!")
                    .setMessage("Travelling date should be greater than Current date")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            dialog.dismiss();
                            showDialog(DATE_DIALOG_ID);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else if(systemdate.equals(pickerdate)){
            if (key.equals("oneway")) {
                Log.i("oneway","entered into oneway loop");
                OneWayFragment.tvDay.setText(tokanizer.nextToken()+"\n"+tokanizer.nextToken());
                OneWayFragment.tvChooseDate.setText(tokanizer.nextToken());
                finish();
            } else if (key.equals("roundtrip")) {
                // RoundTripFragment.tvChooseDate.setText(userdate);
                Log.i("roundtrip","entered into roundtrip loop");
                if(dateKey.equals("departure")) {
                    Log.i("depature","depart");
                    RoundTripFragment.tvChooseDate.setText(userdate);
                    returnDate();
                }else if(dateKey.equals("return")){
                    Log.i("return","return");
                    RoundTripFragment.tvReturnDate.setText(userdate);
                    finish();
                }
            }
            else if (key.equals("multicity")) {
                /*if(multicityDateKey.equals("flight1")){
                    MultiCityFragment.tv_CF1Day.setText(userdate);
                    finish();
                }
                else if(multicityDateKey.equals("flight2")){
                    MultiCityFragment.tv_CF2Day.setText(userdate);
                    finish();
                }else if(multicityDateKey.equals("flightOptional")){
                    MultiCityFragment.tv_CFlightDate.setText(userdate);
                    finish();
                }*/
            }
            //equal
            //OneWayFragment.tvChooseDate.setText(userdate);

        }
    }

    private void returnDate(){
        Intent intent = new Intent(SelectDateActivity.this, SelectDateActivity.class);
        intent.putExtra("fragment", key);
        intent.putExtra("date","return");
        // intent.putExtra("date","return");
        startActivity(intent);
        finish();
    }
}


