package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core;

import android.arch.lifecycle.MutableLiveData;

public interface BaseViewModel<T> {
    MutableLiveData<T> getInstance();
    void update(T data);
}
