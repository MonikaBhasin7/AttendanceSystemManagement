package com.m.attendancesystemmanagement;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.m.attendancesystemmanagement.Adapter.AttendanceAdapter;
import com.m.attendancesystemmanagement.Adapter.ShowAttendanceAdapter;
import com.m.attendancesystemmanagement.Model.Month;
import com.m.attendancesystemmanagement.Model.Student;

import java.util.ArrayList;
import java.util.List;

public class ShowAttendance extends AppCompatActivity {


    int month_filter=0,year_filter=0;
    String selected_year="-1";
    String selected_month="-1";
    List<Month> month_list=new ArrayList<Month>();
    RecyclerView recyclerView;
    List<Student> studentlist=new ArrayList<Student>();
    ShowAttendanceAdapter mAdapter;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_attendance);



        Bundle bundle=getIntent().getExtras();
        data=bundle.getString("subjectid");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(data.toString().trim());
        //setting the title
        toolbar.setTitle(data);

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        add_month_list();


        Spinner mySpinner = (Spinner) findViewById(R.id.month_spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ShowAttendance.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.month_array));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                selected_month=String.valueOf(i);
                //Toast.makeText(getApplicationContext(),"selected_month->"+selected_month,Toast.LENGTH_SHORT).show();
                month_filter=1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner mySpinner_year = (Spinner) findViewById(R.id.year_spinner);

        ArrayAdapter<String> myAdapter_year = new ArrayAdapter<String>(ShowAttendance.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.year_array));
        myAdapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner_year.setAdapter(myAdapter_year);

        mySpinner_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int yeary=i+1999;
                selected_year=String.valueOf(yeary);
                //
                // Toast.makeText(getApplicationContext(),"i->"+selected_year,Toast.LENGTH_SHORT).show();
                year_filter=1;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        showAttendance();


    }

    public void ApplyFilter(View view)
    {
        if((month_filter==1 || year_filter==1)&&!selected_year.equals("-1") && !selected_month.equals("-1"))
        {
            showAttendance();
            month_filter=0;
            year_filter=0;
        }
        else if(selected_year.equals("1999") && selected_month.equals("0"))
        {
            showAttendance();
            month_filter=0;
            year_filter=0;
        }
    }

    void add_month_list()
    {
        month_list.clear();
        Month month0=new Month("0","Not Selected");
        month_list.add(month0);
        Month month=new Month("1","January");
        month_list.add(month);
        Month month1=new Month("2","February");
        month_list.add(month);
        Month month2=new Month("3","March");
        month_list.add(month);
        Month month3=new Month("4","April");
        month_list.add(month);
        Month month4=new Month("5","May");
        month_list.add(month);
        Month month5=new Month("6","June");
        month_list.add(month);
        Month month6=new Month("7","July");
        month_list.add(month);
        Month month7=new Month("8","August");
        month_list.add(month);
        Month month8=new Month("9","September");
        month_list.add(month);
        Month month9=new Month("10","October");
        month_list.add(month);
        Month month10=new Month("11","November");
        month_list.add(month);
        Month month11=new Month("12","December");
        month_list.add(month);
    }
    public void showAttendance()
    {
        //Toast.makeText(this, "data"+data, Toast.LENGTH_SHORT).show();
        Database dbs=new Database(getBaseContext());

        studentlist=dbs.getStudentDetail();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LayoutAnimationController animation1 = AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animation1);
        mAdapter = new ShowAttendanceAdapter(getApplicationContext(),studentlist,data,selected_month,selected_year);

        recyclerView.setHasFixedSize(true);
                /*RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());*/

        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

}
