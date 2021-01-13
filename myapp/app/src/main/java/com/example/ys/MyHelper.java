package com.example.ys;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context)
    {
        super(context , "DrTimetable" , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Applications(_id INTEGER PRIMARY KEY AUTOINCREMENT,User TEXT , FName TEXT, LName TEXT , RollNumber TEXT, Program TEXT, Section TEXT , Code1 TEXT , Code2 TEXT , cordinateCmnt TEXT , hodCmnt TEXT , Status TEXT)";
        String users = "CREATE TABLE Users(_id INTEGER PRIMARY KEY AUTOINCREMENT , User TEXT , Password TEXT, Role TEXT)";
       db.execSQL(sql);
        db.execSQL(users);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertInApps(String user,String fname , String lname , String rollnumber ,String program,String section ,String code1   ,String code2 ,String status, SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("User" , user);
        contentValues.put("fName" , fname);
        contentValues.put("lName" , lname);
        contentValues.put("RollNumber", rollnumber);
        contentValues.put("Program", program);
        contentValues.put("Section", section);
        contentValues.put("Code1", code1);
        contentValues.put("Code2", code2);
        contentValues.put("Status",status);
        database.insert("Applications" , null , contentValues);
    }
    public void insertInUsers(String user ,String password , String role, SQLiteDatabase sqLiteDatabase)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("User" , user);
        contentValues.put("Role", role);
        contentValues.put("Password", password);
        sqLiteDatabase.insert("Users" , null , contentValues);
    }
   public void updateInApplications(String status,SQLiteDatabase sqLiteDatabase,String postion)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Status",status);
        sqLiteDatabase.update("Applications",contentValues,"RollNumber=?",new String[]{postion});
    }
   /* public void delete(SQLiteDatabase sqLiteDatabase , String position) {
        sqLiteDatabase.delete("AppList", "RollNumber=?", new String[]{position});
    }*/

}
