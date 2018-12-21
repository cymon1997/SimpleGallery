package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.R;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Router;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Util;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.BaseResponse;

public class SafeModeViewModel extends ViewModel implements BaseViewModel<String> {
    private MutableLiveData<BaseResponse<String>> data;
    private WeakReference<Context> context;
    private WifiManager manager;

    @Override
    public MutableLiveData<BaseResponse<String>> getInstance() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        return data;
    }

    @Override
    public void update(BaseResponse<String> data) {
        getInstance().setValue(data);
    }

    private void activate() {
        Router.safeModeActivate(context.get());
    }

    private void deactivate() {
        Router.safeModeDeactivate(context.get());
    }

    private void kill(String ssid) {
        if (Util.isBlocked(ssid)) {
            manager.setWifiEnabled(false);
        }
    }

    private void decide(String ssid) {
        if (Router.safeModeStatus(context.get())) {
            deactivate();
        } else {
            activate();
            kill(ssid);
        }
    }

    public void listen(Context context) {
        this.context = new WeakReference<>(context);
        manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (!manager.isWifiEnabled()) {
            Toast.makeText(context, context.getResources().getString(R.string.wifi_enable), Toast.LENGTH_SHORT).show();
            manager.setWifiEnabled(true);
        }

        WifiInfo info = manager.getConnectionInfo();
        if (info.getSupplicantState() == SupplicantState.COMPLETED) {
            BaseResponse<String> response = new BaseResponse<>(info.getSSID());
            response.setStatus(Constant.API_SUCCESS);
            decide(info.getSSID());
            update(response);
        }
    }
}
