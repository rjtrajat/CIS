package com.cureissure.cis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

/**
 * Created by RAJAT SINGH on 11/20/2016.
 */

public class splash extends Activity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private final int SPLASH_DISPLAY_CHANGE = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.splashid);




        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
               LinearLayout linearLayout = (LinearLayout)findViewById(R.id.splashid);
//                linearLayout.setBackgroundResource(R.drawable.splashone);
                TransitionDrawable transition = (TransitionDrawable) linearLayout.getBackground();
                transition.startTransition(1000);
            }
        }, SPLASH_DISPLAY_CHANGE);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(splash.this,MainActivity.class);
                splash.this.startActivity(mainIntent);
                splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
