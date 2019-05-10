package com.m.attendancesystemmanagement.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.m.attendancesystemmanagement.Database;
import com.m.attendancesystemmanagement.Model.Student;
import com.m.attendancesystemmanagement.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ShowAttendanceAdapter extends RecyclerView.Adapter<ShowAttendanceAdapter.ShowAttendanceViewHolder>{


    String selected_month="-1";
    String selected_year="-1";
    String selected_subject_id;
    List<Student> studentList=new ArrayList<Student>();
    Context context;
    public ShowAttendanceAdapter(Context context,List<Student> studentList,String data,String selected_month,String selected_year) {
        this.studentList = studentList;
        this.context=context;
        this.selected_subject_id=data;
        this.selected_month=selected_month;
        this.selected_year=selected_year;
    }


    @NonNull
    @Override
    public ShowAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.show_attendance_item_layout,viewGroup,false);
        return new ShowAttendanceAdapter.ShowAttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAttendanceAdapter.ShowAttendanceViewHolder holder, int i) {
        final Student student=studentList.get(i);


        holder.id.setText(student.getId());
        if(selected_month.equals("-1") && selected_year.equals("-1") || (selected_month.equals("0") && selected_year.equals("1999")))
        {
            //Toast.makeText(context,"Null Equal",Toast.LENGTH_SHORT).show();
            Database dbs=new Database(context);
            int count=dbs.showattendance(selected_subject_id,student.getId());
            //Toast.makeText(context,"Resource count->"+count,Toast.LENGTH_SHORT).show();
            holder.attendance_number.setText(String.valueOf(count));
            int max_lecture=dbs.add_to_subject_attendance_count_list_show(selected_subject_id);
           Toast.makeText(context,"Max Attendance->"+max_lecture,Toast.LENGTH_SHORT).show();

            //max_score.setText(max_lecture);
            //Toast.makeText(context,"Max_Lecture->"+max_lecture,Toast.LENGTH_SHORT).show();
            float count_by= (float) 0.0;
            if(max_lecture>0)
            {
                count_by=count/max_lecture;
            }
            float per=count_by*100;
            if(per<60)
            {
                holder.layout.setBackgroundColor(Color.parseColor("#e3ecf9"));
            }
            else if(per>=60)
            {
                holder.layout.setBackgroundColor(Color.parseColor("#e3ecf9"));
            }
        }
        else if(!selected_month.equals("-1") && !selected_year.equals("-1"))
        {
            //Toast.makeText(context,"Null Not Equal",Toast.LENGTH_SHORT).show();
            Database dbs=new Database(context);
            //Toast.makeText(context,"Month->"+selected_month+"Year->"+selected_year,Toast.LENGTH_SHORT).show();
            int count=dbs.showattendance_filter(selected_subject_id,student.getId(),selected_month,selected_year);
            //Toast.makeText(context,"Resource count->"+count,Toast.LENGTH_SHORT).show();
            holder.attendance_number.setText(String.valueOf(count));
            //max_score_ans.setVisibility(View.INVISIBLE);
            //max_score.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ShowAttendanceViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        TextView attendance_number;
        TextView id;
        public ShowAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            id=(TextView)itemView.findViewById(R.id.id);
            attendance_number=(TextView)itemView.findViewById(R.id.attendance_number);
            layout=(LinearLayout) itemView.findViewById(R.id.layout);
        }
    }
}
