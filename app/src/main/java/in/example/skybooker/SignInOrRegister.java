package in.example.skybooker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import in.example.skybooker.flightsearch.RegisterFragment;
import in.example.skybooker.flightsearch.SignInFragment;


public class SignInOrRegister extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener {

    TextView toolname;
    ImageView toolIcon,googleIcon,fbIcon;
    TabLayout tabLayout;
    ViewPager pager;
    SharedPreferences.Editor editor;
    SharedPreferences sp;


   static String personName,personPhotoUrl,email;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    public static GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    static GoogleSignInOptions gso;
    static boolean bol=false;

    /*public SignInOrRegister(){

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SingleTon.signObj=this;

        sp = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = sp.edit();

        //SingleTon.fragmentActivity=this;
        SingleTon obj = new SingleTon();
        obj.gmailIntialise();

        RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.toolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolSearchImg) ;

        toolname.setText("Sign In or Register");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        googleIcon=(ImageView)findViewById(R.id.imgGooglePlus);
        //fbIcon=(ImageView)findViewById(R.id.fbImg);



        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        /*SignIn & Register  */
        pager= (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(pager);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);

        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary));



        /* Gmail */
        googleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(SingleTon.mGoogleApiClient);
                startActivityForResult(signInIntent, SingleTon.RC_SIGN_IN);
               //showProgressDialog();
            }
        });


        /* facebook */
       /* fbIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }

    /* Gmail */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == SingleTon.RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            personName = acct.getDisplayName();
            personPhotoUrl = acct.getPhotoUrl().toString();
            email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);
            /*txtName.setText(personName);
            txtEmail.setText(email);
            Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);*/
            updateUI(true);
            SingleTon.mPref.edit().putString("profileName",personName);
            SingleTon.mPref.edit().putString("personPhotoUrl",personPhotoUrl);
            SingleTon.mPref.edit().putString("email",email);
            SingleTon.mPref.edit().putBoolean("isUserLogin", true);
            SingleTon.mPref.edit().commit();

            //Log.i(sp.getString("personPhotoUrl",null)+"",sp.getString("email",null)+"");
            Toast.makeText(getApplicationContext(),"Sign in Successfully",Toast.LENGTH_SHORT).show();
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
            Toast.makeText(getApplicationContext(),"Please sign in",Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            googleIcon.setVisibility(View.VISIBLE);
            //btnSignOut.setVisibility(View.VISIBLE);
            //btnRevokeAccess.setVisibility(View.VISIBLE);
            // llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            googleIcon.setVisibility(View.VISIBLE);
            //btnSignOut.setVisibility(View.GONE);
            //btnRevokeAccess.setVisibility(View.GONE);
            // llProfileLayout.setVisibility(View.GONE);
        }


    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SignInFragment(), "SIGN IN");
        adapter.addFragment(new RegisterFragment(), "REGISTER");
        viewPager.setAdapter(adapter);

    }

}

class ViewPagerAdapter extends FragmentPagerAdapter {
     private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mFragmentTitleList.get(position);
    }

   /* private  void gmailDeclarations() {

         gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }*/
/*

    public boolean signOut() {
        gmailDeclarations();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        //updateUI(false);
                        bol=true;
                    }
                });
        return bol;
    }
*/

   /* private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            personName = acct.getDisplayName();
            personPhotoUrl = acct.getPhotoUrl().toString();
            email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);
            *//*txtName.setText(personName);
            txtEmail.setText(email);
            Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);*//*
            updateUI(true);
            editor.putString("profileName",personName);
            editor.putString("personPhotoUrl",personPhotoUrl);
            editor.putString("email",email);
            editor.putBoolean("isUserLogin", true);
            editor.commit();

            Log.i(sp.getString("personPhotoUrl",null)+"",sp.getString("email",null)+"");
            Toast.makeText(getApplication(),"You are already signed in",Toast.LENGTH_SHORT).show();
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
            Toast.makeText(getApplication(),"Please sign in",Toast.LENGTH_SHORT).show();
        }
        finish();
    }*/



   /* @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }*/

   /* @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }*/

   /* private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
*/
    }


