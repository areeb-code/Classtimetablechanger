package com.example.ys;

import android.app.Application;
import android.widget.DatePicker;

import java.io.Serializable;

public class Studentinfo implements Serializable {
    static int appNumber=00001;
public int appNo;
    public String user,fName , lName, RollNo , Program , Section , Email, Code1,Code2,cordCmnts,hodCmnts;
    public String status="Pending";
    //public int day;
    //public int month;
    //public int year ;
    //public DatePicker datePicker;

    public int getAppNumber() {
        return appNumber;
    }

    public Studentinfo(){ appNumber++;}

}