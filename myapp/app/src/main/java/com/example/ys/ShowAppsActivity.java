package com.example.ys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

public class ShowAppsActivity extends AppCompatActivity {
    MyHelper myHelper = new MyHelper(this);
    Button back ;
    RecyclerView recyclerView;
    ArrayList<Studentinfo> studentinfos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back= (Button) findViewById(R.id.btn_new);


        recyclerView=findViewById(R.id.recyclerView);
        getdata();
        MyAdapter myAdapter = new MyAdapter(this,studentinfos);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));;
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
    }

    public void getdata()
    {
        SQLiteDatabase readdatabase = myHelper.getReadableDatabase();
        Cursor cursor=null;
        if(GlobalClass.role.equals("Clerk"))
        {
            cursor = readdatabase.rawQuery("SELECT fName,lName, RollNumber, Program, Section, Code1, Code2,cordinateCmnt,hodCmnt,Status FROM Applications", new String[]{});
        }

        if(GlobalClass.role.equals("Student"))
        {
            cursor = readdatabase.rawQuery("SELECT fName,lName, RollNumber, Program, Section, Code1, Code2,cordinateCmnt,hodCmnt,Status FROM Applications WHERE User = ?" , new String[]{GlobalClass.username});

        }
        if(GlobalClass.role.equals("HOD"))
        {
            String s="Approved By Clerk";
            cursor = readdatabase.rawQuery("SELECT fName,lName, RollNumber, Program, Section, Code1, Code2,cordinateCmnt,hodCmnt,Status FROM Applications WHERE Status = ?" , new String[]{s});

        }
        if(cursor==null) {
            return;
        }
        else {

            cursor.moveToFirst();
            if(!isEmpty("Applications") && cursor.getCount()>0) {
                do {

                        Studentinfo student = new Studentinfo();
                        student.fName = cursor.getString(0);
                        student.lName = cursor.getString(1);
                        student.RollNo = cursor.getString(2);
                        student.Program = cursor.getString(3);
                        student.Section = cursor.getString(4);
                        student.Code1 = cursor.getString(5);
                        student.Code2 = cursor.getString(6);
                        student.status = cursor.getString(9);
                        studentinfos.add(student);

                } while (cursor.moveToNext());
            }
            else return;

        }
    }
    public boolean isEmpty(String TableName){

        SQLiteDatabase database = myHelper.getReadableDatabase();
        long NoOfRows = DatabaseUtils.queryNumEntries(database,TableName);

        if (NoOfRows == 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentinfos= new ArrayList<Studentinfo>();
        getdata();
        MyAdapter myAdapter = new MyAdapter(this,studentinfos);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));;
    }
}