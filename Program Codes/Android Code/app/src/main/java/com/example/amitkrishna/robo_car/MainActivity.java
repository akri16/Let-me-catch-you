package com.example.amitkrishna.robo_car;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import static com.example.amitkrishna.robo_car.Utils.TAG;
import static com.example.amitkrishna.robo_car.Utils.URL;


public class MainActivity extends AppCompatActivity {
private static View main;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main=findViewById(R.id.main);



         View view=findViewById(R.id.viewgroup2);
         View view1=findViewById(R.id.viewgroup1);

        ImageView btnup=(ImageView) findViewById(R.id.primearrowup);
        ImageView btndown=(ImageView) findViewById(R.id.primeDownArrow);
        ImageView btnleft=(ImageView) findViewById(R.id.primeLeftArrow);
        ImageView btnright=(ImageView) findViewById(R.id.primearrowright);

        view.setOnTouchListener(new mylistener(view1));
        view1.setOnTouchListener(new mylistener(view));

        btnup.setOnTouchListener(new mylistener(null));
        btndown.setOnTouchListener(new mylistener(null));
        btnleft.setOnTouchListener(new mylistener(null));
        btnright.setOnTouchListener(new mylistener(null));


    }
    public void onClick(View view){
        String cmd;
        switch (view.getId()){
            case R.id.left:
                cmd="tweezers_loosen";
                break;
            case R.id.right:
                cmd="tweezers_tighten";
                break;
            case R.id.up:
                cmd="arm_up";
                break;
            case R.id.down:
                cmd="arm_down";
                break;
            default:
                cmd="none";
        }
        if(cmd!="none"){
        Backgroundrequesttask backgroundrequesttask=new Backgroundrequesttask(this);
        backgroundrequesttask.execute(URL,cmd);}


    }

   public static class Backgroundrequesttask extends AsyncTask<String,Void,String>{
       private Context context;

       public Backgroundrequesttask(Context context){
           super();
           this.context=context;
       }

       @Override
       protected void onPostExecute(String s) {

          if(s=="error1"){
             Toast.makeText(context,"Robo_car is not connected!",Toast.LENGTH_LONG).show();

         }
         else if(s=="error2"){
             Intent intent=new Intent(context,launcheractivity.class);
             intent=intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
             context.startActivity(intent);


         }
       }

       @Override
       protected String doInBackground(String... params) {
           return Utils.request(params[0],params[1],context);
       }
   }

}
