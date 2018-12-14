package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SampleViewModel extends ViewModel implements BaseViewModel<String> {
    private MutableLiveData<String> sampleData;

    public MutableLiveData<String> getInstance() {
        if (sampleData == null) {
            sampleData = new MutableLiveData<>();
        }
        return sampleData;
    }

    public void update(String data) {
        getInstance().setValue(data);
    }
}
