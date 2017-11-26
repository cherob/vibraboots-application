package felixschlegel.vibrabootsmapbox;

import android.os.AsyncTask;

import java.util.Observable;

/**
 * Created by robin on 26.11.2017.
 */

public class BlinkController extends Observable{
    public static final int BLINKNONE = 0;
    public static final int BLINKLEFT = 1;
    public static final int BLINKRIGHT = 2;
    private int state = BLINKNONE;

    public int getState() {
        return state;
    }

    public void setState(int s){
        state = s;
        setChanged();
        notifyObservers(state);
    }

    public void startRun(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++){
                    if(i > BLINKRIGHT){
                        i = BLINKNONE;
                    }
                    setState(i);

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
