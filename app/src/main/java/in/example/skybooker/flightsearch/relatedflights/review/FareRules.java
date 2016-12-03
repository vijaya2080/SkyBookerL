package in.example.skybooker.flightsearch.relatedflights.review;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.example.skybooker.R;

public class FareRules extends AppCompatActivity {
TextView toolname;
    ImageView toolIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare_rules);
        //setToolBar();
        RelativeLayout toolbarlayout = (RelativeLayout) findViewById(R.id.fareRulesTool_bar);
        toolname = (TextView) toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon = (ImageView) toolbarlayout.findViewById(R.id.toolBackImg);
        toolname.setText("Fare Rules");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}
