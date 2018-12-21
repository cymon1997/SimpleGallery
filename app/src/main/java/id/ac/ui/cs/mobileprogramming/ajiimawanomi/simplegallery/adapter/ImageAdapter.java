package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.R;

public class ImageAdapter extends BaseAdapter {
    private String[] data;

    public ImageAdapter(String[] data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_item, parent, false);
        ImageView imageView = layout.findViewById(R.id.image_item);
        Picasso.get().load(data[position]).into(imageView);
        return layout;
    }
}
