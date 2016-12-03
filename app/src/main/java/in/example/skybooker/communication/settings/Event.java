package in.example.skybooker.communication.settings;

/**
 * Created by vijay on 10/24/2016.
 */
import android.app.Activity;

/**
 * Created by vijay on 10/21/2016.
 */
public class Event extends Activity{
    String name;
    Boolean radio,selected;

    public Event()
    {

    }

    public Event(String name)
    {
        this.name=name;
    }

    public Event(String name, Boolean radio) {
        this.name = name;
        this.radio = radio;

    }
    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRadio() {
        return radio;
    }

    public void setRadio(Boolean radio) {
        this.radio = radio;
    }

}
