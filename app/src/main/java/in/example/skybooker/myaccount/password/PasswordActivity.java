package in.example.skybooker.myaccount.password;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.example.skybooker.R;

public class PasswordActivity extends AppCompatActivity {
    TextView toolname;
    ImageView toolIcon;
    EditText etPwd,etNewPwd;
    TextInputLayout pwdLayout,newPwdLayout;
    Button btSubmit;
    String pwdStr,newPwdStr;
    String pwdPattern="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$";
    AlertDialog.Builder alertDialogBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //setToolBar();

        RelativeLayout toolbarlayout = (RelativeLayout) findViewById(R.id.pwdToolBar);
        toolname = (TextView) toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon = (ImageView) toolbarlayout.findViewById(R.id.toolBackImg);
        toolname.setText("Change Password");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etPwd=(EditText)findViewById(R.id.et_newPwdlayout);
        etNewPwd=(EditText)findViewById(R.id.et_confirmPwdlayout);
        pwdLayout=(TextInputLayout)findViewById(R.id.text_input_newPwdlayout);
        newPwdLayout=(TextInputLayout)findViewById(R.id.text_input_confirmPwdlayout);
        btSubmit=(Button)findViewById(R.id.btPwdSubmit);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

            }
        });


    }


    private void validation() {

        pwdStr=etPwd.getText().toString();
        newPwdStr=etNewPwd.getText().toString();

        pwdLayout.setErrorEnabled(false);
        newPwdLayout.setErrorEnabled(false);

        if(pwdStr.length()==0||pwdStr.length()==' '){
            pwdLayout.setError("Please enter password");
            pwdLayout.setErrorEnabled(true);
        }
        else if(!(pwdStr.matches(pwdPattern))){
            pwdLayout.setErrorEnabled(true);
            pwdLayout.setError("Your password must contain 8 to 30 characters, should have at least 1 number and 1 letter,can't have"+
                    "spaces or special characters");
        }
        else if (newPwdStr.length()==' '|| newPwdStr.length()==0){
            newPwdLayout.setError("Please re-enter your new password");
            newPwdLayout.setErrorEnabled(true);
        }

        else if(!(pwdStr.equals(newPwdStr))){
            newPwdLayout.setErrorEnabled(true);
            newPwdLayout.setError("You have re-entered a different password. Please re-enter your new password.");

        }else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(PasswordActivity.this);
                    dialog.setTitle("Your password changed successfully")
                    .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            dialog.dismiss();
                            finish();
                        }

                    });
            dialog.create().show();
        }

    }
}
