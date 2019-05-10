package com.m.attendancesystemmanagement;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.m.attendancesystemmanagement.Adapter.AttendanceAdapter;
import com.m.attendancesystemmanagement.Adapter.StudentDetailListAdapter;
import com.m.attendancesystemmanagement.Model.Student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TakeAttendanceList extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    String Year;
    String Month;
    String Day;
    public List<Student> checkedstudentList=new ArrayList<Student>();
    RecyclerView recyclerView;
    List<Student> studentlist=new ArrayList<Student>();
    AttendanceAdapter mAdapter;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance_list);

        Bundle bundle=getIntent().getExtras();
        data=bundle.getString("subjectid");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle(data);

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        takeattendance();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(currentDateString);

        /*Toast.makeText(this, "Year"+year, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Month"+month, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "day"+dayOfMonth, Toast.LENGTH_SHORT).show();*/

        Year=String.valueOf(year);
        Month=String.valueOf(month+1);
        Day=String.valueOf(dayOfMonth);
    }

    public void takeattendance()
    {
        //Toast.makeText(this, "data"+data, Toast.LENGTH_SHORT).show();
        Database dbs=new Database(getBaseContext());

            studentlist=dbs.getStudentDetail();

            recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
            LayoutAnimationController animation1 = AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_animation_fall_down);
            recyclerView.setLayoutAnimation(animation1);
            checkedstudentList.clear();
            mAdapter = new AttendanceAdapter(getApplicationContext(),studentlist,checkedstudentList);

            recyclerView.setHasFixedSize(true);
                /*RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());*/

            RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

    }
    public void submitattendance(View view)
    {
        if(!checkedstudentList.isEmpty())
        {

            Database dbs=new Database(getBaseContext());
            //Toast.makeText(getApplicationContext(),"NUmber of Checked Student->"+checkedstudentList.size(),Toast.LENGTH_SHORT).show();
            boolean result=dbs.check_date(data.toString(),Year,Month,Day);
            if(result==true)
            {
                boolean change=dbs.addattendance(checkedstudentList,data.toString(),Year,Month,Day);
                if(change==true)
                {
                    boolean change_count=dbs.add_to_subject_attendance_count_list_increase_by_1(data.toString());
                    if(change_count==true)
                    {
                        Toast.makeText(getApplicationContext(),"Attendance Successfully Taken",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_SHORT).show();
                }
            }

        }
        else if(checkedstudentList.isEmpty())
        {
            Toast.makeText(this, "Mark the Attendance of Atleast 1 Student", Toast.LENGTH_SHORT).show();
        }
    }
}
