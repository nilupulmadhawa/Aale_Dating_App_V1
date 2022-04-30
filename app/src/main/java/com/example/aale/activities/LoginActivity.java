package com.example.aale.activities;

import android.content.Intent;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.app.Activity;

import androidx.annotation.Nullable;

import com.example.aale.activities.AdminActivity;
import com.example.aale.activities.RegisterActivity;
import com.example.aale.databinding.ActivityLoginBinding;


public class LoginActivity extends Activity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText useremailEditText = binding.useremail;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final Button registerButton= binding.register;
        final ProgressBar loadingProgressBar = binding.loading;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("log","logged in");
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("log","go to register");
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }


}
