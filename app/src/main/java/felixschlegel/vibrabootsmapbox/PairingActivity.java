package felixschlegel.vibrabootsmapbox;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class PairingActivity extends AppCompatActivity
<<<<<<< HEAD
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
=======



        implements NavigationView.OnNavigationItemSelectedListener {
>>>>>>> 1ff2ddec190693ce6f2c18dd682e31c6baf4677a



@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pairing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button cbutton_left = (Button) findViewById(R.id.button_left);
        cbutton_left.setOnClickListener(this);
        Button cbutton_right = (Button) findViewById(R.id.button_right);
        cbutton_right.setOnClickListener(this);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //MapBox implementation
        //MapboxNavigation navigation = new MapboxNavigation(this, "sk.eyJ1IjoiZmxhZ3Bvc3QiLCJhIjoiY2phZjdvcGo2MXdpbzJ5anV6MHVyem92OCJ9.Mksti-6N6yCiDhyHM-lGcQ");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pairing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        //define the Activity's
        Intent navIntent = new Intent(this, NavigationActivity.class);
        Intent pairIntent = new Intent(this, PairingActivity.class);

        //clear duplicated Activity's
        navIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pairIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        int id = item.getItemId();

        if (id == R.id.nav_navigation) {
            startActivity(navIntent);
        } else if (id == R.id.nav_pairing) {
            startActivity(pairIntent);
        } else if (id == R.id.nav_credits) {

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        TextView text=(TextView)findViewById(R.id.text_state);
        switch (v.getId()) {
            case  R.id.button_left: {
                text.setText(R.string.title_state_online);
                break;
            }
            case R.id.button_right: {
                
                String connectivity_context = Context.WIFI_SERVICE;
                final WifiManager wifiManager = (WifiManager)getSystemService(connectivity_context);

                WifiAPController wifiAPController  = new WifiAPController();
                wifiAPController.wifiToggle("mHotspot", "12345678", wifiManager, this);

                text.setText(R.string.title_state_offline);
                break;
            }
        }
    }
}
