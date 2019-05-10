package com.m.attendancesystemmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.m.attendancesystemmanagement.Adapter.AttendanceSubjectListAdapter;
import com.m.attendancesystemmanagement.Adapter.StudentDetailListAdapter;
import com.m.attendancesystemmanagement.Model.Student;
import com.m.attendancesystemmanagement.Model.Subject;

import java.util.ArrayList;
import java.util.List;

public class AttendanceSubjectList extends AppCompatActivity {


    AttendanceSubjectListAdapter mAdapter;
    List<Subject> subjectList=new ArrayList<Subject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_subject_list);

        getDetail();
    }
    public void getDetail()
    {
        Database dbs=new Database(getBaseContext());
        subjectList=dbs.getSubject();

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LayoutAnimationController animation1 = AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(animation1);
        mAdapter=new AttendanceSubjectListAdapter(subjectList);
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
                Subject subject = subjectList.get(position);
                //Toast.makeText(getApplicationContext(), cricketer.getCname() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),TakeAttendanceList.class);
                Bundle bundle=new Bundle();
                bundle.putString("subjectid",subject.getSubject_id().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
