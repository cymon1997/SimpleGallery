package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.R;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Router;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Util;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core.SafeModeViewModel;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.BaseResponse;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.ui.SimpleSurfaceView;

public class SafeModeFragment extends Fragment {
    private SafeModeViewModel safeViewModel;
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_safe_mode, container, false);

        safeViewModel = ViewModelProviders.of(this).get(SafeModeViewModel.class);

        Observer<BaseResponse<String>> safeObserver = new Observer<BaseResponse<String>>() {
            @Override
            public void onChanged(@Nullable BaseResponse<String> stringBaseResponse) {
                decide(stringBaseResponse);
            }
        };

        safeViewModel.getInstance().observe(this, safeObserver);

        ((LinearLayout) root.findViewById(R.id.surface_container)).addView(new SimpleSurfaceView(getContext()));
        root.findViewById(R.id.btn_toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        setState();

        return root;
    }

    private void promptBlock() {
        Toast.makeText(getContext(), getResources().getString(R.string.prompt_blocked_ssid), Toast.LENGTH_SHORT).show();
    }

    private void decide(BaseResponse<String> response) {
        boolean isActive = Router.safeModeStatus(getContext());
        String ssid = response.getPayload();
        if (response.getStatus() == Constant.API_SUCCESS) {
            if (Util.isBlocked(ssid) && isActive) {
                promptBlock();
            }
        }
//        Toast.makeText(getContext(), "SSID [" + ssid + "]", Toast.LENGTH_SHORT).show();
    }

    private void setState() {
        boolean isActive = Router.safeModeStatus(getContext());
        if (isActive) {
            root.findViewById(R.id.safe_logo).setAlpha(1);
            ((Button) root.findViewById(R.id.btn_toggle)).setText(getResources().getString(R.string.safe_deactivate));
        } else {
            root.findViewById(R.id.safe_logo).setAlpha(0.1f);
            ((Button) root.findViewById(R.id.btn_toggle)).setText(getResources().getString(R.string.safe_activate));
        }
    }

    private void toggle() {
        if (Router.safeModeStatus(getContext())) {
            Toast.makeText(getContext(), getResources().getString(R.string.prompt_safe_deactivate), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.prompt_safe_activate), Toast.LENGTH_SHORT).show();
        }
        safeViewModel.listen(getContext());
        setState();
    }
}
