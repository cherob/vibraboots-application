package felixschlegel.vibrabootsmapbox;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShoeCommunication {

    private final String USER_AGENT = "Mozilla/5.0";

    private boolean hasRightShoe = true;
    private boolean hasLeftShoe = true;

    public boolean getStatusRight() {
        return hasRightShoe;
    }

    public boolean getStatusLeft() {
        return hasLeftShoe;
    }
    public void rightShoeAction(){
        try {
            sendingGetRequest("RightShoeAction");
        }
        catch (Exception e) {
            Log.d(ShoeCommunication.class.getName(), "An Error Ocurred, Sending Data");
        }
    }

    public void leftShoeAction(){
        try {
            sendingGetRequest("LeftShoeAction");
        }
        catch (Exception e) {
            Log.d(ShoeCommunication.class.getName(), "An Error Ocurred, Sending Data");
        }
    }

    public static void main(String[] args) throws Exception {

        ShoeCommunication http = new ShoeCommunication();

    }

    // HTTP GET request
    private void sendingGetRequest(String msg) throws Exception {

        String urlString = ("http://localhost:8080/" + msg);

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

    }





}