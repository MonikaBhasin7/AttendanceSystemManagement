package com.m.attendancesystemmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.m.attendancesystemmanagement.Adapter.ListAdapter;
import com.m.attendancesystemmanagement.Adapter.StudentDetailListAdapter;
import com.m.attendancesystemmanagement.Model.Subject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListAdapter mAdapter;
    RecyclerView recyclerView;
    List<String> text_list = new ArrayList<String>();
    List<Integer> image_list = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.hide();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        text_list.add("Take Attendance");
        text_list.add("Add Student Data");
        text_list.add("Add Subject");
        text_list.add("Show Student Detail");
        text_list.add("Show Student Attendance");

        image_list.add(R.drawable.takeaatendancebig);
        image_list.add(R.drawable.ic_add_circle_black_24dp);
        image_list.add(R.drawable.addsubject);
        image_list.add(R.drawable.showstudentdetail);
        image_list.add(R.drawable.showstudentattendance);

        getdetail();

    }

    public void getdetail()
    {
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LayoutAnimationController animation1 = AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animation1);
        mAdapter = new ListAdapter(text_list,image_list);
        recyclerView.setHasFixedSize(true);
                /*RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());*/

        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String texty=text_list.get(position);
                //Toast.makeText(getApplicationContext(), cricketer.getCname() + " is selected!", Toast.LENGTH_SHORT).show();
                if(texty.equals("Take Attendance"))
                {
                    Intent intent =new Intent(getApplicationContext(),AttendanceSubjectList.class);
                    startActivity(intent);
                }
                else if(texty.equals("Add Student Data"))
                {
                    Intent intent =new Intent(getApplicationContext(),AddStudentData.class);
                    startActivity(intent);
                }
                else if(texty.equals("Add Subject"))
                {
                    Intent intent =new Intent(getApplicationContext(),AddSubject.class);
                    startActivity(intent);
                }
                else if(texty.equals("Show Student Detail"))
                {
                    Intent intent =new Intent(getApplicationContext(),ShowStudentDetail.class);
                    startActivity(intent);
                }
                else if(texty.equals("Show Student Attendance"))
                {
                    Intent intent =new Intent(getApplicationContext(),ShowAttendanceSubjectList.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add_student_data) {
            Intent intent=new Intent(getApplicationContext(),AddStudentData.class);
            startActivity(intent);

        } else if (id == R.id.show_student_detail) {
            Intent intent=new Intent(getApplicationContext(),ShowStudentDetail.class);
            startActivity(intent);
        } else if (id == R.id.show_student_attendance) {
            Intent intent=new Intent(getApplicationContext(),ShowAttendanceSubjectList.class);
            startActivity(intent);
        }
        else if (id == R.id.add_subject) {
            Intent intent=new Intent(getApplicationContext(),AddSubject.class);
            startActivity(intent);
        }
        else if (id == R.id.take_attendance) {
            Intent intent=new Intent(getApplicationContext(),AttendanceSubjectList.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
