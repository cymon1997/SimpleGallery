package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api.LoginLogDatabase;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.BaseResponse;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.LoginLog;

public class LoginHistoryViewModel extends ViewModel implements BaseViewModel<List<LoginLog>> {
    private MutableLiveData<BaseResponse<List<LoginLog>>> logs;

    @Override
    public MutableLiveData<BaseResponse<List<LoginLog>>> getInstance() {
        if (logs == null) {
            logs = new MutableLiveData<>();
        }
        return logs;
    }

    public void getAllLogs(Context context) {
        new AllLogsTask(context).execute();
    }

    public void getLogsByEmail(Context context, String email) {
        new LogsByEmailTask(context).execute(email);
    }

    @Override
    public void update(BaseResponse<List<LoginLog>> data) {
        getInstance().setValue(data);
    }

    private class AllLogsTask extends AsyncTask<String, Void, List<LoginLog>> {
        private WeakReference<Context> context;

        private AllLogsTask(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected List<LoginLog> doInBackground(String... strings) {
            return LoginLogDatabase.getInstance(context.get()).getAllLogs();
        }

        @Override
        protected void onPostExecute(List<LoginLog> loginLogs) {
            BaseResponse<List<LoginLog>> response = new BaseResponse<>(loginLogs);
            response.setStatus(Constant.API_SUCCESS);
            update(response);
        }
    }

    private class LogsByEmailTask extends AsyncTask<String, Void, List<LoginLog>> {
        private WeakReference<Context> context;

        private LogsByEmailTask(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected List<LoginLog> doInBackground(String... strings) {
            return LoginLogDatabase.getInstance(context.get()).getLogsByEmail(strings[0]);
        }

        @Override
        protected void onPostExecute(List<LoginLog> loginLogs) {
            BaseResponse<List<LoginLog>> response = new BaseResponse<>(loginLogs);
            response.setStatus(Constant.API_SUCCESS);
            update(response);
        }
    }
}
