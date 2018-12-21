package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;

import com.google.firebase.auth.FirebaseUser;

import java.lang.ref.WeakReference;
import java.util.Date;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api.FirebaseAPI;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api.LoginLogDatabase;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.BaseResponse;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.LoginLog;

public class AuthViewModel extends ViewModel implements BaseViewModel<FirebaseUser>, ResponseReceiver<FirebaseUser> {
    private MutableLiveData<BaseResponse<FirebaseUser>> response;
    private WeakReference<Context> context;

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
        this.context = new WeakReference<>(activity.getApplicationContext());
        FirebaseAPI.login(activity, this, email, password);
    }

    public void register(Activity activity, String email, String password) {
        this.context = new WeakReference<>(activity.getApplicationContext());
        FirebaseAPI.register(activity, this, email, password);
    }

    private void addLoginLog(FirebaseUser user) {
        LoginLog log = new LoginLog(user.getEmail(), new Date());
        new AddLoginLogTask(context.get()).execute(log);
    }

    @Override
    public void onReceive(int requestCode, int resultCode, FirebaseUser user) {
        BaseResponse<FirebaseUser> response = new BaseResponse<>(user);
        switch (requestCode) {
            case Constant.FIREBASE_LOGIN_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    response.setStatus(resultCode);
                    addLoginLog(user);
                    update(response);
                } else {
                    response.setStatus(resultCode);
                    update(response);
                }
                break;
            case Constant.FIREBASE_REGISTER_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    response.setStatus(resultCode);
                    addLoginLog(user);
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

    private class AddLoginLogTask extends AsyncTask<LoginLog, Void, LoginLog> {
        private WeakReference<Context> context;

        private AddLoginLogTask(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected LoginLog doInBackground(LoginLog... loginLogs) {
            return LoginLogDatabase.getInstance(context.get()).addLoginLog(loginLogs[0]);
        }
    }
}
