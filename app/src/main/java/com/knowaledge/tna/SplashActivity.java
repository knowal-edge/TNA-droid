package com.knowaledge.tna;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import static com.knowaledge.tna.Constants.constants.SPLASH_TIME_OUT;
import static com.knowaledge.tna.Constants.constants.cancel;
import static com.knowaledge.tna.Constants.constants.internet_check;
import static com.knowaledge.tna.Constants.constants.not_connect;
import static com.knowaledge.tna.Constants.constants.turn_on;

public class SplashActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (isInternetOn()) {
                    final Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    // close this activity
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                    builder.setCancelable(false);
                    builder.setMessage(internet_check);
                    builder.setPositiveButton(turn_on, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                        }
                    });
                    builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

            }
        }, SPLASH_TIME_OUT);
    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connec.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            // Check for network connections
            if (activeNetwork.getState() == NetworkInfo.State.CONNECTED ||
                    activeNetwork.getState() == NetworkInfo.State.CONNECTING ||
                    activeNetwork.getState() == NetworkInfo.State.CONNECTING ||
                    activeNetwork.getState() == NetworkInfo.State.CONNECTED) {

                return true;

            } else if (
                    activeNetwork.getState() == NetworkInfo.State.DISCONNECTED ||
                            activeNetwork.getState() == NetworkInfo.State.DISCONNECTED) {

                Toast.makeText(this, not_connect, Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return false;
    }

}