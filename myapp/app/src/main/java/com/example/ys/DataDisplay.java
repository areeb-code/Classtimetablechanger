package com.example.ys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DataDisplay extends AppCompatActivity {



    public DataDisplay() {
    }
    MyHelper myHelper = new MyHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        final SQLiteDatabase writedatabase = myHelper.getWritableDatabase();
        Button Cancel ,btnApprove;
        final TextView firstname, lName, RollNo , Program , Section , Code1,Code2,appNo;
        final Studentinfo student = (Studentinfo) getIntent().getSerializableExtra("obj");
        firstname =findViewById(R.id.editTextTextPersonName);
        lName =findViewById(R.id.editTextTextPersonName2);
        RollNo =findViewById(R.id.editTextNumber);
        Program =findViewById(R.id.editTextTextPersonName4);
        Section =findViewById(R.id.editTextTextPersonName5);
        Code1 =findViewById(R.id.editTextTextPersonName7);
        Code2 =findViewById(R.id.editTextTextPersonName8);
        appNo = findViewById(R.id.textView4);
        Cancel = findViewById(R.id.btnCancl);
        firstname.setText(student.fName);
        lName.setText(student.lName);
        RollNo.setText(student.RollNo);
        Program.setText(student.Program);
        Section.setText(student.Section);
        Code1.setText(student.Code1);
        Code2.setText(student.Code2);
        btnApprove=findViewById(R.id.button3);
        if(GlobalClass.role.equals("Student"))
        {
            btnApprove.setText(student.status);
        }
        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GlobalClass.role.equals("Clerk"))
                {
                    if((student.status.equals("Approved By HOD") || student.status.equals("Approved By Clerk")))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Already Approved",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        student.status="Approved By Clerk";
                        myHelper.updateInApplications(student.status,writedatabase,student.RollNo);
                    }

                }
                if(GlobalClass.role.equals("HOD"))
                {
                    if(student.status.equals("Approved By HOD"))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Already Approved",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                        student.status="Approved By HOD";
                    myHelper.updateInApplications(student.status,writedatabase,student.RollNo);
                }
                if(GlobalClass.role.equals("Student"))
                {
                    if(student.status.equals("Approved By HOD"))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Already Approved",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if(student.status.equals("Approved By Clerk"))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Wait for Hod Confirmation",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if(student.status.equals("Uploaded"))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"Wait for Clerk Confirmation",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
                finish();
            }
        });
        appNo.setText("Application No #" + student.appNo);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}