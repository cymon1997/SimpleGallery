package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Util;

public class DetailActivity extends AppCompatActivity {
    public static final String INTENT_IMAGE_URL = "IMAGE_URL";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        setActionBar();
        setImage();
    }

    private void setImage() {
        Intent intent = getIntent();
        String imgUrl = intent.getStringExtra(INTENT_IMAGE_URL);
        if (Util.isEmpty(imgUrl)) {
            imgUrl = "https://via.placeholder.com/640x320";
        }
        ImageView imageView = findViewById(R.id.image_item);
        Picasso.get().load(imgUrl).into(imageView);
    }

    private void setActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
