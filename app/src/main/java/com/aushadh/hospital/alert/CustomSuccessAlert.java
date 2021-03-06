package com.aushadh.hospital.Alert;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.aushadh.hospital.Dash;
import com.aushadh.hospital.R;

public class CustomSuccessAlert {
    Context context ;
    AlertDialog alertDialog;
    Activity activity  ;


    public CustomSuccessAlert(Context context , Activity activity) {
        this.context = context;
        this.activity = activity;
    }
    public  void showAlert(String msg){

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.success_alert , null);
        TextView text  = view.findViewById(R.id.text);
        // imageView.setAnimation(animation);
        Button ok = view.findViewById(R.id.ok) ;

        text.setText(msg);
        alert.setView(view);
        alert.setCancelable(false) ;
//        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
        alertDialog  = alert.create() ;
        alertDialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog.isShowing()){
                    dismiss();
                    Intent intent = new Intent(context , Dash.class);
                    context.startActivity(intent);
                    activity.finish();
                }
            }
        });
    }
    public  void dismiss(){
        alertDialog.dismiss();
    }
}
