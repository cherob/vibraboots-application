package felixschlegel.vibrabootsmapbox;

import android.app.Application;

/**
 * Created by robin on 25.11.2017.
 */

public class App extends Application {
    private static App mInstance = null;
    private ShoeCommunication mShoes;
    private BlinkController mBlink = new BlinkController();

    public static App getInstance() {
        return mInstance;
    }

    public ShoeCommunication getShoeCommunication(){
        return mShoes;
    }

    public BlinkController getBlinkController(){
        return mBlink;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mShoes = new ShoeCommunication();
    }
}
