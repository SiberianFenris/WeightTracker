package com.example.weighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class LoginPage extends AppCompatActivity {
    private EditText user;
    private EditText password;
    private Button signInButton;
    private WeightDB database;
    public boolean validUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        user = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        signInButton = findViewById(R.id.loginButton);
        WeightDB.initialize(getApplicationContext());
        database = WeightDB.getInstance();
        validUser = false;

        user.setOnKeyListener((view, keyCode, keyEvent) -> {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                password.requestFocus();
                return true;
            }
            return false;
        });

        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                enableSignInButtonIfReady();
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                enableSignInButtonIfReady();
            }
        });

        signInButton.setOnClickListener(view -> signIn(view));
        signInButton.setEnabled(false);


    }
    private void enableSignInButtonIfReady() {
        boolean isUsernameNonEmpty = !TextUtils.isEmpty(user.getText());
        boolean isPasswordNonEmpty = !TextUtils.isEmpty(password.getText());
        signInButton.setEnabled(isUsernameNonEmpty && isPasswordNonEmpty);
    }

    public boolean validateUser(String username, String password) {
        if (database.validateUser(username, password)) {
            Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
            return true;
        } else if (database.checkUserExists(username)) {
            Toast.makeText(getApplicationContext(), "Incorrect password!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            User newUser = new User(username, password);
            boolean userAdd = database.addUser(newUser);
            if (userAdd) {
                Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Unable to create user!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    public void signIn(android.view.View view) {
        String username = user.getText().toString();
        String password = this.password.getText().toString();
        if(validateUser(username, password)) {
            Intent intent = new Intent(this, com.example.weighttracker.MainActivity.class);
            intent.putExtra("username", username); // add the username as an extra
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean getValidUser() {

        return validUser;
    }
}