package com.m.attendancesystemmanagement.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.m.attendancesystemmanagement.Interfaces.ItemClickListener;
import com.m.attendancesystemmanagement.Model.Student;
import com.m.attendancesystemmanagement.R;
import com.m.attendancesystemmanagement.TakeAttendanceList;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    public List<Student> checkedstudentList=new ArrayList<Student>();
    List<Student> studentList=new ArrayList<Student>();
    Context context;
    public AttendanceAdapter(Context context,List<Student> studentList,List<Student> checkedstudentList) {
        this.studentList = studentList;
        this.context=context;
        this.checkedstudentList=checkedstudentList;
    }
    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.attendance_item_layout,viewGroup,false);
        return new AttendanceAdapter.AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int i) {

        final Student student=studentList.get(i);


        holder.id.setText(student.getId());
        holder.setItemClickListener(new AttendanceViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CheckBox checkBox=(CheckBox)v;
                if(checkBox.isChecked())
                {
                    checkedstudentList.add(student);
                }
                else if(!checkBox.isChecked())
                {
                    checkedstudentList.remove(student);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class AttendanceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ItemClickListener itemClickListener;
        CheckBox present;
        TextView id;
        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            present = (CheckBox) itemView.findViewById(R.id.present);
            id = (TextView) itemView.findViewById(R.id.id);

            present.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener=ic;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }

        interface ItemClickListener {

            void onItemClick(View v, int position);
        }
    }

}
