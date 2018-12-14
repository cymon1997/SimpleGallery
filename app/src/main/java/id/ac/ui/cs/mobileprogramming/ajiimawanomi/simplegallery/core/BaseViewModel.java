package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core;

import android.arch.lifecycle.MutableLiveData;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.BaseResponse;

public interface BaseViewModel<T> {
    MutableLiveData<BaseResponse<T>> getInstance();
    void update(BaseResponse<T> data);
}
