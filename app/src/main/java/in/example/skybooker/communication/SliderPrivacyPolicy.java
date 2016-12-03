package in.example.skybooker.communication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import in.example.skybooker.R;

public class SliderPrivacyPolicy extends AppCompatActivity {

    TextView toolname;
    ImageView toolIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_privacy_policy);

        setToolbar();


        /*RelativeLayout toolbarlayout=(RelativeLayout)findViewById(R.id.sliderPrivacyToolBar);
        toolname=(TextView)toolbarlayout.findViewById(R.id.toolBarTitle);
        toolIcon=(ImageView)toolbarlayout.findViewById(R.id.toolBackImg) ;

        toolname.setText("Privacy Policy");
        toolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
    }

    private void setToolbar() {
        Toolbar tb = (Toolbar) findViewById(R.id.privacyPolicyTool_bar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Privacy Policy");
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case  R.id.action_settings: showDialog();
                return true;
            case R.id.terms:return true;
            case R.id.privacy:return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SliderPrivacyPolicy.this);
        dialog.setMessage("Want to make a call?");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {

            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                // TODO Auto-generated method stub

            }
        });
        dialog.show();
    }
}
