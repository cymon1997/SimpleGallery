package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.File;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.R;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.adapter.ImageAdapter;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core.ResponseReceiver;

public class GalleryFragment extends Fragment implements ResponseReceiver {
    private View root;
    private GridView grid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        grid = root.findViewById(R.id.grid_view);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Do nothing
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.PERMISSION_READ_EXTERNAL_STORAGE_REQUEST);
            }
        } else {
            // Do Action
            attempt();
        }
        return root;
    }

    private void attempt() {
        ContentResolver cr = getContext().getContentResolver();
        Uri uri = MediaStore.Files.getContentUri("external");
        String[] projection = null;
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cursor = cr.query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursor == null) {
            errorUnknown();
        } else if (cursor.getCount() == 0) {
            errorEmpty();
        } else {
            String[] data = new String[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
                File file = new File(path);
                data[i] = file.getAbsolutePath();
            }
            grid.setAdapter(new ImageAdapter(getActivity(), data));
        }
    }

    private void generateImages() {
        String[] data = new String[10];
        for (int i = 0; i < data.length; i++) {
            data[i] = "https://via.placeholder.com/1920x1280";
        }
        grid.setAdapter(new ImageAdapter(getActivity(), data));
    }

    private void errorEmpty() {
        root.findViewById(R.id.error_empty).setVisibility(View.VISIBLE);
    }

    private void errorUnknown() {
        Toast.makeText(getContext(), getResources().getString(R.string.error_unknown), Toast.LENGTH_SHORT).show();
    }

    private void errorPermission() {
        Toast.makeText(getContext(), getResources().getString(R.string.prompt_permission_denied), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(int requestCode, int resultCode, Object response) {
        switch (requestCode) {
            case Constant.PERMISSION_READ_EXTERNAL_STORAGE_REQUEST:
                if (resultCode == Constant.API_SUCCESS) {
                    attempt();
                } else {
                    errorPermission();
                }
                break;
        }
    }
}
