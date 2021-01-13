package com.example.ys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PUT;

public class AddAplication extends AppCompatActivity {
    String[] program = { "BS-CS", "BS-IT","BS-SE" };
    String[] section = { "A", "B","C" };
    String[] coursecode = { "CS-462", "CS-402","CS-407","CS-322" };
    MyHelper myHelper = new MyHelper(this);

       ArrayAdapter programad,sectionad,code1ad,code2ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        ////////////////////////////////////
        Button Submit , Cancel ;
        final EditText firstname, lName, RollNo ;
        final Spinner Program , Section , Code1,Code2;
        firstname =findViewById(R.id.editTextTextPersonName);
        lName =findViewById(R.id.editTextTextPersonName2);
        RollNo =findViewById(R.id.editTextNumber);
        Program =findViewById(R.id.editTextTextPersonName4);
        Section =findViewById(R.id.editTextTextPersonName5);
        Code1 =findViewById(R.id.editTextTextPersonName7);
        Code2 =findViewById(R.id.editTextTextPersonName8);
        Submit = findViewById(R.id.btnsubmt);
        Cancel = findViewById(R.id.btnCancl);
        Bundle bundle = getIntent().getExtras();
        setmyadapter(programad,program,Program);
        setmyadapter(sectionad,section,Section);
        setmyadapter(code1ad,coursecode,Code1);
        setmyadapter(code2ad,coursecode,Code2);
        ////////////////////////////////////
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Studentinfo student = new Studentinfo();
                Boolean check = true;
                if(firstname.getText().toString().length()==0)
                {
                    firstname.setError("This field is required");
                    check=false;
                }
                else
                {
                    student.fName=firstname.getText().toString();

                }
                if(lName.getText().toString().length()==0)
                {
                    lName.setError("This field is required");
                    check=false;
                }
                else
                {
                    student.lName=lName.getText().toString();

                }
                student.appNo=student.getAppNumber();

                if(RollNo.getText().toString().length()==0)
                {
                    RollNo.setError("This field is required");
                    check=false;
                }
                else
                {
                    student.RollNo=RollNo.getText().toString();

                }
                    student.Section=Section.getSelectedItem().toString();
                    student.Program=Program.getSelectedItem().toString();
                    student.Code1=Code1.getSelectedItem().toString();
                    student.Code2=Code2.getSelectedItem().toString();
                    student.user=GlobalClass.username;
                    student.status="Uploaded";
                if(check)
                {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.43.194/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    MyWebApi myWebApi = retrofit.create(MyWebApi.class);
                    Call<PUT> callx = myWebApi.addapplication(student.user,student.fName,student.lName,student.RollNo,student.Program,student.Section,student.Code1,student.Code2,"","",student.status);
                    callx.enqueue(new Callback<PUT>() {
                        @Override
                        public void onResponse(Call<PUT> call, Response<PUT> response) {
                            student.status="Pending";
                        }

                        @Override
                        public void onFailure(Call<PUT> call, Throwable t) {

                        }
                    });
                    myHelper.insertInApps(student.user,student.fName,student.lName,student.RollNo,student.Program,student.Section,student.Code1,student.Code2,student.status,sqLiteDatabase);

                    finish();
                }
                //



            }
        });
        //
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setmyadapter(ArrayAdapter a,String[] s,Spinner sp)
    {
        a = new ArrayAdapter(this, android.R.layout.simple_spinner_item, s);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(a);
    }
}