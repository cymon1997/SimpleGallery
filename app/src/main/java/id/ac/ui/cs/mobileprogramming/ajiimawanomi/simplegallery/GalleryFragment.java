package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.adapter.ImageAdapter;

public class GalleryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        GridView grid = root.findViewById(R.id.grid_view);
        String[] data = new String[10];
        for (int i = 0; i < data.length; i++) {
            data[i] = "https://via.placeholder.com/350x150";
        }
        grid.setAdapter(new ImageAdapter(data));
        return root;
    }
}
