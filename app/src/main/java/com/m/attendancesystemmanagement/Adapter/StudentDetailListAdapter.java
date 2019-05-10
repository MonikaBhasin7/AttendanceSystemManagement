package com.m.attendancesystemmanagement.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m.attendancesystemmanagement.Model.Student;
import com.m.attendancesystemmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailListAdapter extends RecyclerView.Adapter<StudentDetailListAdapter.StudentListViewHolder>  {
    List<Student> studentList=new ArrayList<Student>();

    public StudentDetailListAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }
    @NonNull
    @Override
    public StudentListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.student_detail_item_layout,viewGroup,false);
        return new StudentDetailListAdapter.StudentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListViewHolder holder, int i) {

        final Student student=studentList.get(i);


        holder.id.setText(student.getId());
        holder.name.setText(student.getName());
        holder.email.setText(student.getEmail());
        holder.phone_number.setText(student.getPhone_number());
        holder.gender.setText(student.getGender());
        /*if(student.getGender()=="female")
        {
            holder.gender.setText("F");
        }
        if(student.getGender()=="male")
        {
            holder.gender.setText("M");
        }
        if(student.getElective()=="Information Security")
        {
            holder.elective.setText("IS");
        }
        if(student.getElective()=="Web Technology")
        {
            holder.elective.setText("WT");
        }*/



    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentListViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView email;
        TextView phone_number;
        TextView gender;
        public StudentListViewHolder(@NonNull View itemView) {
            super(itemView);

            id=(TextView)itemView.findViewById(R.id.id);
            name=(TextView)itemView.findViewById(R.id.name);
            email=(TextView)itemView.findViewById(R.id.email);
            phone_number=(TextView)itemView.findViewById(R.id.phone_number);
            gender=(TextView)itemView.findViewById(R.id.gender);

        }
    }
}
