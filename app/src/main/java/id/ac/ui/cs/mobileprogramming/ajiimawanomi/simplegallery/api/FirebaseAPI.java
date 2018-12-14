package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAPI {

    private static FirebaseAuth firebase;

    public static FirebaseAuth getInstance() {
        firebase = FirebaseAuth.getInstance();
        return firebase;
    }

    public static FirebaseUser getCurrentUser() {
        return getInstance().getCurrentUser();
    }

    public static void register(Activity activity, String email, String password) {
        getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Register success, auto login
                        } else {
                            // Register failed
                        }
                    }
        });
    }

    public static void login(Activity activity, String email, String password) {
        getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Login success
                        } else {
                            // Login failed
                        }
                    }
                });
    }

    public static void logout() {
        getInstance().signOut();
    }
}
