package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.BaseResponse;

public interface ResponseReceiver<T> {
    void onReceive(int requestCode, int resultCode, T response);
}
