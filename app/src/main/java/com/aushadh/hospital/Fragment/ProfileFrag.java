package com.aushadh.hospital.Fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aushadh.hospital.App.SessionManager;
import com.aushadh.hospital.CreateAlarm;
import com.aushadh.hospital.MainActivity;
import com.aushadh.hospital.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends Fragment {
SessionManager sessionManager ;
View view ;
TextView userMobile ;
private Button createRemainder;

    //SessionManager sessionManager = new SessionManager()
    public ProfileFrag() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_profile, container, false);
        userMobile= view.findViewById(R.id.mobile);
        sessionManager = new SessionManager( Objects.requireNonNull( getContext() ) );
        userMobile.setText("+91-"+sessionManager.getLoggedInMobile()   );
        createRemainder = view.findViewById(R.id.create) ;
        createRemainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c     = new Intent(getContext() , CreateAlarm.class);
                c.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(c);
            }
        });
        Button logout = view.findViewById( R.id.logout );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.setLogin(false);
                sessionManager.setLoginMobile(null);
                Intent home = new Intent(getContext() , MainActivity.class);
                home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
                startActivity(home);
                Objects.requireNonNull( getActivity() ).finish();
            }
        });
        return view ;
    }

}
