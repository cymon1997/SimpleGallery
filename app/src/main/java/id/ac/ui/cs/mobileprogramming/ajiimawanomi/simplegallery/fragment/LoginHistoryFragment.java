package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.R;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.adapter.LoginHistoryAdapter;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.adapter.WifiAdapter;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api.LoginLogDatabase;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core.LoginHistoryViewModel;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.BaseResponse;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.LoginLog;

public class LoginHistoryFragment extends Fragment {
    private LoginHistoryViewModel historyViewModel;
    private GridView grid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_wifi, container, false);
        grid = root.findViewById(R.id.grid_view);

        historyViewModel = ViewModelProviders.of(this).get(LoginHistoryViewModel.class);

        Observer<BaseResponse<List<LoginLog>>> historyObserver = new Observer<BaseResponse<List<LoginLog>>>() {
            @Override
            public void onChanged(@Nullable BaseResponse<List<LoginLog>> response) {
                setData(response);
            }
        };
        historyViewModel.getInstance().observe(this, historyObserver);
        historyViewModel.getAllLogs(getContext());

        return root;
    }

    private void errorUnknown() {
        Toast.makeText(getContext(), getResources().getString(R.string.error_unknown), Toast.LENGTH_SHORT).show();
    }

    private void setData(BaseResponse<List<LoginLog>> response) {
        switch (response.getStatus()) {
            case Constant.API_SUCCESS:
                grid.setAdapter(new LoginHistoryAdapter(response.getPayload()));
                break;
            case Constant.API_ERROR_UNKNOWN:
                errorUnknown();
                break;
        }
    }
}
