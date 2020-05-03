package com.petrique.openstack.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.petrique.openstack.DatabaseHelper;
import com.petrique.openstack.MainActivity;
import com.petrique.openstack.OverviewFragment.OverviewFragment;
import com.petrique.openstack.R;
import com.petrique.openstack.RegisterActivity;
import com.petrique.openstack.ui.login.LoginViewModel;
import com.petrique.openstack.ui.login.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    DatabaseHelper db;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        db = new DatabaseHelper(this);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final TextView registerTextView = findViewById(R.id.register);
        final Button loginButton = findViewById(R.id.login);
        final CheckBox remember = findViewById(R.id.remember);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if(checkbox.equals("true"))
        {
            Intent intent = new Intent (LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if(checkbox.equals("false")) {
            Toast.makeText(this, "Please Sign In", Toast.LENGTH_SHORT).show();
        }

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                    //Complete and destroy login activity once successful
                    finish();
                }
                setResult(Activity.RESULT_OK);

            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(), db);
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String user = usernameEditText.getText().toString().trim();
//                String pass = passwordEditText.getText().toString().trim();
//                Boolean res = db.checkUser(user, pass);
//                if(res == true)
//                {
//                    Toast.makeText(LoginActivity.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
//                }

                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(), db);
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(buttonView.isChecked())
                {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();

                }
                else if(!buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(MainIntent);

        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
