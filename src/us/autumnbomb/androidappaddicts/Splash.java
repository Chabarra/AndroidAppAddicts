package us.autumnbomb.androidappaddicts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity {
    private long ms=0;
    private long splashTime=1500;
    private boolean splashActive = true;
    private boolean paused=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hides the titlebar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash);

        Thread mythread = new Thread() {
            public void run() {
                try {
                    while (splashActive && ms < splashTime) {
                        if(!paused)
                            ms=ms+500;
                        sleep(500);
                    }
                } catch(Exception e) {}
                finally {
                    Intent intent = new Intent(Splash.this, Home.class);
                    startActivity(intent);
                }
            }
        };
        mythread.start();
    }
}
