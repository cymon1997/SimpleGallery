package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Router;
import io.fabric.sdk.android.Fabric;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        Crashlytics.getInstance().crash(); // Force a crash
        Fabric.with(this, new Crashlytics());

        Thread loading = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(Constant.APP_SPLASH_DURATION);
                } catch (InterruptedException e) {
                    finishLoading();
                } finally {
                    finishLoading();
                }
            }
        };
        loading.start();
    }

    private void finishLoading() {
        if (Constant.IS_DEBUG_MODE) {
            Router.gotoTest(this);
        } else {
            Router.gotoMain(this);
        }
        finish();
    }
}
