package com.petrique.openstack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.petrique.openstack.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText confirmPasswordEditText = findViewById(R.id.confirmPassword);
        final Button registerButton = findViewById(R.id.registerButton);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading2);
        final TextView backToLoginTextView = findViewById(R.id.backToLogin);

        db = new DatabaseHelper(this);

        backToLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameEditText.getText().toString().trim();
                String pass = passwordEditText.getText().toString().trim();
                String confirm_pass = confirmPasswordEditText.getText().toString().trim();

                if(pass.equals(confirm_pass)) {
                    long val = db.addUser(user, pass);
                    if (val > 0) {
                        Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(moveToLogin);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Error adding user in db!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
