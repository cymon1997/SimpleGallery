package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common;

import android.app.Activity;
import android.content.Intent;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.LoginActivity;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.MainActivity;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.RegisterActivity;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.TestActivity;

public class Router {

    public static void gotoMain(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    public static void gotoLogin(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    public static void gotoRegister(Activity activity) {
        activity.startActivity(new Intent(activity, RegisterActivity.class));
    }

    public static void gotoTest(Activity activity) {
        activity.startActivity(new Intent(activity, TestActivity.class));
    }
}
