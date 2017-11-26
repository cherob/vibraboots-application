package felixschlegel.vibrabootsmapbox;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import static android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS;

public class PairingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    static boolean wifiIsActive;
    private ImageView leftShoe;
    private ImageView rightShoe;
    private Button cbutton_left;
    private Button cbutton_state;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ImageView wifi;
    private TextView button;
    private Intent navIntent;
    private Intent pairIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_pairing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        leftShoe = (ImageView) findViewById(R.id.image_stateLeft);
        rightShoe = (ImageView) findViewById(R.id.image_stateRight);

        cbutton_left = (Button) findViewById(R.id.button_find);
        cbutton_left.setOnClickListener(this);
        cbutton_state = (Button) findViewById(R.id.button_state);
        cbutton_state.setOnClickListener(this);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        wifiIsActive = WifiAccessManager.getWifiApState(this);

        wifi = (ImageView) findViewById(R.id.image_wifi);
        button = (TextView)findViewById(R.id.button_state);
        presetWiFiState(button, wifi);

        //MapBox implementation
        //MapboxNavigation navigation = new MapboxNavigation(this, "sk.eyJ1IjoiZmxhZ3Bvc3QiLCJhIjoiY2phZjdvcGo2MXdpbzJ5anV6MHVyem92OCJ9.Mksti-6N6yCiDhyHM-lGcQ");

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        if (App.getInstance().getShoeCommunication().getStatusLeft() == true) {
            leftShoe.setImageResource(R.drawable.green_left);
        } else if (App.getInstance().getShoeCommunication().getStatusLeft() == false) {
            leftShoe.setImageResource(R.drawable.red_left);
        } else {
            leftShoe.setImageResource(R.drawable.inactive_left);
        }



        if (App.getInstance().getShoeCommunication().getStatusRight() == true) {
            rightShoe.setImageResource(R.drawable.green_right);
        } else if (App.getInstance().getShoeCommunication().getStatusRight() == false) {
            rightShoe.setImageResource(R.drawable.red_right);
        } else {
            rightShoe.setImageResource(R.drawable.inactive_right);
        }
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
        navIntent = new Intent(this, NavigationActivity.class);
        pairIntent = new Intent(this, PairingActivity.class);

        //clear duplicated Activity's
        navIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pairIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        int id = item.getItemId();

        if (id == R.id.nav_navigation) {
            startActivity(navIntent);
        } else if (id == R.id.nav_pairing) {
            startActivity(pairIntent);
        } else if (id == R.id.nav_credits) {

        } else if (id == R.id.nav_perm){
            Intent getPerm = new Intent(ACTION_MANAGE_WRITE_SETTINGS);
            startActivity(getPerm);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_find: {
                break;
            }

            case R.id.button_state: {
                wifi = (ImageView) findViewById(R.id.image_wifi);
                button = (TextView)findViewById(R.id.button_state);
                updateWiFiState(button, wifi);
                break;
            }
        }
    }

    private void presetWiFiState(TextView button, ImageView wifi) {
        if(!wifiIsActive) {
            button.setText(R.string.title_state_online);
            wifi.setImageResource(R.drawable.wi_fi);
            App.getInstance().getShoeCommunication().runStatusChecks();
        }else if(wifiIsActive){
            button.setText(R.string.title_state_offline);
            wifi.setImageResource(R.drawable.wi_fi_off);
            leftShoe.setImageResource(R.drawable.inactive_left);
            rightShoe.setImageResource(R.drawable.inactive_right);
        }
    }

    private void updateWiFiState(TextView button, ImageView wifi) {
        if(!wifiIsActive) {
            WifiAccessManager.setWifiApState(this, true);
            button.setText(R.string.title_state_online);
            wifi.setImageResource(R.drawable.wi_fi);
            wifiIsActive = true;
            App.getInstance().getShoeCommunication().runStatusChecks();
        }else if(wifiIsActive){
            WifiAccessManager.setWifiApState(this, false);
            button.setText(R.string.title_state_offline);
            wifi.setImageResource(R.drawable.wi_fi_off);
            wifiIsActive = false;
            leftShoe.setImageResource(R.drawable.inactive_left);
            rightShoe.setImageResource(R.drawable.inactive_right);
        }
    }
}
