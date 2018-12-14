package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common;

import android.text.TextUtils;
import android.util.Patterns;

public class Util {

    public static boolean isEmpty(String text) {
        return TextUtils.isEmpty(text);
    }

    public static boolean isValidEmail(CharSequence text) {
        return (!TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches());
    }
}
