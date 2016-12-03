package in.example.skybooker.flightsearch.fragments.multicity;

import android.view.View;

import java.io.Serializable;

/**
 * Created by nbhag on 12/2/2016.
 */
public class ViewSerializer implements Serializable {
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public transient View view;
}
