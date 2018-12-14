package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core.SampleViewModel;

public class SampleActivity extends AppCompatActivity {
    private SampleViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(SampleViewModel.class);

        final Observer<String> sampleObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                String data = s;
            }
        };

        viewModel.getInstance().observe(this, sampleObserver);
    }
}
