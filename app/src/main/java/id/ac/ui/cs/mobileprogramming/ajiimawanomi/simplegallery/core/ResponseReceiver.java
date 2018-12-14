package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core;

public interface ResponseReceiver<T> {
    void onReceive(int requestCode, int resultCode, T response);
}
