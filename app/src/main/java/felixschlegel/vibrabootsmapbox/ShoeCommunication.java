package felixschlegel.vibrabootsmapbox;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class ShoeCommunication extends Observable{

    private final String USER_AGENT = "Mozilla/5.0";


    private String ipRight = "";
    private String ipLeft = "";
    private String port = "800";

    private boolean hasRightShoe = false;
    private boolean hasLeftShoe = false;
    private boolean isScanRunning = false;

    public  boolean getIsScanRunning(){
        return isScanRunning;
    }

    public void ipScan() {
            AsyncTask.execute(new Runnable() {

                @Override
                public void run() {
                    if(isScanRunning){
                        return;
                    }
                    isScanRunning = true;
                    boolean rightFound = false;
                    boolean leftFound = false;
                    for(int i=2; i<256; i++) {
                        String result = "";
                        try {
                            result = sendingGetRequest("http://192.168.43." + i + ":" + port + "/status", 300);
                        } catch (Exception e) {
                            Log.d(ShoeCommunication.class.getName(), "No shoe found at http://192.168.43." + i);
                        }
                        if (result.equals("right")) {
                            ipRight = "192.168.43." + i;
                            checkStatusRight();
                            rightFound = true;
                        }
                        else if (result.equals("left")) {
                            ipLeft = "192.168.43." + i;
                            checkStatusLeft();
                            leftFound = true;
                        }
                        if(rightFound && leftFound) {
                            break;
                        }
                    }
                    setChanged();
                    notifyObservers();
                    isScanRunning = false;
                }
            });
    }

    public void runStatusChecks() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                checkStatusRight();
                                checkStatusLeft();
                            }
                        });
                    }
                });
            }
        };
        timer.schedule(task, 0, 10000); //it executes this every 1000ms


    }

    private void checkStatusRight() {
        String result = "";
        try {
            result = sendingGetRequest("http://" + ipRight + ":" + port + "/status", 5000);
        } catch (Exception e) {
            Log.d(ShoeCommunication.class.getName(), "An Error Ocurred, Sending Data");
        }
        boolean before = hasRightShoe;
        if (result.equals("right")) {
            hasRightShoe = true;
        } else {
            hasRightShoe = false;
        }
        if( hasRightShoe!=before ) {
            setChanged();
            notifyObservers();
        }
    }

    private void checkStatusLeft() {
        String result = "";
        try {
            result = sendingGetRequest("http://" + ipLeft + ":" + port + "/status", 5000);
        } catch (Exception e) {
            Log.d(ShoeCommunication.class.getName(), "An Error Ocurred, Sending Data");
        }

        boolean before = hasLeftShoe;
        if (result.equals("left")) {
            hasLeftShoe = true;
        } else {
            hasLeftShoe = false;
        }
        if( hasLeftShoe!=before ) {
            setChanged();
            notifyObservers();
        }
    }

    public boolean getStatusRight() {
        return hasRightShoe;
    }

    public boolean getStatusLeft() {
        return hasLeftShoe;
    }

    public void rightShoeAction(final int times){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    sendingGetRequest("http://" + ipRight + ":" + port + "/vibrate?times=" + times, 5000);
                }
                catch (Exception e) {
                    Log.d(ShoeCommunication.class.getName(), "An Error Ocurred, Sending Data");
                }
            }
        });
    }

    public void leftShoeAction(int times){
        try {
            sendingGetRequest("vibrate?times=" + times, 5000);
        }
        catch (Exception e) {
            Log.d(ShoeCommunication.class.getName(), "An Error Ocurred, Sending Data");
        }
    }

    // HTTP GET request
    private String sendingGetRequest(String msg, int conTimeout) throws Exception {

        String urlString = (msg);

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // By default it is GET request
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        con.setConnectTimeout(conTimeout);

        int responseCode = con.getResponseCode();
        System.out.println("Sending get request : "+ url);
        System.out.println("Response code : "+ responseCode);

        // Reading response from input Stream
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        // System.out.println(response.toString());
        return response.toString();
 }





}