package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common;

public class Constant {
    public static final boolean IS_DEBUG_MODE = false;

    public static final int APP_SPLASH_DURATION = 3000;

//    FIREBASE API
    public static final int FIREBASE_LOGIN_REQUEST = 1001;
    public static final int FIREBASE_REGISTER_REQUEST = 1002;
    public static final int FIREBASE_LOGOUT_REQUEST = 1003;

//    HTTP RESULT
    public static final int API_SUCCESS = 200;
    public static final int API_ERROR_NOT_FOUND = 404;
    public static final int API_ERROR_INTERNAL_SERVER = 500;
    public static final int API_ERROR_UNKNOWN = 503;

//    SHARED PREFERENCES
    public static final String SHARED_PREFERENCES_USERDATA = "USER";
    public static final String SHARED_PREFERENCES_USERDATA_UID = "USER_UID";
    public static final String SHARED_PREFERENCES_USERDATA_EMAIL = "USER_EMAIL";
}
