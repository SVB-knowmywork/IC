package interestcalculator.knowmywork.com.ic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by SVB on 19-07-2015.
 */
public class SplashScreen extends Activity {

    // SPLASH SCREEN TIME OUT VALUE
    private static int TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            // THIS METHOD WILL BE EXECUTED ONCE TIMER IS OUT
            @Override
            public void run() {
                // STARTS MAIN ACTIVITY
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }

}

