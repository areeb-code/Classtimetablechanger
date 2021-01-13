package com.example.ys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PUT;

public class RegisterActivity extends AppCompatActivity {
    String[] role = { "Student", "Clerk","HOD" };
    ArrayAdapter myrole;
    EditText userid,pass;
    Button register;
    Spinner rolespin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userid=findViewById(R.id.usernameId);
        pass=findViewById(R.id.passwrd);
        register=findViewById(R.id.btn_register);
        rolespin=findViewById(R.id.editTextTextPersonName5);
        setmyadapter(myrole,role,rolespin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check=true;
                if (pass.getText().toString().length()==0) {
                    pass.setError("");
                    if (userid.getText().toString().length() == 0) {
                        userid.setError("");
                        check = false;
                    }
                }
                if(check)
                {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.43.194/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    MyWebApi myWebApi = retrofit.create(MyWebApi.class);
                    Call<PUT> callx = myWebApi.adduser(userid.getText().toString(), pass.getText().toString(),rolespin.getSelectedItem().toString());
                    callx.enqueue(new Callback<PUT>() {
                        @Override
                        public void onResponse(Call<PUT> call, Response<PUT> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Secnnnnnces", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        @Override
                        public void onFailure(Call<PUT> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Seces", Toast.LENGTH_SHORT).show();
                        }
                    });
                    finish();
                }
            }
        });
    }




    public void setmyadapter(ArrayAdapter a, String[] s, Spinner sp)
    {
        a = new ArrayAdapter(this, android.R.layout.simple_spinner_item, s);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(a);
    }
}