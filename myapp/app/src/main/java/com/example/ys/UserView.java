package com.example.ys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserView extends AppCompatActivity {
Button btn_submit;
Button btn_new,logout;
TextView delacc;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.109/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    MyWebApi myWebApi = retrofit.create(MyWebApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        logout=findViewById(R.id.button4);
        btn_new = findViewById(R.id.btn_new);
        btn_submit = findViewById(R.id.btn_list);
        delacc=findViewById(R.id.deleteacc);
        delacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(UserView.this)

                        .setIcon(android.R.drawable.ic_dialog_alert)

                        .setTitle("Delete Account")

                        .setMessage("Are you sure")

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Call<String> call = myWebApi.deleteacc1(GlobalClass.username);
                                call.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });
                                Toast.makeText(getApplicationContext(),"Account Deleted",Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(UserView.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserView.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if((GlobalClass.role.equals("HOD") || GlobalClass.role.equals("Clerk")))
        {
            btn_new.setVisibility(View.GONE);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserView.this, ShowAppsActivity.class);
                startActivity(intent);

            }
        });
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserView.this, AddAplication.class);
                startActivity(intent);


            }
        });






    }
    @Override
    public void onBackPressed() {

        
    }
}