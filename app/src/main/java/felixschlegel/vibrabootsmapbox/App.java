package felixschlegel.vibrabootsmapbox;

import android.app.Application;

/**
 * Created by robin on 25.11.2017.
 */

public class App extends Application {
    private static App mInstance = null;
    private ShoeCommunication mShoes;

    public static App getInstance() {
        return mInstance;
    }

    public ShoeCommunication getShoeCommunication(){
        return mShoes;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mShoes = new ShoeCommunication();
        mShoes.runStatusChecks();
    }
}
