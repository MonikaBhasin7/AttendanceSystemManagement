package com.m.attendancesystemmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import com.m.attendancesystemmanagement.Adapter.StudentDetailListAdapter;
import com.m.attendancesystemmanagement.Model.Student;

import java.util.ArrayList;
import java.util.List;

public class ShowStudentDetail extends AppCompatActivity {

    StudentDetailListAdapter mAdapter;
    RecyclerView recyclerView;
    List<Student> studentlist=new ArrayList<Student>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_detail);

        getDetail();
    }

    public void getDetail()
    {
        Database dbs=new Database(getBaseContext());
        studentlist=dbs.getStudentDetail();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LayoutAnimationController animation1 = AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animation1);
        mAdapter = new StudentDetailListAdapter(studentlist);
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
