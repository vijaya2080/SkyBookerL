package in.example.skybooker.flightsearch;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.example.skybooker.R;
import in.example.skybooker.myaccount.rewards.RewardsActivity;

/**
 * Created by siris on 10/13/2016.
 */
public class SignInFragment extends Fragment {

    EditText etEmailId,etPassword,etRecoverEmail;
    TextView tvRecoverPwd;
    Button btSignin;
    RelativeLayout rewardsLayout;
    TextInputLayout emailLayout,pwdLayout,recoverLayout;

    String loginEmailStr,loginPwdStr,recoverEmailStr;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.frag_signin, null);

        etEmailId=(EditText)root.findViewById(R.id.etEmail);
        etPassword=(EditText)root.findViewById(R.id.etPwd);

        emailLayout = (TextInputLayout)root.findViewById(R.id.text_input_Emaillayout);
        pwdLayout=(TextInputLayout)root.findViewById(R.id.text_input_Pwdlayout);

        tvRecoverPwd=(TextView)root.findViewById(R.id.tvRecoverPwd);
        tvRecoverPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                final View promptView = layoutInflater.inflate(R.layout.recoverpassword, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setView(promptView);

                recoverLayout=(TextInputLayout) promptView.findViewById(R.id.text_input_recoverlayout);
                etRecoverEmail = (EditText) promptView.findViewById(R.id.etRecoverEmail);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("RECOVER", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                Button theButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                theButton.setOnClickListener(new CustomListener(alertDialog));
               /* if (theButton != null) {
                    theButton.setBackgroundDrawable(getActivity().getResources()
                            .getDrawable(ContextCompat.getColor(getActivity(), R.color.colorPrimary)));

                    theButton.setTextColor(getActivity().getResources()
                            .getColor(android.R.color.white));
                }*/

            }
        });

        btSignin=(Button)root.findViewById(R.id.btSignin);

        /*getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);*/

        btSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidation();
            }
        });


        rewardsLayout=(RelativeLayout)root.findViewById(R.id.rewardLayout);
        rewardsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),RewardsActivity.class));
            }
        });

        return root;
    }

    public void loginValidation(){

        emailLayout.setErrorEnabled(false);
        emailLayout.setErrorEnabled(false);
        pwdLayout.setEnabled(false);

        loginEmailStr=etEmailId.getText().toString();
        loginPwdStr=etPassword.getText().toString();

        if(loginEmailStr.length()==0||loginEmailStr.length()==' '){
            emailLayout.setErrorEnabled(true);
            emailLayout.setError("Please enter an email address");
            //etEmailId.setError("Please enter an email address");
        }
        else if (!(loginEmailStr.matches(emailPattern))){
           // etEmailId.setError("Please enter valid email address");
            emailLayout.setErrorEnabled(true);
            emailLayout.setError("Please enter valid email address");
        }
        else if(loginPwdStr.length()==0 || loginPwdStr.length()==' '){
            //etPassword.setError("Please enter password");
            pwdLayout.setError(" Please enter password");
            pwdLayout.setEnabled(true);

        }
        else {

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

            recoverEmailStr = etRecoverEmail.getText().toString();
            if (etRecoverEmail.length() == 0 || etRecoverEmail.length() == ' ') {
                //etRecoverEmail.setError("Please enter email address ");
                recoverLayout.setError("Please enter email address ");
                recoverLayout.setErrorEnabled(true);
            } else{
                dialog.dismiss();
            }
        }
    }
}
