package felixschlegel.vibrabootsmapbox;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import javax.crypto.Cipher;

/**
 * Created by robin on 25.11.2017.
 */

public class WifiAccessManager {

    private static final String SSID = "navshoes";

    public static boolean getWifiApState(Context context){

        WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if(mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED){
            return true;
        }
        return false;
    }

    public static boolean setWifiApState(Context context, boolean enabled) {
        //config = Preconditions.checkNotNull(config);
        try {
            WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (enabled) {
                mWifiManager.setWifiEnabled(false);
            }
            WifiConfiguration conf = getWifiApConfiguration();
            mWifiManager.addNetwork(conf);

            while (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING){}
            while (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLING){}
            return (Boolean) mWifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class).invoke(mWifiManager, conf, enabled);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static WifiConfiguration getWifiApConfiguration() {
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID =  SSID;
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        return conf;
    }
}
