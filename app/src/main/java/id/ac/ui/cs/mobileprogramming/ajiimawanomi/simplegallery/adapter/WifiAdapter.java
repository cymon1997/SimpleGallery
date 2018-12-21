package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.adapter;

import android.graphics.drawable.Drawable;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.R;

public class WifiAdapter extends BaseAdapter {
    private List<ScanResult> data;

    public WifiAdapter(List<ScanResult> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wifi_item, parent, false);
        String signal;
        Drawable logo;
        switch (WifiManager.calculateSignalLevel(data.get(position).level, 5)) {
            case 0:
            case 1:
            case 2:
                signal = "Weak";
                logo = parent.getContext().getResources().getDrawable(R.drawable.ic_action_wifi_off);
                break;
            case 3:
            case 4:
            default:
                signal = "Fair";
                logo = parent.getContext().getResources().getDrawable(R.drawable.ic_action_wifi);
                break;
        }
        ((ImageView) layout.findViewById(R.id.wifi_logo)).setImageDrawable(logo);
        ((TextView) layout.findViewById(R.id.wifi_ssid)).setText(data.get(position).SSID);
        ((TextView) layout.findViewById(R.id.wifi_strength)).setText(signal);
        return layout;
    }
}
