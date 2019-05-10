package com.m.attendancesystemmanagement.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m.attendancesystemmanagement.Interfaces.ItemClickListener;
import com.m.attendancesystemmanagement.Model.Student;
import com.m.attendancesystemmanagement.Model.Subject;
import com.m.attendancesystemmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class AttendanceSubjectListAdapter extends RecyclerView.Adapter<AttendanceSubjectListAdapter.AttendanceSubjectListViewHolder> {


    List<Subject> subjectList=new ArrayList<Subject>();

    public AttendanceSubjectListAdapter(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }
    @NonNull
    @Override
    public AttendanceSubjectListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.subject_detail_item_layout,viewGroup,false);
        return new AttendanceSubjectListAdapter.AttendanceSubjectListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceSubjectListViewHolder holder, int i) {

        final Subject subject=subjectList.get(i);

        holder.subject_id.setText(subject.getSubject_id());
        holder.subject_name.setText(subject.getSubject_name());
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class AttendanceSubjectListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ItemClickListener itemClickListener;
        TextView subject_id;
        TextView subject_name;
        public AttendanceSubjectListViewHolder(@NonNull View itemView) {
            super(itemView);

            subject_id=(TextView)itemView.findViewById(R.id.subject_id);
            subject_name=(TextView)itemView.findViewById(R.id.subject_name);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
    }
}
