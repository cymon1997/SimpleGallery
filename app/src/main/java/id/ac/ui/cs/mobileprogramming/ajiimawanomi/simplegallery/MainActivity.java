package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Router;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core.AuthViewModel;

public class MainActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);

        Observer<FirebaseUser> authObserver = new Observer<FirebaseUser>() {
            @Override
            public void onChanged(@Nullable FirebaseUser firebaseUser) {
                redirectIfAuth(firebaseUser);
            }
        };

        authViewModel.getInstance().observe(this, authObserver);
    }

    private void redirectIfAuth(FirebaseUser user) {
        if (user == null) {
            Router.gotoLogin(this);
            finish();
        }
    }
}
