package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;

public class AuthViewModel extends ViewModel implements BaseViewModel<FirebaseUser>, ResponseReceiver<FirebaseUser> {
    private MutableLiveData<FirebaseUser> currentUser;

    @Override
    public MutableLiveData<FirebaseUser> getInstance() {
        if (currentUser == null) {
            currentUser = new MutableLiveData<>();
        }
        return currentUser;
    }

    @Override
    public void update(FirebaseUser user) {
        getInstance().setValue(user);
    }

    @Override
    public void onReceive(int requestCode, int resultCode, FirebaseUser response) {
        switch (requestCode) {
            case Constant.FIREBASE_LOGIN_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    update(response);
                }
                break;
            case Constant.FIREBASE_REGISTER_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    update(response);
                }
                break;
            case Constant.FIREBASE_LOGOUT_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    update(response);
                }
                break;
        }
    }
}
