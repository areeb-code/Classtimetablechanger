package com.example.ys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    MyHelper myHelper = new MyHelper(this);
EditText userId,pass;
TextView userd;
Button login,register;
TextView forgotpass;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.194/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    MyWebApi myWebApi = retrofit.create(MyWebApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        ////////DUMMY ACCOUNTS////////////////





        /////////////////////////////////////
        login=findViewById(R.id.btn_login);
        userId=findViewById(R.id.usernameId);
        pass=findViewById(R.id.passwrd);
        forgotpass=findViewById(R.id.frogotpass);
        register=findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(LoginActivity.this,ForgotPassActivity.class);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             boolean check=true;
                if (pass.getText().toString().length()==0) {
                    pass.setError("");
                    if (userId.getText().toString().length() == 0) {
                        userId.setError("");
                        check = false;
                    }
                }
                if(check)
                {
                    //if(checkLoginCredential2()){
                      //  Toast toast = Toast.makeText(getApplicationContext(), "Wait", Toast.LENGTH_SHORT);
                        //toast.show();
                    //}
                    Call<List<Post>> callx = myWebApi.loginuser(userId.getText().toString(),pass.getText().toString());
                    callx.enqueue(new Callback<List<Post>>() {
                        @Override
                        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                            List<Post> posts = response.body();
                            assert posts != null;
                            if(posts.size()>0) {
                                for (Post post : posts) {

                                    GlobalClass.username = post.getUser();
                                    GlobalClass.password = post.getPassword();
                                    GlobalClass.role = post.getRole();
                                    Intent intent = new Intent(LoginActivity.this, UserView.class);
                                    startActivity(intent);
                                    finish();
                                }



                            }
                            else
                            {
                                Toast toast = Toast.makeText(LoginActivity.this, "Error login/pass", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Post>> call, Throwable t) {

                        }
                    });




                }
            }
        });



    }
    public Boolean checkLoginCredential( )
    {

        SQLiteDatabase readable = myHelper.getReadableDatabase();
        Cursor cursor = readable.rawQuery("Select User , Password,Role from Users" , new String[]{});
        if(cursor!=null)
        {
            cursor.moveToFirst();

            do {
                if (userId.getText().toString().equals(cursor.getString(0))) {
                    if(pass.getText().toString().equals(cursor.getString(1)))
                    {
                        GlobalClass.username =cursor.getString(0);
                        GlobalClass.password =cursor.getString(1);
                        GlobalClass.role =cursor.getString(2);
                        return true;
                    }

                }
            }while (cursor.moveToNext());



        }
        else {
            return false;
        }

        return false;

    }
    public Boolean checkLoginCredential2( ) {


        Call<List<Post>> call = myWebApi.getPosts();
        userd = findViewById(R.id.textView);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    //userd.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {

                    if (post.getUser().equals(userId.getText().toString())) {

                        if (post.getPassword().equals(pass.getText().toString())) {

                            GlobalClass.username = post.getUser();
                            GlobalClass.password = post.getPassword();
                            GlobalClass.role = post.getRole();

                            Intent intent = new Intent(LoginActivity.this, UserView.class);
                            startActivity(intent);

                            finish();



                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

        return true;
    }
}