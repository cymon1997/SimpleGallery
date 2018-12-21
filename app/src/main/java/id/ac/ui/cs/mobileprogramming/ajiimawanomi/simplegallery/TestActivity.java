package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
                if (success) {
                    scanSuccess(wifiManager.getScanResults());
                } else {
                    scanFailure();
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        getApplicationContext().registerReceiver(receiver, filter);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scanFailure() {

    }
}
