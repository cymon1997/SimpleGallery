package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.R;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Router;

public class ImageAdapter extends BaseAdapter {
    private Activity activity;
    private String[] data;

    public ImageAdapter(Activity activity, String[] data) {
        this.activity = activity;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image_item, parent, false);
        ImageView imageView = layout.findViewById(R.id.image_item);
//        loadFromSource(imageView, data[position]);
        loadFromPath(imageView, data[position]);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage(data[position]);
            }
        });
        return layout;
    }

    private void loadFromPath(ImageView target, String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        target.setImageBitmap(bitmap);
    }

    private void loadFromSource(ImageView target, String url) {
        Picasso.get().load(url).into(target);
    }

    private void openImage(String imgUrl) {
        Router.gotoDetailImage(activity, imgUrl);
    }
}
