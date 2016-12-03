package in.example.skybooker.flightsearch.relatedflights.review;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.example.skybooker.R;

public class BaggedFees extends AppCompatActivity {
    TextView toolname;
    ImageView toolIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagged_fees);

        //setToolBar();
        RelativeLayout toolbarlayout = (RelativeLayout) findViewById(R.id.tool_bar);

        toolname.setText("Bagged Fees");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}
