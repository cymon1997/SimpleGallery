package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.R;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.LoginLog;

public class LoginHistoryAdapter extends BaseAdapter {
    private List<LoginLog> data;

    public LoginHistoryAdapter(List<LoginLog> data) {
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
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_log_history_item, parent, false);
        ((TextView) layout.findViewById(R.id.log_timestamp)).setText(data.get(position).getTimestamp().toString());
        ((TextView) layout.findViewById(R.id.wifi_strength)).setText(data.get(position).getEmail());
        return layout;
    }
}
