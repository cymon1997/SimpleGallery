package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.R;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.adapter.WifiAdapter;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core.ResponseReceiver;

public class WifiFragment extends Fragment implements ResponseReceiver {
    private GridView grid;
    private WifiManager wifiManager;
    private BroadcastReceiver receiver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        grid = root.findViewById(R.id.grid_view);

        wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, true);
                if (success) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_WIFI_STATE)) {
                            // Do nothing
                        } else {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.PERMISSION_READ_EXTERNAL_STORAGE_REQUEST);
                        }
                    } else {
                        // Do Action
                        scanSuccess(wifiManager.getScanResults());
                    }

                } else {
                    scanFailure();
                }
            }
        };

        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.wifi_enable), Toast.LENGTH_SHORT).show();
            wifiManager.setWifiEnabled(true);
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        getContext().getApplicationContext().registerReceiver(receiver, filter);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CHANGE_WIFI_STATE)) {
                // Do nothing
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.PERMISSION_READ_EXTERNAL_STORAGE_REQUEST);
            }
        } else {
            // Do Action
            attempt();
        }


        grid.setAdapter(new WifiAdapter(new ArrayList<ScanResult>()));
        return root;
    }

    private void attempt() {
        boolean success = wifiManager.startScan();
        if (!success) {
            scanFailure();
        }
    }

    private void scanSuccess(List<ScanResult> results) {
        List<ScanResult> dataset = new ArrayList<>();
        int size = results.size();
        HashMap<String, Integer> signalStrength = new HashMap<>();
        try {
            for (int i = 0; i < size; i++) {
                ScanResult result = results.get(i);
                if (!result.SSID.isEmpty()) {
                    String key = result.SSID + " " + result.capabilities;
                    if (!signalStrength.containsKey(key)) {
                        signalStrength.put(key, i);
                        dataset.add(result);
                    } else {
                        int position = signalStrength.get(key);
                        ScanResult updateItem = dataset.get(position);
                        int level = 5;
                        if (WifiManager.calculateSignalLevel(updateItem.level, level) > WifiManager.calculateSignalLevel(result.level, level)) {
                            dataset.set(position, updateItem);
                        }
                    }
                }
            }
            grid.setAdapter(new WifiAdapter(dataset));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(getContext(), "WIFI SCAN SUCCESS!", Toast.LENGTH_SHORT).show();
    }

    private void scanFailure() {
        Toast.makeText(getContext(), "WIFI SCAN FAILED!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        getContext().getApplicationContext().unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void errorPermission() {
        Toast.makeText(getContext(), getResources().getString(R.string.prompt_permission_denied), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(int requestCode, int resultCode, Object response) {
        switch (requestCode) {
            case Constant.PERMISSION_ACCESS_WIFI_STATE_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    scanSuccess(wifiManager.getScanResults());
                } else {
                    errorPermission();
                }
                break;
            case Constant.PERMISSION_CHANGE_WIFI_STATE_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    attempt();
                } else {
                    errorPermission();
                }
                break;
        }
    }
}
