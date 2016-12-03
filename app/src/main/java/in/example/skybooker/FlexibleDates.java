package in.example.skybooker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FlexibleDates extends AppCompatActivity {
    TextView toolname;
    ImageView toolIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexible_dates);

        RelativeLayout toolbarlayout = (RelativeLayout) findViewById(R.id.SingleFlightTravelFlexibleDatesToolBar);
        toolname = (TextView) toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon = (ImageView) toolbarlayout.findViewById(R.id.toolBackImg);
        toolname.setText("Flexible Dates");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
