package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.DetailActivity;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.LoginActivity;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.MainActivity;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.RegisterActivity;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.TestActivity;

import static android.content.Context.MODE_PRIVATE;

public abstract class Router {

    public static void gotoMain(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    public static void gotoLogin(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    public static void gotoRegister(Activity activity) {
        activity.startActivity(new Intent(activity, RegisterActivity.class));
    }

    public static void gotoDetailImage(Activity activity, String imgUrl) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailActivity.INTENT_IMAGE_URL, imgUrl);
        activity.startActivity(intent);
    }

    public static void gotoTest(Activity activity) {
        activity.startActivity(new Intent(activity, TestActivity.class));
    }

    public static void saveData(Activity activity, String uid, String email) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(Constant.SHARED_PREFERENCES_USERDATA, Context.MODE_PRIVATE).edit();
        editor.putString(Constant.SHARED_PREFERENCES_USERDATA_UID, uid);
        editor.putString(Constant.SHARED_PREFERENCES_USERDATA_EMAIL, email);
        editor.apply();
    }

    public static void clearData(Activity activity) {
        SharedPreferences.Editor auth = activity.getSharedPreferences(Constant.SHARED_PREFERENCES_USERDATA, Context.MODE_PRIVATE).edit();
        auth.clear();
        auth.apply();
        SharedPreferences.Editor setting = activity.getSharedPreferences(Constant.SHARED_PREFERENCES_SAFE_MODE, Context.MODE_PRIVATE).edit();
        setting.clear();
        setting.apply();
    }

    public static void safeModeActivate(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.SHARED_PREFERENCES_SAFE_MODE, Context.MODE_PRIVATE).edit();
        editor.putBoolean(Constant.SHARED_PREFERENCES_SAFE_MODE_STATUS, true);
        editor.apply();
    }

    public static void safeModeDeactivate(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.SHARED_PREFERENCES_SAFE_MODE, Context.MODE_PRIVATE).edit();
        editor.putBoolean(Constant.SHARED_PREFERENCES_SAFE_MODE_STATUS, false);
        editor.apply();
    }

    public static boolean safeModeStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Constant.SHARED_PREFERENCES_SAFE_MODE, MODE_PRIVATE);
        return preferences.getBoolean(Constant.SHARED_PREFERENCES_SAFE_MODE_STATUS, false);
    }
}
