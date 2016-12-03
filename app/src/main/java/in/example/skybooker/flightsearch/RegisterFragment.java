package in.example.skybooker.flightsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.example.skybooker.R;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.myaccount.rewards.RewardsActivity;

/**
 * Created by siris on 10/13/2016.
 */
public class RegisterFragment extends Fragment {


    EditText etFirst,etLast,etEmail,etPwd,etNewPwd;
    TextView tvTermsC;
    Button btRegister;
    CheckBox checkBox;
    RelativeLayout rewardslay;
    TextInputLayout emailRegLayout,pwdRegLayout,newPwdRegLayout,firstNameLayout,lastNameLayout;

    String firstNameStr,lastNameStr,regEmailStr,regPwdStr,regNewPwdStr;
    String pwdPattern="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.frag_register, null);
        etFirst=(EditText)root.findViewById(R.id.etFName);
        etLast=(EditText)root.findViewById(R.id.etLName);
        etEmail=(EditText)root.findViewById(R.id.etRegEmail);
        etPwd=(EditText)root.findViewById(R.id.etRegPwd);
        etNewPwd=(EditText)root.findViewById(R.id.etRegNewPwd);

        firstNameLayout=(TextInputLayout)root.findViewById(R.id.text_input_Firstlayout);
        lastNameLayout=(TextInputLayout)root.findViewById(R.id.text_input_LastNamelayout);
        emailRegLayout=(TextInputLayout)root.findViewById(R.id.text_input_EmailReglayout);
        pwdRegLayout=(TextInputLayout)root.findViewById(R.id.text_input_PwdReglayout);
        newPwdRegLayout=(TextInputLayout)root.findViewById(R.id.text_input_NewPwdReglayout);


        tvTermsC=(TextView)root.findViewById(R.id.tvTermsAndC);
        tvTermsC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),TermsAndConditions.class));
            }
        });

        checkBox=(CheckBox)root.findViewById(R.id.CheckBoxSelected);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked())
                    Toast.makeText(getActivity(), "checked", Toast.LENGTH_LONG).show();
            }
        });

        btRegister=(Button)root.findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerValidation();
            }
        });

        rewardslay=(RelativeLayout)root.findViewById(R.id.rewardLay);
        rewardslay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),RewardsActivity.class));
            }
        });

        return root;
    }

    public void registerValidation(){

        firstNameStr=etFirst.getText().toString();
        lastNameStr=etLast.getText().toString();
        regEmailStr=etEmail.getText().toString();
        regPwdStr=etPwd.getText().toString();
        regNewPwdStr=etNewPwd.getText().toString();

        emailRegLayout.setErrorEnabled(false);
        pwdRegLayout.setErrorEnabled(false);
        newPwdRegLayout.setErrorEnabled(false);
        firstNameLayout.setErrorEnabled(false);
        lastNameLayout.setErrorEnabled(false);

        if(firstNameStr.length()==0||firstNameStr.length()==' '){
            firstNameLayout.setError("please enter your first name");
            firstNameLayout.setErrorEnabled(true);
        }
        else if(lastNameStr.length()==0||lastNameStr.length()==' '){
            lastNameLayout.setError("Please enter your last name");
            lastNameLayout.setErrorEnabled(true);
        }
        else if(regEmailStr.length()==0|| regEmailStr.length()==' '){
            emailRegLayout.setError("Please enter an email address");
            emailRegLayout.setErrorEnabled(true);
        }
        else if (!(regEmailStr.matches(emailPattern))){
            emailRegLayout.setError("Please enter valid email address");
            emailRegLayout.setErrorEnabled(true);
        }
        else if(regPwdStr.length()==0||regPwdStr.length()==' '){

            pwdRegLayout.setError("Please enter password");
            pwdRegLayout.setErrorEnabled(true);
        }
        else if(!(regPwdStr.matches(pwdPattern))){
            pwdRegLayout.setErrorEnabled(true);
            pwdRegLayout.setError("Your password must contain 8 to 30 characters, should have at least 1 number and 1 letter,can't have"+
                                        "spaces or special characters");
        }
        else if (regNewPwdStr.length()==' '|| regNewPwdStr.length()==0){
            newPwdRegLayout.setError("Please re-enter your new password");
            newPwdRegLayout.setErrorEnabled(true);
        }

        else if(!(regPwdStr.equals(regNewPwdStr))){
            newPwdRegLayout.setErrorEnabled(true);
            newPwdRegLayout.setError("You have re-entered a different password. Please re-enter your new password.");

        }

    }

}
