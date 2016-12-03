package in.example.skybooker.flightsearch.relatedflights.travelers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.example.skybooker.R;

public class TSAPrivacyPolicy extends AppCompatActivity {
    TextView toolname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tsaprivacy_policy);

            RelativeLayout toolbarlayout = (RelativeLayout) findViewById(R.id.travellersToolBar);
        toolname = (TextView) toolbarlayout.findViewById(R.id.toolBarTitle);
        ImageView toolIcon = (ImageView) toolbarlayout.findViewById(R.id.toolBackImg);
        toolname.setText("TSA Privacy Policy FAQs");

        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
