package in.example.skybooker.communication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import in.example.skybooker.R;

public class SliderFeedbackActivity extends AppCompatActivity {

    TextView toolname,tv_contact;
    ImageView toolIcon;
    TextInputLayout emailFeedbackLayout,nameFeedbackLayout,msgFeedbackLayout;
    EditText et_emailFeedback,etNameFeedback,et_msgFeedback;
    Button btFeedbackSubmit;

    String str_emailFeedback,strNameFeedback,str_msgFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_feedback);

        setToolbar();

      /*  RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.sliderFeedbackToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

        toolname.setText("Feedback");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        tv_contact=(TextView)findViewById(R.id.tv_feedbackContact);
        tv_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SliderFeedbackActivity.this,AirLineNumber.class));
            }
        });

        emailFeedbackLayout=(TextInputLayout)findViewById(R.id.text_input_feedbackEmaillayout);
        nameFeedbackLayout=(TextInputLayout)findViewById(R.id.text_input_feedbackNamelayout);
        msgFeedbackLayout=(TextInputLayout)findViewById(R.id.text_input_feedbackMessagelayout);

        et_emailFeedback=(EditText)findViewById(R.id.et_feedbackEmaillayout);
        etNameFeedback=(EditText)findViewById(R.id.et_feedbackNamelayout);
        et_msgFeedback=(EditText)findViewById(R.id.et_feedbackMessagelayout);

        btFeedbackSubmit=(Button)findViewById(R.id.btFeedback);

        btFeedbackSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbackValidation();
            }
        });

    }

    private void setToolbar() {
        Toolbar tb = (Toolbar) findViewById(R.id.sliderFeedbackToolBar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Feedback");
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
            case R.id.terms:return true;
            case R.id.privacy:return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SliderFeedbackActivity.this);
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

    private void feedbackValidation() {
        str_emailFeedback=et_emailFeedback.getText().toString();
        strNameFeedback=etNameFeedback.getText().toString();
        str_msgFeedback=et_msgFeedback.getText().toString();

        if(str_emailFeedback.length()==0|| str_emailFeedback.length()==' '){
            emailFeedbackLayout.setErrorEnabled(true);
            emailFeedbackLayout.setError("Please enter the email address used to make the booking.");
        }else if(strNameFeedback.length()==0||strNameFeedback.length()==' '){
            nameFeedbackLayout.setError("Please enter your name.");
            nameFeedbackLayout.setErrorEnabled(true);
        }else if(str_msgFeedback.length()==0||str_msgFeedback.length()==' '){
            msgFeedbackLayout.setError("Please enter your feedback,comments, or suggestions.");
            msgFeedbackLayout.setErrorEnabled(true);
        }

    }
}
