package com.m.attendancesystemmanagement;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.m.attendancesystemmanagement.Model.Student;
import com.m.attendancesystemmanagement.Model.Subject;

public class AddSubject extends AppCompatActivity {


    TextInputLayout subject_id;
    TextInputLayout subject_name;
    String elective_bool_text=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("Add Subject");

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
        subject_id=(TextInputLayout) findViewById(R.id.subject_id);
        subject_name=(TextInputLayout) findViewById(R.id.subject_name);

    }


    private boolean validate_subject_id()
    {
        String subject_id_input=subject_id.getEditText().getText().toString().trim();
        if(subject_id_input.isEmpty())
        {
            subject_id.setError("Field can't be empty");
            return false;
        }
        else
        {
            subject_id.setError(null);
            return true;
        }
    }

    private boolean validate_subject_name()
    {
        String subject_name_input=subject_name.getEditText().getText().toString().trim();
        if(subject_name_input.length()==0)
        {
            subject_name.setError("Field can't be empty");
            return false;
        }
        else
        {
            subject_name.setError(null);
            return true;
        }
    }
    public void AddSubject(View view)
    {
        if(!validate_subject_id() | !validate_subject_name())
        {
            //Toast.makeText(getApplicationContext(),"Invalid Data",Toast.LENGTH_SHORT).show();
            return;
        }

        Database dbs=new Database(getBaseContext());
        boolean boole=dbs.check_subject(subject_id.getEditText().getText().toString().trim());
        if(boole==true)
        {

            //Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
            Subject subject=new Subject(subject_id.getEditText().getText().toString().trim(),subject_name.getEditText().getText().toString().trim(),
                   "0");

            /*Log.d(String.valueOf(id),"id->"+student.getId());
            Log.d(String.valueOf(email),"email->"+student.getEmail());
            Log.d(String.valueOf(phone_number),"phone_number->"+student.getPhone_number());*/

            boolean change=dbs.addToSubject(subject);
            if(change==true)
            {
                boolean subject_adder=dbs.make_subject_table(subject_id.getEditText().getText().toString().trim());
                if(subject_adder)
                {
                    boolean result=dbs.add_to_subject_attendance_count_list(subject_id.getEditText().getText().toString().trim());
                    if(result==true)
                    {
                        Toast.makeText(getApplicationContext(), "Subject Data Added", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "New Subject Table Created", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Subject Data Not Added", Toast.LENGTH_SHORT).show();
            }

        }
        else if(boole==false)
        {
            //Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            //Toast.makeText(getApplicationContext(), "Student Data Not Added", Toast.LENGTH_SHORT).show();
            dlgAlert.setMessage("Subject Already Exists");
            dlgAlert.setTitle("Error Message...");
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

        }
    }
}
