package in.example.skybooker.flightsearch.relatedflights.payment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.example.skybooker.R;

public class TravelAssist extends AppCompatActivity {
TextView toolname;
    ImageView toolIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_assist);
        //setToolBar();
        RelativeLayout toollayout=(RelativeLayout)findViewById(R.id.travelAssistTool_bar);
        toolname = (TextView) toollayout.findViewById(R.id.toolBarTitle);
        toolIcon = (ImageView) toollayout.findViewById(R.id.toolBackImg);
        toolname.setText("Travel Assist");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
