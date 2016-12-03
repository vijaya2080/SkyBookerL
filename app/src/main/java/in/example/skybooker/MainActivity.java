package in.example.skybooker;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import in.example.skybooker.communication.SliderFeedbackActivity;
import in.example.skybooker.communication.SliderPrivacyPolicy;
import in.example.skybooker.communication.SliderSettingsActivity;
import in.example.skybooker.flightsearch.relatedflights.payment.TermsAndConditions;
import in.example.skybooker.flightsearch.SearchFlightActivty;
import in.example.skybooker.myaccount.rewards.RewardsActivity;
import in.example.skybooker.myaccount.WelcomeActivity;
import in.example.skybooker.slider.CarRentals;
import in.example.skybooker.slider.CustomNavRVAdapter;
import in.example.skybooker.slider.FlightStatus;
import in.example.skybooker.slider.HotelsActivity;
import in.example.skybooker.slider.MyTrips;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CustomNavRVAdapter.OnSecurityQnClickListener, GoogleApiClient.OnConnectionFailedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    ArrayList<String> IMAGES;
    ArrayList<Integer> imagesArray;
    int currentPage = 0, NUM_PAGES = 5;
    private static ViewPager mPager;
    ImageView flightImg, carImg, tripsImage, downImg, upImg, profileImage, signinProfilePic;
    SharedPreferences.Editor editor;
    SharedPreferences shared;
    String profileName, profilePic, profileEmail;
    TextView tv_profileName, tv_signIn, tv_signOut, tv_myAccount, tv_settings, tv_feedback, tv_TnC, tv_privacyPolicy;

    NavigationView navigationView;
    AlertDialog.Builder alertDialogBuilder;
    RelativeLayout header, customNavSignoutLayout, rlcallforSupport;
    Boolean login;
    RecyclerView navDrawerRv;
    CustomNavRVAdapter customAdapter;
    LinearLayout signOutLayout;
    RelativeLayout flights,hotels,cars,mytrips;
    private ArrayList<String> navDrawerRvTitles, navDrawerRvSubTitles;
    int index;
   ImageView img_flights,img_hotels,img_cars,img_mytrips;
   TextView tv_flights,tv_hotels,tv_cars,tv_mytrips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SingleTon.obj=this;

        shared = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor = shared.edit();
       /* SingleTon singleObj = new SingleTon();
        singleObj.gmailIntialise();*/

        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        toolbar = (Toolbar) findViewById(R.id.appbar_top);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mPager = (ViewPager) findViewById(R.id.pager);

        flights=(RelativeLayout)findViewById(R.id.rv_flights);
        img_flights=(ImageView)flights.findViewById(R.id.flightIcon);
        tv_flights=(TextView)flights.findViewById(R.id.tv_flights);
        tv_flights.setText("Flights");
        img_flights.setImageResource(R.mipmap.flight);



        hotels=(RelativeLayout)findViewById(R.id.rv_hotels);
        img_hotels=(ImageView)hotels.findViewById(R.id.flightIcon);
        tv_hotels=(TextView)hotels.findViewById(R.id.tv_flights);
        tv_hotels.setText("Hotels");
        img_hotels.setImageResource(R.mipmap.hotels);

        cars=(RelativeLayout)findViewById(R.id.rv_cars);
        img_cars=(ImageView)cars.findViewById(R.id.flightIcon);
        tv_cars=(TextView)cars.findViewById(R.id.tv_flights);
        tv_cars.setText("Cars");
        img_cars.setImageResource(R.mipmap.cars);

        mytrips=(RelativeLayout)findViewById(R.id.rv_mytrips);
        img_mytrips=(ImageView)mytrips.findViewById(R.id.flightIcon);
        tv_mytrips=(TextView)mytrips.findViewById(R.id.tv_flights);
        tv_mytrips.setText("My Trips");
        img_mytrips.setImageResource(R.mipmap.trips);



        tv_settings = (TextView) findViewById(R.id.tv_SliderSettings);
        tv_feedback = (TextView) findViewById(R.id.tv_sliderFeedback);
        tv_TnC = (TextView) findViewById(R.id.tv_SliderTnC);
        tv_privacyPolicy = (TextView) findViewById(R.id.tv_sliderPrivacy);

        signOutLayout=(LinearLayout)findViewById(R.id.sliderRecycler);

        rlcallforSupport = (RelativeLayout) findViewById(R.id.callLayout);


        IMAGES = new ArrayList<>();
        imagesArray = new ArrayList<>();
        navDrawerRvTitles = new ArrayList<>();
        navDrawerRvSubTitles = new ArrayList<>();


        navDrawerRvTitles.add("Flights");
        navDrawerRvTitles.add("Hotels");
        navDrawerRvTitles.add("Car Rentals");
        navDrawerRvTitles.add("My Trips");
        navDrawerRvTitles.add("My Rewards");
        navDrawerRvTitles.add("Flight Status");

        Integer ImageName[] = {
                R.mipmap.flights,
                R.mipmap.sliderhotels,
                R.mipmap.carrentals,
                R.mipmap.mytrips,
                R.mipmap.rewards,
                R.mipmap.flightstatus
        };

        navDrawerRvSubTitles.add("Find deals for over 550 airlines");
        navDrawerRvSubTitles.add("Save on hotel rooms");
        navDrawerRvSubTitles.add("Drive away with savings");
        navDrawerRvSubTitles.add("Get deatails about your trips");
        navDrawerRvSubTitles.add("Book and earn double points!");
        navDrawerRvSubTitles.add("Check the status of your flight now");

        IMAGES.add("http://cozyhomz.com/media/catalog/category/sofa-bg1_1.jpg");
        IMAGES.add("http://ozyhomz.com/media/catalog/category/liv-bg2.jpg");
        IMAGES.add("http://cozyhomz.com/media/catalog/category/dinning_10.jpg");
        IMAGES.add("http://cozyhomz.com/media/catalog/category/bar-1.jpg");
        IMAGES.add("http://cozyhomz.com/media/catalog/category/decor-1_1.jpg");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        header = (RelativeLayout) findViewById(R.id.header_view);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        profileImage = (ImageView) header.findViewById(R.id.circleView);
        signinProfilePic = (ImageView) header.findViewById(R.id.profileImg);
        tv_signIn = (TextView) header.findViewById(R.id.signIn);
        downImg = (ImageView) header.findViewById(R.id.imgDownArrow);
        upImg = (ImageView) header.findViewById(R.id.imgUpArrow);
        tv_profileName = (TextView) header.findViewById(R.id.tv_profileName);
        tv_signIn.setText("Sign In");

        navDrawerRv = (RecyclerView) findViewById(R.id.sliderRv);
        customAdapter = new CustomNavRVAdapter(this, navDrawerRvTitles, navDrawerRvSubTitles,ImageName);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        navDrawerRv.setLayoutManager(layoutManager);
        navDrawerRv.setItemAnimator(new DefaultItemAnimator());
        navDrawerRv.setAdapter(customAdapter);
        customAdapter.setOnSecurityQnClickListener(this);
        //navDrawerRv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        customNavSignoutLayout = (RelativeLayout) findViewById(R.id.customNavSignoutLayout);
        tv_signOut = (TextView) customNavSignoutLayout.findViewById(R.id.tv_customNvSignout);
        tv_myAccount = (TextView) customNavSignoutLayout.findViewById(R.id.tv_customNvMyAccount);


        downImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOutLayout.setVisibility(View.GONE);
                customNavSignoutLayout.setVisibility(View.VISIBLE);
                upImg.setVisibility(View.VISIBLE);
                downImg.setVisibility(View.GONE);

            }
        });
        upImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOutLayout.setVisibility(View.VISIBLE);
                customNavSignoutLayout.setVisibility(View.GONE);
                upImg.setVisibility(View.GONE);
                downImg.setVisibility(View.VISIBLE);
            }
        });

        img_flights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchFlightActivty.class));
            }
        });

        img_mytrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shared.getBoolean("isUserLogin",false))
                    startActivity(new Intent(MainActivity.this,MyTrips.class) );
                else
                    startActivity(new Intent(MainActivity.this,SignInOrRegister.class) );
            }
        });


        init();

        tv_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogBuilder.setMessage("Are you sure you want to sign out?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                gmailSignout();

                                /*//SignInOrRegister obj=new SignInOrRegister();
                                if(obj.signOut()){
                                    editor.putBoolean("isUserLogin",false);
                                    editor.commit();
                                    checkSignIn();
                                }*/

                            }
                        });

                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                alertDialogBuilder.create().show();
            }

        });
        drawer.closeDrawer(GravityCompat.START);
        tv_myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));

            }
        });

        tv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(MainActivity.this, SliderSettingsActivity.class));
            }
        });

        tv_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(MainActivity.this, SliderPrivacyPolicy.class));
            }
        });
        tv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(MainActivity.this, SliderFeedbackActivity.class));
            }
        });
        rlcallforSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialogBuilder.setMessage("1-212-634-4079")
                        .setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:1-212-634-4079"));
                                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                startActivity(intent);
                            }
                        });


                alertDialogBuilder.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).create().show();
            }
        });
        tv_TnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TermsAndConditions.class));
            }
        });
    }

      private  void gmailSignout() {

          Auth.GoogleSignInApi.signOut(SingleTon.mGoogleApiClient).setResultCallback(
                  new ResultCallback<Status>() {
                      @Override
                      public void onResult(Status status) {
                          editor.putBoolean("isUserLogin",false);
                          editor.commit();
                          checkSignIn();
                      }
                  });

      }

    protected void onResume(){
        super.onResume();
        checkSignIn();
    }
    public void checkSignIn(){
        if(shared.getBoolean("isUserLogin",false)){
            tv_signIn.setVisibility(View.GONE);
            profileImage.setVisibility(View.VISIBLE);
            signinProfilePic.setVisibility(View.GONE);
            tv_profileName.setVisibility(View.VISIBLE);
            downImg.setVisibility(View.VISIBLE);

            profileName=shared.getString("profileName",null);
            tv_profileName.setText("Hello "+profileName+" !");
            profilePic=shared.getString("personPhotoUrl",null);
            Glide.with(getApplicationContext()).load(profilePic)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profileImage);

        }
        else{
            profileImage.setVisibility(View.GONE);
            signinProfilePic.setVisibility(View.VISIBLE);
            tv_profileName.setVisibility(View.GONE);
            downImg.setVisibility(View.GONE);
            upImg.setVisibility(View.GONE);
            header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,SignInOrRegister.class) );
                   // Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                    drawer.closeDrawer(GravityCompat.START);
                }
            });


        }
    }
    public void init(){

        for (int i = 0; i < IMAGES.size(); i++)

        mPager.setAdapter(new SlidingImageAdapter(MainActivity.this, IMAGES));
       // addDots();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                //selectDot(position);
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.singOut){

            alertDialogBuilder.setMessage("Are you sure you want to sign out?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();

            alertDialogBuilder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
        }
        else if(id==R.id.myAccount){
            startActivity(new Intent(MainActivity.this,WelcomeActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSecurityQnClick(View view, int position) throws ClassNotFoundException, IOException, DecoderException {
        Toast.makeText(getApplicationContext(),"clicked on"+position+" ",Toast.LENGTH_SHORT).show();
        index=position;
        display(index);
    }

    private void display(int index) {
        drawer.closeDrawer(GravityCompat.START);

        if(index==0){
            startActivity(new Intent(MainActivity.this,SearchFlightActivty.class) );
        }
        else if(index==1){
            startActivity(new Intent(MainActivity.this,HotelsActivity.class) );
        }
        else if(index==2){
            startActivity(new Intent(MainActivity.this,CarRentals.class) );
        }
        else if(index==3){
            if(shared.getBoolean("isUserLogin",false))
                startActivity(new Intent(MainActivity.this,MyTrips.class) );
            else
                startActivity(new Intent(MainActivity.this,SignInOrRegister.class) );
        }
        else if(index==4){
            startActivity(new Intent(MainActivity.this,RewardsActivity.class) );
        }
        else if(index==5){
            startActivity(new Intent(MainActivity.this,FlightStatus.class) );
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
