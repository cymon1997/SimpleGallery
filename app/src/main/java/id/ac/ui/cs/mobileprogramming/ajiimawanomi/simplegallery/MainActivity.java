package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Router;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Util;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core.AuthViewModel;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.BaseResponse;

public class MainActivity extends AppCompatActivity {
    private final String CURRENT_STATE = "CURRENT_STATE";
    private int state;
    private GalleryFragment galleryFragment;
    private WifiFragment wifiFragment;
    private DrawerLayout drawer;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_drawer);
        setDrawer();

        manageFragment(savedInstanceState);

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);

        Observer<BaseResponse<FirebaseUser>> authObserver = new Observer<BaseResponse<FirebaseUser>>() {
            @Override
            public void onChanged(@Nullable BaseResponse<FirebaseUser> response) {
                redirectIfAuth(response);
            }
        };

        authViewModel.getInstance().observe(this, authObserver);

        checkData();
    }

    private void logout() {
        Router.clearData(this);
        Router.gotoLogin(this);
    }

    private void errorUnknown() {
        Toast.makeText(this, getResources().getString(R.string.error_unknown), Toast.LENGTH_SHORT).show();
    }

    private void checkData() {
        SharedPreferences preferences = getSharedPreferences(Constant.SHARED_PREFERENCES_USERDATA, MODE_PRIVATE);
        if (Util.isEmpty(preferences.getString(Constant.SHARED_PREFERENCES_USERDATA_UID, ""))) {
            redirectIfAuth(new BaseResponse<FirebaseUser>(Constant.API_SUCCESS, "", null));
        }
    }

    private void redirectIfAuth(BaseResponse<FirebaseUser> response) {
        switch (response.getStatus()) {
            case Constant.API_SUCCESS:
                Router.clearData(this);
                Router.gotoLogin(this);
                finish();
                break;
            case Constant.API_ERROR_UNKNOWN:
                errorUnknown();
                break;
        }
    }

    private void setDrawer() {
        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.drawer_navigation);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_wifi:
                        setFragment(wifiFragment);
                        state = 1;
                        break;
                    case R.id.drawer_logout:
                        logout();
                        break;
                }
                item.setChecked(true);
                drawer.closeDrawers();
                return false;
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_STATE, state);
    }

    private void manageFragment(Bundle savedInstanceState) {
        if (galleryFragment == null) {
            galleryFragment = new GalleryFragment();
        }
        if (wifiFragment == null) {
            wifiFragment = new WifiFragment();
        }
        if (savedInstanceState == null) {
            state = 0;
//            Toast.makeText(getApplicationContext(), "STATE : NULL", Toast.LENGTH_SHORT).show();
        } else {
            state = savedInstanceState.getInt(CURRENT_STATE, -1);
//            Toast.makeText(getApplicationContext(), "STATE : " + state, Toast.LENGTH_SHORT).show();
        }
        switch (state) {
            case 1:
                setFragment(wifiFragment);
                break;
            case 0:
            default:
                setFragment(galleryFragment);
                state = 0;
                break;
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
