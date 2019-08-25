package com.example.amitkrishna.robo_car;

import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static com.example.amitkrishna.robo_car.Utils.TAG;
import static com.example.amitkrishna.robo_car.MainActivity.Backgroundrequesttask;

/**
 * Created by Amit Krishna on 30-05-2017.
 */

class mylistener implements View.OnTouchListener {
    private View toggleview;
    private float val1=0;
    private float val2=0;


    mylistener(View toggleview){
        super();
        this.toggleview=toggleview;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int eventval= MotionEventCompat.getActionMasked(event);
        if(eventval==MotionEvent.ACTION_DOWN ){
            if(toggleview==null){
                Log.i(TAG[0], "onTouch: btn pressed");

                String cmd;
                switch (v.getId()){
                    case R.id.primeLeftArrow:
                        cmd="move_left";
                        break;
                    case R.id.primearrowright:
                        cmd="move_right";
                        break;
                    case R.id.primearrowup:
                        cmd="move_forward";
                        break;
                    case R.id.primeDownArrow:
                        cmd="move_backward";
                        break;
                    default:
                        cmd="none";
                }
                if(cmd!="none"){
              new Backgroundrequesttask(v.getContext()).execute(Utils.URL,cmd);}

           }
           else {val1=event.getX();}
            return true;
        }
        else  if (eventval==MotionEvent.ACTION_UP){
            if(toggleview==null){
                Log.i(TAG[0], "onTouch: btn released");
                new Backgroundrequesttask(v.getContext()).execute(Utils.URL,"stop");
            }
            else {val2=event.getX();}

        }

        if(val1-300>val2 && val2>0 && v.getId()==R.id.viewgroup1){
            int id1=v.getId();
            int id2=toggleview.getId();
            Log.i(TAG[0], "onTouch:al1="+ String.valueOf(val1)+"val2="+String.valueOf(val2));
            Log.i(TAG[0],"id1="+String.valueOf(id1)+"id2="+String.valueOf(id2));

                v.setVisibility(View.GONE);
                toggleview.setVisibility(View.VISIBLE);

        }
        else if(val1+300<val2 && val2>0 && v.getId()==R.id.viewgroup2){
            int id1=v.getId();
            int id2=toggleview.getId();
            Log.i(TAG[0], "onTouch:al1="+ String.valueOf(val1)+"val2="+String.valueOf(val2));
            Log.i(TAG[0],"id1="+String.valueOf(id1)+"id2="+String.valueOf(id2));
            v.setVisibility(View.GONE);
            toggleview.setVisibility(View.VISIBLE);

        }

        return true;
    }
}
