package in.example.skybooker;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by siris on 10/22/2016.
 */
public class SingleTon extends Application implements GoogleApiClient.OnConnectionFailedListener{
    public static SharedPreferences mPref;
    static String personName,personPhotoUrl,email;
    private static final String TAG = MainActivity.class.getSimpleName();
    static final int RC_SIGN_IN = 007;
    public static GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    static GoogleSignInOptions gso;
    static boolean bol=false;

    public static MainActivity obj;
    public static SignInOrRegister signObj;
    public  static  JSONObject jsonObj1;

    String request="{\n" +
            "  \"SearchFlights_Response\": {\n" +
            "    \"-Count\": \"6\",\n" +
            "    \"-SessionId\": \"9952CFD2A78839E3E0D0A44F87B2B132\",\n" +
            "    \"-ExecutedIn\": \"3.05s\",\n" +
            "    \"FlightResult\": [\n" +
            "      {\n" +
            "        \"Outbound\": {\n" +
            "          \"Duration\": \"0050\",\n" +
            "          \"Airline\": \"AI\",\n" +
            "          \"FlightDetails\": {\n" +
            "            \"FlightSegment\": {\n" +
            "              \"DepartureDate\": \"201116\",\n" +
            "              \"DepartureTime\": \"1720\",\n" +
            "              \"ArrivalDate\": \"201116\",\n" +
            "              \"ArrivalTime\": \"1810\",\n" +
            "              \"FromLocation\": \"1810\",\n" +
            "              \"From\": { \"Location\": \"VGA\" },\n" +
            "              \"To\": { \"Location\": \"HYD\" },\n" +
            "              \"MarketingCarrier\": \"AI\",\n" +
            "              \"OperatingCarrier\": \"AI\",\n" +
            "              \"FlightNumber\": \"840\",\n" +
            "              \"AircraftType\": \"320\",\n" +
            "              \"EquipmentType\": \"320\",\n" +
            "              \"ElectronicTicketing\": \"Y\",\n" +
            "              \"ProductDetailQualifier\": \"LCA\",\n" +
            "              \"Rbd\": \"U\",\n" +
            "              \"Cabin\": \"M\",\n" +
            "              \"AvlStatus\": \"9\",\n" +
            "              \"FareBasis\": \"UIP\",\n" +
            "              \"FareType\": { \"FlightSegment\": \"RP\" }\n" +
            "            },\n" +
            "            \"BaggageInformation\": {\n" +
            "              \"FreeAllowance\": \"25\",\n" +
            "              \"QuantityCode\": \"W\"\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"Recommendation\": {\n" +
            "          \"Reference\": { \"Outbound\": \"P3J4T3K3X4Y2R214V2-0-9952CFD2A78839E3E0D0A44F87B2B132-0\" },\n" +
            "          \"Currency\": \"USD\",\n" +
            "          \"TotalFare\": \"131.32\",\n" +
            "          \"TotalFareTax\": \"17.32\",\n" +
            "          \"NumberType\": \"D\",\n" +
            "          \"Adult\": \"1\",\n" +
            "          \"AdultTotalFare\": \"65.66\",\n" +
            "          \"AdultTotalTax\": \"8.66\",\n" +
            "          \"Child\": \"1\",\n" +
            "          \"ChildTotalFare\": \"65.66\",\n" +
            "          \"ChildTotalTax\": \"8.66\",\n" +
            "          \"FareRule\": \"TD\",\n" +
            "          \"CabinClass\": \"Economy\",\n" +
            "          \"UserName\": \"bhaskar\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"Outbound\": {\n" +
            "          \"Duration\": \"0050\",\n" +
            "          \"Airline\": \"AI\",\n" +
            "          \"FlightDetails\": {\n" +
            "            \"FlightSegment\": {\n" +
            "              \"DepartureDate\": \"201116\",\n" +
            "              \"DepartureTime\": \"1720\",\n" +
            "              \"ArrivalDate\": \"201116\",\n" +
            "              \"ArrivalTime\": \"1810\",\n" +
            "              \"FromLocation\": \"1810\",\n" +
            "              \"From\": { \"Location\": \"VGA\" },\n" +
            "              \"To\": { \"Location\": \"HYD\" },\n" +
            "              \"MarketingCarrier\": \"AI\",\n" +
            "              \"OperatingCarrier\": \"AI\",\n" +
            "              \"FlightNumber\": \"840\",\n" +
            "              \"AircraftType\": \"320\",\n" +
            "              \"EquipmentType\": \"320\",\n" +
            "              \"ElectronicTicketing\": \"Y\",\n" +
            "              \"ProductDetailQualifier\": \"LCA\",\n" +
            "              \"Rbd\": \"U\",\n" +
            "              \"Cabin\": \"M\",\n" +
            "              \"AvlStatus\": \"9\",\n" +
            "              \"FareBasis\": \"UIP\",\n" +
            "              \"FareType\": { \"FlightSegment\": \"RP\" }\n" +
            "            },\n" +
            "            \"BaggageInformation\": {\n" +
            "              \"FreeAllowance\": \"25\",\n" +
            "              \"QuantityCode\": \"W\"\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"Recommendation\": {\n" +
            "          \"Reference\": { \"Outbound\": \"P3J4T3K3X4Y2R214S2-0-9952CFD2A78839E3E0D0A44F87B2B132-1\" },\n" +
            "          \"Currency\": \"USD\",\n" +
            "          \"TotalFare\": \"131.32\",\n" +
            "          \"TotalFareTax\": \"17.32\",\n" +
            "          \"NumberType\": \"D\",\n" +
            "          \"Adult\": \"1\",\n" +
            "          \"AdultTotalFare\": \"65.66\",\n" +
            "          \"AdultTotalTax\": \"8.66\",\n" +
            "          \"Child\": \"1\",\n" +
            "          \"ChildTotalFare\": \"65.66\",\n" +
            "          \"ChildTotalTax\": \"8.66\",\n" +
            "          \"FareRule\": \"TD\",\n" +
            "          \"CabinClass\": \"Economy\",\n" +
            "          \"UserName\": \"bhaskar\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"Outbound\": {\n" +
            "          \"Duration\": \"2625\",\n" +
            "          \"Airline\": \"AI\",\n" +
            "          \"FlightDetails\": {\n" +
            "            \"FlightSegment\": [\n" +
            "              {\n" +
            "                \"DepartureDate\": \"201116\",\n" +
            "                \"DepartureTime\": \"2040\",\n" +
            "                \"ArrivalDate\": \"201116\",\n" +
            "                \"ArrivalTime\": \"2255\",\n" +
            "                \"FromLocation\": \"2255\",\n" +
            "                \"From\": { \"Location\": \"VGA\" },\n" +
            "                \"To\": {\n" +
            "                  \"Location\": \"DEL\",\n" +
            "                  \"Terminal\": \"3\"\n" +
            "                },\n" +
            "                \"MarketingCarrier\": \"AI\",\n" +
            "                \"OperatingCarrier\": \"AI\",\n" +
            "                \"FlightNumber\": \"468\",\n" +
            "                \"AircraftType\": \"319\",\n" +
            "                \"EquipmentType\": \"319\",\n" +
            "                \"ElectronicTicketing\": \"Y\",\n" +
            "                \"ProductDetailQualifier\": \"LCA\",\n" +
            "                \"Rbd\": \"U\",\n" +
            "                \"Cabin\": \"M\",\n" +
            "                \"AvlStatus\": \"9\",\n" +
            "                \"FareBasis\": \"UIP\",\n" +
            "                \"FareType\": { \"FlightSegment\": \"RP\" }\n" +
            "              },\n" +
            "              {\n" +
            "                \"DepartureDate\": \"211116\",\n" +
            "                \"DepartureTime\": \"2055\",\n" +
            "                \"ArrivalDate\": \"211116\",\n" +
            "                \"ArrivalTime\": \"2305\",\n" +
            "                \"FromLocation\": \"2305\",\n" +
            "                \"From\": {\n" +
            "                  \"Location\": \"DEL\",\n" +
            "                  \"Terminal\": \"3\"\n" +
            "                },\n" +
            "                \"To\": { \"Location\": \"HYD\" },\n" +
            "                \"MarketingCarrier\": \"AI\",\n" +
            "                \"OperatingCarrier\": \"AI\",\n" +
            "                \"FlightNumber\": \"839\",\n" +
            "                \"AircraftType\": \"321\",\n" +
            "                \"EquipmentType\": \"321\",\n" +
            "                \"ElectronicTicketing\": \"Y\",\n" +
            "                \"ProductDetailQualifier\": \"LCA\",\n" +
            "                \"Rbd\": \"U\",\n" +
            "                \"Cabin\": \"M\",\n" +
            "                \"AvlStatus\": \"9\",\n" +
            "                \"FareBasis\": \"UIP\",\n" +
            "                \"FareType\": { \"FlightSegment\": \"RP\" }\n" +
            "              }\n" +
            "            ],\n" +
            "            \"BaggageInformation\": {\n" +
            "              \"FreeAllowance\": \"25\",\n" +
            "              \"QuantityCode\": \"W\"\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"Recommendation\": {\n" +
            "          \"Reference\": { \"Outbound\": \"P3J4T3K3X4Y2R214V2-0-9952CFD2A78839E3E0D0A44F87B2B132-2\" },\n" +
            "          \"Currency\": \"USD\",\n" +
            "          \"TotalFare\": \"385.72\",\n" +
            "          \"TotalFareTax\": \"31.72\",\n" +
            "          \"NumberType\": \"D\",\n" +
            "          \"Adult\": \"1\",\n" +
            "          \"AdultTotalFare\": \"192.86\",\n" +
            "          \"AdultTotalTax\": \"15.86\",\n" +
            "          \"Child\": \"1\",\n" +
            "          \"ChildTotalFare\": \"192.86\",\n" +
            "          \"ChildTotalTax\": \"15.86\",\n" +
            "          \"FareRule\": \"TD\",\n" +
            "          \"CabinClass\": \"Economy\",\n" +
            "          \"UserName\": \"bhaskar\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"Outbound\": {\n" +
            "          \"Duration\": \"2625\",\n" +
            "          \"Airline\": \"AI\",\n" +
            "          \"FlightDetails\": {\n" +
            "            \"FlightSegment\": [\n" +
            "              {\n" +
            "                \"DepartureDate\": \"201116\",\n" +
            "                \"DepartureTime\": \"2040\",\n" +
            "                \"ArrivalDate\": \"201116\",\n" +
            "                \"ArrivalTime\": \"2255\",\n" +
            "                \"FromLocation\": \"2255\",\n" +
            "                \"From\": { \"Location\": \"VGA\" },\n" +
            "                \"To\": {\n" +
            "                  \"Location\": \"DEL\",\n" +
            "                  \"Terminal\": \"3\"\n" +
            "                },\n" +
            "                \"MarketingCarrier\": \"AI\",\n" +
            "                \"OperatingCarrier\": \"AI\",\n" +
            "                \"FlightNumber\": \"468\",\n" +
            "                \"AircraftType\": \"319\",\n" +
            "                \"EquipmentType\": \"319\",\n" +
            "                \"ElectronicTicketing\": \"Y\",\n" +
            "                \"ProductDetailQualifier\": \"LCA\",\n" +
            "                \"Rbd\": \"U\",\n" +
            "                \"Cabin\": \"M\",\n" +
            "                \"AvlStatus\": \"9\",\n" +
            "                \"FareBasis\": \"UIP\",\n" +
            "                \"FareType\": { \"FlightSegment\": \"RP\" }\n" +
            "              },\n" +
            "              {\n" +
            "                \"DepartureDate\": \"211116\",\n" +
            "                \"DepartureTime\": \"2055\",\n" +
            "                \"ArrivalDate\": \"211116\",\n" +
            "                \"ArrivalTime\": \"2305\",\n" +
            "                \"FromLocation\": \"2305\",\n" +
            "                \"From\": {\n" +
            "                  \"Location\": \"DEL\",\n" +
            "                  \"Terminal\": \"3\"\n" +
            "                },\n" +
            "                \"To\": { \"Location\": \"HYD\" },\n" +
            "                \"MarketingCarrier\": \"AI\",\n" +
            "                \"OperatingCarrier\": \"AI\",\n" +
            "                \"FlightNumber\": \"839\",\n" +
            "                \"AircraftType\": \"321\",\n" +
            "                \"EquipmentType\": \"321\",\n" +
            "                \"ElectronicTicketing\": \"Y\",\n" +
            "                \"ProductDetailQualifier\": \"LCA\",\n" +
            "                \"Rbd\": \"U\",\n" +
            "                \"Cabin\": \"M\",\n" +
            "                \"AvlStatus\": \"9\",\n" +
            "                \"FareBasis\": \"UIP\",\n" +
            "                \"FareType\": { \"FlightSegment\": \"RP\" }\n" +
            "              }\n" +
            "            ],\n" +
            "            \"BaggageInformation\": {\n" +
            "              \"FreeAllowance\": \"25\",\n" +
            "              \"QuantityCode\": \"W\"\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"Recommendation\": {\n" +
            "          \"Reference\": { \"Outbound\": \"P3J4T3K3X4Y2R214S2-0-9952CFD2A78839E3E0D0A44F87B2B132-3\" },\n" +
            "          \"Currency\": \"USD\",\n" +
            "          \"TotalFare\": \"385.72\",\n" +
            "          \"TotalFareTax\": \"31.72\",\n" +
            "          \"NumberType\": \"D\",\n" +
            "          \"Adult\": \"1\",\n" +
            "          \"AdultTotalFare\": \"192.86\",\n" +
            "          \"AdultTotalTax\": \"15.86\",\n" +
            "          \"Child\": \"1\",\n" +
            "          \"ChildTotalFare\": \"192.86\",\n" +
            "          \"ChildTotalTax\": \"15.86\",\n" +
            "          \"FareRule\": \"TD\",\n" +
            "          \"CabinClass\": \"Economy\",\n" +
            "          \"UserName\": \"bhaskar\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"Outbound\": {\n" +
            "          \"Duration\": \"2305\",\n" +
            "          \"Airline\": \"AI\",\n" +
            "          \"FlightDetails\": {\n" +
            "            \"FlightSegment\": [\n" +
            "              {\n" +
            "                \"DepartureDate\": \"201116\",\n" +
            "                \"DepartureTime\": \"2040\",\n" +
            "                \"ArrivalDate\": \"201116\",\n" +
            "                \"ArrivalTime\": \"2255\",\n" +
            "                \"FromLocation\": \"2255\",\n" +
            "                \"From\": { \"Location\": \"VGA\" },\n" +
            "                \"To\": {\n" +
            "                  \"Location\": \"DEL\",\n" +
            "                  \"Terminal\": \"3\"\n" +
            "                },\n" +
            "                \"MarketingCarrier\": \"AI\",\n" +
            "                \"OperatingCarrier\": \"AI\",\n" +
            "                \"FlightNumber\": \"468\",\n" +
            "                \"AircraftType\": \"319\",\n" +
            "                \"EquipmentType\": \"319\",\n" +
            "                \"ElectronicTicketing\": \"Y\",\n" +
            "                \"ProductDetailQualifier\": \"LCA\",\n" +
            "                \"Rbd\": \"U\",\n" +
            "                \"Cabin\": \"M\",\n" +
            "                \"AvlStatus\": \"9\",\n" +
            "                \"FareBasis\": \"UIP\",\n" +
            "                \"FareType\": { \"FlightSegment\": \"RP\" }\n" +
            "              },\n" +
            "              {\n" +
            "                \"DepartureDate\": \"211116\",\n" +
            "                \"DepartureTime\": \"1730\",\n" +
            "                \"ArrivalDate\": \"211116\",\n" +
            "                \"ArrivalTime\": \"1945\",\n" +
            "                \"FromLocation\": \"1945\",\n" +
            "                \"From\": {\n" +
            "                  \"Location\": \"DEL\",\n" +
            "                  \"Terminal\": \"3\"\n" +
            "                },\n" +
            "                \"To\": { \"Location\": \"HYD\" },\n" +
            "                \"MarketingCarrier\": \"AI\",\n" +
            "                \"OperatingCarrier\": \"AI\",\n" +
            "                \"FlightNumber\": \"126\",\n" +
            "                \"AircraftType\": \"77W\",\n" +
            "                \"EquipmentType\": \"77W\",\n" +
            "                \"ElectronicTicketing\": \"Y\",\n" +
            "                \"ProductDetailQualifier\": \"LCA\",\n" +
            "                \"Rbd\": \"B\",\n" +
            "                \"Cabin\": \"M\",\n" +
            "                \"AvlStatus\": \"9\",\n" +
            "                \"FareBasis\": \"BRT\",\n" +
            "                \"FareType\": { \"FlightSegment\": \"RP\" }\n" +
            "              }\n" +
            "            ],\n" +
            "            \"BaggageInformation\": {\n" +
            "              \"FreeAllowance\": \"25\",\n" +
            "              \"QuantityCode\": \"W\"\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"Recommendation\": {\n" +
            "          \"Reference\": { \"Outbound\": \"P3J4T3K3X4Y2R214V2-0-9952CFD2A78839E3E0D0A44F87B2B132-4\" },\n" +
            "          \"Currency\": \"USD\",\n" +
            "          \"TotalFare\": \"744\",\n" +
            "          \"TotalFareTax\": \"52.00\",\n" +
            "          \"NumberType\": \"D\",\n" +
            "          \"Adult\": \"1\",\n" +
            "          \"AdultTotalFare\": \"372\",\n" +
            "          \"AdultTotalTax\": \"26.00\",\n" +
            "          \"Child\": \"1\",\n" +
            "          \"ChildTotalFare\": \"372\",\n" +
            "          \"ChildTotalTax\": \"26.00\",\n" +
            "          \"FareRule\": \"TD\",\n" +
            "          \"CabinClass\": \"Economy\",\n" +
            "          \"UserName\": \"bhaskar\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"Outbound\": {\n" +
            "          \"Duration\": \"2305\",\n" +
            "          \"Airline\": \"AI\",\n" +
            "          \"FlightDetails\": {\n" +
            "            \"FlightSegment\": [\n" +
            "              {\n" +
            "                \"DepartureDate\": \"201116\",\n" +
            "                \"DepartureTime\": \"2040\",\n" +
            "                \"ArrivalDate\": \"201116\",\n" +
            "                \"ArrivalTime\": \"2255\",\n" +
            "                \"FromLocation\": \"2255\",\n" +
            "                \"From\": { \"Location\": \"VGA\" },\n" +
            "                \"To\": {\n" +
            "                  \"Location\": \"DEL\",\n" +
            "                  \"Terminal\": \"3\"\n" +
            "                },\n" +
            "                \"MarketingCarrier\": \"AI\",\n" +
            "                \"OperatingCarrier\": \"AI\",\n" +
            "                \"FlightNumber\": \"468\",\n" +
            "                \"AircraftType\": \"319\",\n" +
            "                \"EquipmentType\": \"319\",\n" +
            "                \"ElectronicTicketing\": \"Y\",\n" +
            "                \"ProductDetailQualifier\": \"LCA\",\n" +
            "                \"Rbd\": \"U\",\n" +
            "                \"Cabin\": \"M\",\n" +
            "                \"AvlStatus\": \"9\",\n" +
            "                \"FareBasis\": \"UIP\",\n" +
            "                \"FareType\": { \"FlightSegment\": \"RP\" }\n" +
            "              },\n" +
            "              {\n" +
            "                \"DepartureDate\": \"211116\",\n" +
            "                \"DepartureTime\": \"1730\",\n" +
            "                \"ArrivalDate\": \"211116\",\n" +
            "                \"ArrivalTime\": \"1945\",\n" +
            "                \"FromLocation\": \"1945\",\n" +
            "                \"From\": {\n" +
            "                  \"Location\": \"DEL\",\n" +
            "                  \"Terminal\": \"3\"\n" +
            "                },\n" +
            "                \"To\": { \"Location\": \"HYD\" },\n" +
            "                \"MarketingCarrier\": \"AI\",\n" +
            "                \"OperatingCarrier\": \"AI\",\n" +
            "                \"FlightNumber\": \"126\",\n" +
            "                \"AircraftType\": \"77W\",\n" +
            "                \"EquipmentType\": \"77W\",\n" +
            "                \"ElectronicTicketing\": \"Y\",\n" +
            "                \"ProductDetailQualifier\": \"LCA\",\n" +
            "                \"Rbd\": \"B\",\n" +
            "                \"Cabin\": \"M\",\n" +
            "                \"AvlStatus\": \"9\",\n" +
            "                \"FareBasis\": \"BRT\",\n" +
            "                \"FareType\": { \"FlightSegment\": \"RP\" }\n" +
            "              }\n" +
            "            ],\n" +
            "            \"BaggageInformation\": {\n" +
            "              \"FreeAllowance\": \"25\",\n" +
            "              \"QuantityCode\": \"W\"\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        \"Recommendation\": {\n" +
            "          \"Reference\": { \"Outbound\": \"P3J4T3K3X4Y2R214S2-0-9952CFD2A78839E3E0D0A44F87B2B132-5\" },\n" +
            "          \"Currency\": \"USD\",\n" +
            "          \"TotalFare\": \"744\",\n" +
            "          \"TotalFareTax\": \"52.00\",\n" +
            "          \"NumberType\": \"D\",\n" +
            "          \"Adult\": \"1\",\n" +
            "          \"AdultTotalFare\": \"372\",\n" +
            "          \"AdultTotalTax\": \"26.00\",\n" +
            "          \"Child\": \"1\",\n" +
            "          \"ChildTotalFare\": \"372\",\n" +
            "          \"ChildTotalTax\": \"26.00\",\n" +
            "          \"FareRule\": \"TD\",\n" +
            "          \"CabinClass\": \"Economy\",\n" +
            "          \"UserName\": \"bhaskar\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    public SingleTon() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        mPref = this.getApplicationContext().getSharedPreferences("mPref", MODE_PRIVATE);
        try {
            jsonObj1 = new JSONObject(request);
            Log.i("jsonResponse",jsonObj1+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        printHashKey();
    }



    public void gmailIntialise(){
        Log.i("gmailIntialise","gmailIntialise");
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(obj)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }


    public void printHashKey(){
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "in.example.skybooker",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
