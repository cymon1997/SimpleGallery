package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.api.FirebaseAPI;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Constant;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Router;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.common.Util;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.core.AuthViewModel;
import id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.data.BaseResponse;

public class LoginActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);

        Observer<BaseResponse<FirebaseUser>> authObserver = new Observer<BaseResponse<FirebaseUser>>() {
            @Override
            public void onChanged(@Nullable BaseResponse<FirebaseUser> response) {
                redirectIfAuth(response);
            }
        };

        authViewModel.getInstance().observe(this, authObserver);

        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt();
            }
        });
        findViewById(R.id.btn_help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegister();
            }
        });
        ((TextView) findViewById(R.id.field_email)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                findViewById(R.id.prompt_error).setVisibility(View.INVISIBLE);
            }
        });
        ((TextView) findViewById(R.id.field_password)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                findViewById(R.id.prompt_error).setVisibility(View.INVISIBLE);
            }
        });
    }

    private void login(String email, String password) {
        authViewModel.login(this, email, password);
    }

    private void attempt() {
        String email = ((EditText) findViewById(R.id.field_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.field_password)).getText().toString();
        if (Util.isValidEmail(email) && !Util.isEmpty(password)) {
            login(email, password);
        } else {
            errorFieldEmpty();
        }
    }

    private void errorFieldEmpty() {
        findViewById(R.id.prompt_error).setVisibility(View.VISIBLE);
    }

    private void errorNotFound() {
        Toast.makeText(this, getResources().getString(R.string.error_not_registered), Toast.LENGTH_SHORT).show();
    }

    private void gotoRegister() {
        Router.gotoRegister(this);
    }

    private void redirectIfAuth(BaseResponse<FirebaseUser> response) {
        switch (response.getStatus()) {
            case Constant.API_SUCCESS:
                Router.saveData(this, response.getPayload().getUid(), response.getPayload().getEmail());
                Router.gotoMain(this);
                finish();
                break;
            case Constant.API_ERROR_NOT_FOUND:
                errorNotFound();
                break;

        }
    }
}
