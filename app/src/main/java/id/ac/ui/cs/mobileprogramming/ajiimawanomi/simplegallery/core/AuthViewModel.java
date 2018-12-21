package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api.FirebaseAPI;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.BaseResponse;

public class AuthViewModel extends ViewModel implements BaseViewModel<FirebaseUser>, ResponseReceiver<FirebaseUser> {
    private MutableLiveData<BaseResponse<FirebaseUser>> response;

    @Override
    public MutableLiveData<BaseResponse<FirebaseUser>> getInstance() {
        if (response == null) {
            response = new MutableLiveData<>();
        }
        return response;
    }

    @Override
    public void update(BaseResponse<FirebaseUser> response) {
        getInstance().setValue(response);
    }

    public void login(Activity activity, String email, String password) {
        FirebaseAPI.login(activity, this, email, password);
    }

    public void register(Activity activity, String email, String password) {
        FirebaseAPI.register(activity, this, email, password);
    }

    @Override
    public void onReceive(int requestCode, int resultCode, FirebaseUser user) {
        BaseResponse<FirebaseUser> response = new BaseResponse<>(user);
        switch (requestCode) {
            case Constant.FIREBASE_LOGIN_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    response.setStatus(resultCode);
                    update(response);
                } else {
                    response.setStatus(resultCode);
                    update(response);
                }
                break;
            case Constant.FIREBASE_REGISTER_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    response.setStatus(resultCode);
                    update(response);
                } else {
                    response.setStatus(resultCode);
                    update(response);
                }
                break;
            case Constant.FIREBASE_LOGOUT_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    response.setStatus(resultCode);
                    update(response);
                } else {
                    response.setStatus(resultCode);
                    update(response);
                }
                break;
        }
    }
}
