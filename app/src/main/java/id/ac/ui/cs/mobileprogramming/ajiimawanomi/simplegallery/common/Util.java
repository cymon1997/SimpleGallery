package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

public abstract class Util {

    public static boolean isEmpty(String text) {
        return TextUtils.isEmpty(text);
    }

    public static boolean isValidEmail(CharSequence text) {
        return (!TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches());
    }

    public static boolean isBlocked(String ssid) {
        ssid = ssid.replace("\"", "");
        String[] secured = new String[]{ "jangan hot" };
        boolean blocked = true;
        for (String i : secured) {
//            Log.d("SSID IS BLOCKED", " : ["+ ssid +"] ["+ i +"] THEN [" + i.equals(ssid) + "]");
            if (i.equals(ssid)) {
                blocked = false;
            }
        }
        return blocked;
    }
}
