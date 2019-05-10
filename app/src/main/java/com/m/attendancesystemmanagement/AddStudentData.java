package com.m.attendancesystemmanagement;

import android.annotation.SuppressLint;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.m.attendancesystemmanagement.Model.Student;
import com.m.attendancesystemmanagement.Model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddStudentData extends AppCompatActivity {

    private RadioGroup radiogender;
    String MobilePattern = "[0-9]{10}";
    TextInputLayout id;
    TextInputLayout name;
    TextInputLayout email;
    TextInputLayout phone_number;
    public String gender_text=null;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("Add Student");

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        id=(TextInputLayout) findViewById(R.id.id);
        name=(TextInputLayout) findViewById(R.id.name);
        email=(TextInputLayout) findViewById(R.id.email);
        phone_number=(TextInputLayout)findViewById(R.id.phone_number);

        radiogender = (RadioGroup) findViewById(R.id.gender_group);
        //radiogender.clearCheck();

        radiogender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    gender_text= String.valueOf(rb.getText());
                }

            }
        });


    }

    public void onClear(View v) {
        /* Clears all selected radio buttons to default */
        radiogender.clearCheck();
    }
    private boolean validate_roll_number()
    {
        String phone_number_input=id.getEditText().getText().toString().trim();
        if(phone_number_input.isEmpty())
        {
            id.setError("Field can't be empty");
            return false;
        }
        else if(phone_number_input.length()!=7)
        {
            id.setError("Length should be 10");
            return false;
        }
        else
        {
            id.setError(null);
            return true;
        }
    }
    private boolean validate_email()
    {
        String email1=email.getEditText().getText().toString();
        if(email1.isEmpty())
        {
            email.setError("Field can't be empty");
            return false;
        }
        /*else if(!email1.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
        {
            email.setError("Invalid Email Address");
            return false;
        }*/
        else
        {
            email.setError(null);
            return true;
        }

    }

    private boolean validate_phone_number()
    {
        String phone_number_input=phone_number.getEditText().getText().toString().trim();
        if(phone_number_input.isEmpty())
        {
            phone_number.setError("Field can't be empty");
            return false;
        }
        else if(phone_number_input.length()!=10)
        {
            phone_number.setError("Length should be 10");
            return false;
        }
        else if(!phone_number_input.matches(MobilePattern))
        {
            phone_number.setError("Only be Numbers");
            return false;
        }
        else
        {
            phone_number.setError(null);
            return true;
        }
    }

    private boolean validate_username()
    {
        String username_input=name.getEditText().getText().toString().trim();
        if(username_input.length()==0)
        {
            name.setError("Field can't be empty");
            return false;
        }
        if(username_input.length()>15)
        {
            name.setError("Username too Long");
            return false;
        }
        else
        {
            name.setError(null);
            return true;
        }
    }

    public void AddData(View view)
    {
        if(!validate_roll_number() | !validate_phone_number() | !validate_username() | !validate_email() | gender_text==null)
        {
            Toast.makeText(getApplicationContext(),"Invalid Data",Toast.LENGTH_SHORT).show();
            return;
        }

        Database dbs=new Database(getBaseContext());
        boolean boole=dbs.check(id.getEditText().getText().toString().trim());
        if(boole==true)
        {

            //Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
            Student student=new Student(id.getEditText().getText().toString().trim(),name.getEditText().getText().toString().trim(),
                    email.getEditText().getText().toString().trim(),phone_number.getEditText().getText().toString().trim(),gender_text,"0");

            /*Log.d(String.valueOf(id),"id->"+student.getId());
            Log.d(String.valueOf(email),"email->"+student.getEmail());
            Log.d(String.valueOf(phone_number),"phone_number->"+student.getPhone_number());*/

            boolean change=dbs.addToStudentDetail(student);
            if(change==true)
            {
                Toast.makeText(getApplicationContext(), "Student Data Added", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Student Data Not Added", Toast.LENGTH_SHORT).show();
            }

        }
        else if(boole==false)
        {
            //Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            Toast.makeText(getApplicationContext(), "Student Data Not Added", Toast.LENGTH_SHORT).show();
            dlgAlert.setMessage("Roll Number Already Exists");
            dlgAlert.setTitle("Error Message...");
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

        }

    }
}
