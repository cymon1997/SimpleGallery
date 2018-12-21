package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common;

public abstract class Constant {
    public static final String PACKAGE_NAME = "id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery";

//    CONTENT PROVIDER
    public static final String PROVIDER_TABLE_GALLERY = "gallery";
    public static final int PROVIDER_TABLE_ALL = 2000;
    public static final int PROVIDER_TABLE_SINGLE = 2001;

//    ROOM DATABASE
    public static final String ROOM_DATABASE_LOGIN_LOG = "loginlog";
    public static final boolean IS_DEBUG_MODE = false;
    public static final int APP_SPLASH_DURATION = 3000;

//    DATABASE API
    public static final int DATABASE_LIST_ALL_REQUEST = 3001;
    public static final int DATABASE_LIST_BY_EMAIL = 3002;
    public static final int DATABASE_INSERT_REQUEST = 3003;

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
