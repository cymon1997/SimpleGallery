package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Util;

public class GalleryProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(Constant.PACKAGE_NAME, Constant.PROVIDER_TABLE_GALLERY, 1);
        uriMatcher.addURI(Constant.PACKAGE_NAME, String.format("%s/#", Constant.PROVIDER_TABLE_GALLERY), 2);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder, @Nullable CancellationSignal cancellationSignal) {
        switch (uriMatcher.match(uri)) {
            case Constant.PROVIDER_TABLE_ALL:
                if (Util.isEmpty(sortOrder)) {
                    sortOrder = "_ID ASC";
                }
                break;
            case Constant.PROVIDER_TABLE_SINGLE:
                selection += "_ID = " + uri.getLastPathSegment();
                break;
        }
        return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
