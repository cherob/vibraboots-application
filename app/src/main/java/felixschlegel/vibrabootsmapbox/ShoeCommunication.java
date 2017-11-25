package felixschlegel.vibrabootsmapbox;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShoeCommunication {

    private final String USER_AGENT = "Mozilla/5.0";

    private String ipRight = "192.168.43.47";
    private String ipLeft = "";
    private String port = "1841";


    private boolean hasRightShoe = false;
    private boolean hasLeftShoe = false;

    public boolean checkStatusRight() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String result = "";
                try {
                    result = sendingGetRequest("http://" + ipRight + ":" + port + "/status");
                } catch (Exception e) {
                    Log.d(ShoeCommunication.class.getName(), "An Error Ocurred, Sending Data");
                }
                Log.d(ShoeCommunication.class.getName(), result);
            }
        });
        return true;
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
                    sendingGetRequest("http://" + ipRight + ":" + port + "/vibrate?times=" + times);
                }
                catch (Exception e) {
                    Log.d(ShoeCommunication.class.getName(), "An Error Ocurred, Sending Data");
                }
            }
        });
    }

    public void leftShoeAction(int times){
        try {
            sendingGetRequest("vibrate?times=" + times);
        }
        catch (Exception e) {
            Log.d(ShoeCommunication.class.getName(), "An Error Ocurred, Sending Data");
        }
    }

    // HTTP GET request
    private String sendingGetRequest(String msg) throws Exception {

        String urlString = (msg);

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // By default it is GET request
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

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
        System.out.println(response.toString());

        return response.toString();

    }





}