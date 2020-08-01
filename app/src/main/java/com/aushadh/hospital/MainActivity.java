package com.aushadh.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.aushadh.hospital.App.SessionManager;

/*
* TODO :: This is Splash screen first screen to be attempt
* */

public class MainActivity extends AppCompatActivity {

SessionManager sessionManager  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);
        if (sessionManager.isLoggedIn()){
            Intent Home  = new Intent(getApplicationContext() , Dash.class);
            startActivity(Home);
            finish();
        }else{
            Intent Login  = new Intent(getApplicationContext() , Login.class);
            startActivity(Login);
           finish();
        }

    }
}
