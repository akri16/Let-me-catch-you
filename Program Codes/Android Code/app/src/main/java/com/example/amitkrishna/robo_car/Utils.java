package com.example.amitkrishna.robo_car;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Amit Krishna on 31-05-2017.
 */

public final class Utils {
    public static final String[] TAG=new String[4];
    public static final String URL="http://192.168.225.235";

    public static String request(String reqadd, String cmd, Context context){

        try {
            URL url=new URL(reqadd+"/"+cmd);
            HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode()==200){
               BufferedReader inputStreamReader= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String val = inputStreamReader.readLine();
                Log.i(TAG[2], "request:val="+val);
                return val;
            }
            else if(urlConnection.getResponseCode()==404){

                return "error1";

            }
            Log.i(TAG[2], "request:responsecode="+String.valueOf(urlConnection.getResponseCode()));


        }
        catch (MalformedURLException e){
            Log.e(TAG[2], "request: bad url",e );
            return null;

        }
        catch (IOException e){
            if(e.toString().contains("No route to host")){return "error1";}
            Log.e(TAG[2],"request: can`t connect",e);


            return "error2";


        }
      return null;
    }




}
