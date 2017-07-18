package com.example.user.signup;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
        private EditText  etPassword, etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword = (EditText) findViewById(R.id.etPassword);
        etPhone = (EditText) findViewById(R.id.etPhone);

    }

    public void login(View v) {

        String passWord = etPassword.getText().toString();
        String phoneNumber = etPhone.getText().toString();

        Toast.makeText(this, "會員登入中...", Toast.LENGTH_SHORT).show();
        new CheckLoginActivity(this).execute(phoneNumber,passWord);


        }

    }



