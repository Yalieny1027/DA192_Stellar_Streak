package com.aushadh.hospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aushadh.hospital.Alert.CustomAlertDanger;
import com.aushadh.hospital.App.Constant;
import com.aushadh.hospital.App.CustomLoader;
import com.aushadh.hospital.App.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Register extends AppCompatActivity {
    Toolbar toolbar ;
    Button loginBtn  , registerBtn  ;
    EditText name , email , mobile , password ;
    String nameStr , emailStr , mobileStr , passwordStr ;
    CustomAlertDanger customAlertDanger  ;
    CustomLoader customLoader ;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Poppins-Regular.ttf")
                .setFontAttrId(R.attr.fontPath).build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = findViewById(R.id.toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.left_arrow);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        Objects.requireNonNull( getSupportActionBar() ).setHomeAsUpIndicator(upArrow);
        if (getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // toolbar.setTitle("Manage Booking");
        }
        setTitle(getString(R.string.registerAccount));
        customAlertDanger = new CustomAlertDanger(this);
        customLoader = new CustomLoader(this);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr = name.getText().toString() ;
                emailStr = email.getText().toString();
                mobileStr = mobile.getText().toString();
                passwordStr = password.getText().toString() ;
                if (TextUtils.isEmpty(nameStr)){
                    customAlertDanger.showAlertDanger("Name is required");
                }else if(TextUtils.isEmpty(emailStr)){
                    customAlertDanger.showAlertDanger("Email is required");
                }else if(TextUtils.isEmpty(mobileStr)){
                    customAlertDanger.showAlertDanger("Mobile is required");
                }else if(TextUtils.isEmpty(passwordStr)){
                    customAlertDanger.showAlertDanger("Password is required");
                }else{
                    startRegistration (nameStr , emailStr , mobileStr , passwordStr);
                }

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(getApplicationContext() , Login.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
                startActivity(loginIntent);
                finish();
            }
        });
    }

    private void startRegistration(final String nameStr, final String emailStr, final String mobileStr, final String passwordStr) {
        customLoader.show();
        Constant.USER_MOBILE = mobileStr ;
        StringRequest register = new StringRequest(Request.Method.POST, Server.REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                customLoader.dismiss();
                Log.d("response" , response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean error = jsonObject.getBoolean("error");
                    if (!error){
                        Intent otp = new Intent(getApplicationContext() , OTPHandler.class);
                        otp .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(otp);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                customLoader.dismiss();
                Log.d("responseError" , error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
            //      return super.getParams();
                HashMap<String , String> map = new HashMap<>();

                map.put("name" , nameStr) ;
                map.put("email" , emailStr);
                map.put("mobile" , mobileStr);
                map.put("password" , passwordStr);
                return  map  ;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(register);
    }

}
