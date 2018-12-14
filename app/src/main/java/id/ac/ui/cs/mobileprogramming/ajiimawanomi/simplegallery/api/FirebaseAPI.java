package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core.ResponseReceiver;

public class FirebaseAPI {
    private static FirebaseAuth firebase;

    public static FirebaseAuth getInstance() {
        firebase = FirebaseAuth.getInstance();
        return firebase;
    }

    public static FirebaseUser getCurrentUser() {
        return getInstance().getCurrentUser();
    }

    public static void register(Activity activity, final ResponseReceiver receiver, String email, String password) {
        getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Register success, auto login
                            receiver.onReceive(Constant.FIREBASE_REGISTER_REQUEST, Constant.API_SUCCESS, getCurrentUser());
                        } else {
                            // Register failed
                            receiver.onReceive(Constant.FIREBASE_REGISTER_REQUEST, Constant.API_ERROR_UNKNOWN, null);
                        }
                    }
        });
    }

    public static void login(Activity activity, final ResponseReceiver receiver, String email, String password) {
        getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login success
                            receiver.onReceive(Constant.FIREBASE_LOGIN_REQUEST, Constant.API_SUCCESS, getCurrentUser());
                        } else {
                            // Login failed
                            receiver.onReceive(Constant.FIREBASE_LOGIN_REQUEST, Constant.API_ERROR_NOT_FOUND, null);
                        }
                    }
                });
    }

    public static void logout(ResponseReceiver receiver) {
        getInstance().signOut();
        receiver.onReceive(Constant.FIREBASE_LOGOUT_REQUEST, Constant.API_SUCCESS, getCurrentUser());
    }
}
