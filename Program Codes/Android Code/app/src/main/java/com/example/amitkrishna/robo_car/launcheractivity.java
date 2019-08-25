package com.example.amitkrishna.robo_car;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import static com.example.amitkrishna.robo_car.Utils.TAG;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class launcheractivity extends AppCompatActivity {
    private  Button view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcheractivity);
        TAG[0]="MyListener";
        TAG[1]="MainActivity";
        TAG[2]="Utils";
        TAG[3]="LauncherActivity";
         view1=(Button) findViewById(R.id.btn);

       new launchertask().execute();

    }
    public void onClick(View view){
        String btntext =(String) view1.getText();
        if (btntext=="Continue"){
            Intent intent=new Intent(this,MainActivity.class);
            intent=intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        }
        else {new launchertask().execute();}

    }
    private class launchertask extends AsyncTask<Void,Void,Void>{
        private View view=findViewById(R.id.progressbar);
        private  TextView status=((TextView)findViewById(R.id.status));

        @Override
        protected void onPreExecute() {
            status.setText("Setting things up....");
            status.setTextColor(ContextCompat.getColor(launcheractivity.this,android.R.color.white));
            view.setVisibility(View.VISIBLE);
            view1.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{Thread.sleep(3000); }
            catch(InterruptedException e){
                Log.e(TAG[3], "onCreate:",e);}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            ConnectivityManager connectivity = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkinfo=  connectivity.getActiveNetworkInfo();

            if(networkinfo!=null && networkinfo.isConnected() && ("\"JioFi3_2875D4\"".equals(networkinfo.getExtraInfo()))){(status).setText("Done");
            view1.setText("Continue");
            view.setVisibility(View.INVISIBLE);
            view1.setVisibility(View.VISIBLE);}

            else if (networkinfo==null || networkinfo.isConnected() ||  networkinfo.getExtraInfo()!="\"JioFi3_2875D4\""){


                    status.setText("You are not connected to the local host JioFi3_2875D4\n"+ "to which Robo_Car is connected to \n"+"please connect to JioFi3_2875D4 to continue");
                status.setTextColor(ContextCompat.getColor(launcheractivity.this,android.R.color.holo_red_light));
                view.setVisibility(View.INVISIBLE);
                view1.setText("Retry");
                view1.setVisibility(View.VISIBLE);

            }


        }
    }


}