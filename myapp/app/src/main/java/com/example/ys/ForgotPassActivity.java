package com.example.ys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class ForgotPassActivity extends AppCompatActivity {
     EditText userid,pass;
     Button   changepass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        userid=findViewById(R.id.usernameId);
        pass=findViewById(R.id.passwrd);
        changepass=findViewById(R.id.btn_register);
        changepass.setOnClickListener(new View.OnClickListener() {
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
                if(check) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.43.194/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    MyWebApi myWebApi = retrofit.create(MyWebApi.class);
                    Call<PUT> callx = myWebApi.changepass(userid.getText().toString(), pass.getText().toString());
                    callx.enqueue(new Callback<PUT>() {
                        @Override
                        public void onResponse(Call<PUT> call, Response<PUT> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(ForgotPassActivity.this, "Secnnnnnces", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }

                        @Override
                        public void onFailure(Call<PUT> call, Throwable t) {
                            Toast.makeText(ForgotPassActivity.this, "Secces", Toast.LENGTH_SHORT).show();
                        }
                    });
   /*   Call<String> callx =myWebApi.changepassword(userid.getText().toString(),pass.getText().toString());
callx.enqueue(new Callback<String>() {
    @Override
    public void onResponse(Call<String> call, Response<String> response) {

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }
});
*/
                    finish();
                }
            }
        });

    }
}