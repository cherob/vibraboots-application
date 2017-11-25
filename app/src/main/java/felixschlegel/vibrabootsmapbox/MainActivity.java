package felixschlegel.vibrabootsmapbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MapBox implementation
        MapboxNavigation navigation = new MapboxNavigation(this, "sk.eyJ1IjoiZmxhZ3Bvc3QiLCJhIjoiY2phZjdvcGo2MXdpbzJ5anV6MHVyem92OCJ9.Mksti-6N6yCiDhyHM-lGcQ");

    }
}
