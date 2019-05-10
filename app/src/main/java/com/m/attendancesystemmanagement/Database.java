package com.m.attendancesystemmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import com.m.attendancesystemmanagement.Model.Student;
import com.m.attendancesystemmanagement.Model.Subject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Monika Bhasin on 07-08-2018.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DatabaseName="AttendanceSystemManagement.db";
    public static final String TableNameAddData = "student_detail";
    public static final String TableName2="order_view_details";
    public static final String TableNameAddSubject = "subject_detail";
    public static final String TotalAttendancePerSubject = "totalAttendancePerSubject";
    private static final int DB_VER=1;

    public static final String Id = "Id";
    public static final String Name = "Name";
    public static final String Email = "Email";
    public static final String PhoneNumber= "PhoneNumber";
    public static final String Gender = "Gender";
    public static final String Elective="Elective";


    public static final String SubjectId = "SubjectId";
    public static final String SubjectName = "SubjectName";
    public static final String Elective_bool = "Elective_bool";

    public static final String Attendance = "Attendance";
    public static final String Year="Year";
    public static final String Month="Month";
    public static final String Day="Day";

    public static final String Count="Count";

    public Database(Context context) {
        super(context,DatabaseName,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table " + TableNameAddData +" (Id TEXT,Name TEXT,Email TEXT,PhoneNumber TEXT,Gender TEXT)");
            //db.execSQL("create table " + TableName2 +" (Date TEXT,Time TEXT,PriceOrder TEXT,Address TEXT)");
            db.execSQL("create table " + TableNameAddSubject +" (SubjectId TEXT,SubjectName TEXT,Elective_bool TEXT)");
            db.execSQL("create table " + TotalAttendancePerSubject +" (SubjectId TEXT,Count TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TableNameAddData);
        db.execSQL("DROP TABLE IF EXISTS "+TableName2);
        db.execSQL("DROP TABLE IF EXISTS "+TableNameAddSubject);
        onCreate(db);
    }

    public boolean addToStudentDetail(Student student)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Id,student.getId());
        contentValues.put(Name,student.getName());
        contentValues.put(Email,student.getEmail());
        contentValues.put(PhoneNumber,student.getPhone_number());
        contentValues.put(Gender,student.getGender());
        long result = db.insert(TableNameAddData,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean check(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableNameAddData,null);

        if(res.moveToFirst())
        {
            do {
                String a=res.getString(res.getColumnIndex(Id));

                if(id.equals(a))
                {
                    return false;
                }
            }while (res.moveToNext());

        }
        return true;
    }


    public List<Student> getStudentDetail()
    {
        List<Student> result;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableNameAddData,null);
        result = new ArrayList<>();


        try {
            if(res.moveToFirst())
            {
                do {
                    String id=res.getString(res.getColumnIndex(Id));
                    String name=res.getString(res.getColumnIndex(Name));
                    String email=res.getString(res.getColumnIndex(Email));
                    String phone_number=res.getString(res.getColumnIndex(PhoneNumber));
                    String gender=res.getString(res.getColumnIndex(Gender));

                    Student student=new Student(id,name,email,phone_number,gender,"0");
                    result.add(student);

                }while (res.moveToNext());

            }

        }
        catch (IllegalStateException e)
        {

        }
        return result;
    }


    public boolean addToSubject(Subject subject)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SubjectId,subject.getSubject_id());
        contentValues.put(SubjectName,subject.getSubject_name());
        contentValues.put(Elective_bool,subject.getElective_bool());
        long result = db.insert(TableNameAddSubject,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public List<Subject> getSubjecty()
    {
        List<Subject> result;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableNameAddSubject,null);
        result = new ArrayList<>();


        if(res.moveToFirst())
        {
            do {
                String subject_id=res.getString(res.getColumnIndex(SubjectId));
                String subject_name=res.getString(res.getColumnIndex(SubjectName));
                String elective_bool=res.getString(res.getColumnIndex(Elective_bool));

                if(elective_bool.equals("1"))
                {
                    Subject subject=new Subject(subject_id,subject_name,elective_bool);
                    result.add(subject);
                }

            }while (res.moveToNext());

        }
        return result;
    }

    public boolean check_subject(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableNameAddSubject,null);

        if(res.moveToFirst())
        {
            do {
                String a=res.getString(res.getColumnIndex(SubjectId));

                if(id.equals(a))
                {
                    return false;
                }
            }while (res.moveToNext());

        }
        return true;
    }
    public boolean make_subject_table(String subject_table_name)
    {
        /*subject_change_id=1;
        subject_change_name=subject_table_name;
        onCreate(db1);*/
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table " + subject_table_name +"(Id TEXT,Year TEXT,Month TEXT,Day TEXT,Attendance TEXT)");
        return true;
    }

    public boolean add_to_subject_attendance_count_list(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SubjectId,id);
        contentValues.put(Count,"0");
        long result = db.insert(TotalAttendancePerSubject,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public int add_to_subject_attendance_count_list_show(String subject_idi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TotalAttendancePerSubject,null);
        int current_total_attendance = 0;
        if(res.moveToFirst())
        {
            do {
                String subject_id=res.getString(res.getColumnIndex(SubjectId));
                String subject_count_attendance=res.getString(res.getColumnIndex(Count));
                if(subject_idi.equals(subject_id))
                {
                    current_total_attendance=Integer.parseInt(subject_count_attendance);
                    return current_total_attendance;
                }

            }while (res.moveToNext());
        }
        return current_total_attendance;
    }

    public boolean add_to_subject_attendance_count_list_decrease_by_1(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TotalAttendancePerSubject,null);
        int current_total_attendance = 0;
        if(res.moveToFirst())
        {
            do {
                String subject_id=res.getString(res.getColumnIndex(SubjectId));
                String subject_count_attendance=res.getString(res.getColumnIndex(Count));
                if(id.equals(subject_id))
                {
                    current_total_attendance=Integer.parseInt(subject_count_attendance);
                }

            }while (res.moveToNext());
        }
        current_total_attendance=current_total_attendance-1;
        ContentValues values=new ContentValues();
        values.put("Count",String.valueOf(current_total_attendance));
        db.update(TotalAttendancePerSubject,values,"SubjectId=?",new String[]{id});
        return true;

    }

    public boolean add_to_subject_attendance_count_list_increase_by_1(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TotalAttendancePerSubject,null);
        int current_total_attendance = 0;
        if(res.moveToFirst())
        {
            do {
                String subject_id=res.getString(res.getColumnIndex(SubjectId));
                String subject_count_attendance=res.getString(res.getColumnIndex(Count));
                if(id.equals(subject_id))
                {
                    current_total_attendance=Integer.parseInt(subject_count_attendance);
                }

            }while (res.moveToNext());
        }
        current_total_attendance=current_total_attendance+1;
        ContentValues values=new ContentValues();
        values.put("Count",String.valueOf(current_total_attendance));
        db.update(TotalAttendancePerSubject,values,"SubjectId=?",new String[]{id});
        return true;

    }

    public List<Subject> getSubject()
    {
        List<Subject> result;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableNameAddSubject,null);
        result = new ArrayList<>();


        if(res.moveToFirst())
        {
            do {
                String subject_id=res.getString(res.getColumnIndex(SubjectId));
                String subject_name=res.getString(res.getColumnIndex(SubjectName));
                String elective_bool=res.getString(res.getColumnIndex(Elective_bool));

                Subject subject=new Subject(subject_id,subject_name,elective_bool);
                result.add(subject);

            }while (res.moveToNext());

        }
        return result;
    }

    boolean checkElective(String subjectid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableNameAddSubject,null);

        if(res.moveToFirst())
        {
            do {
                String a=res.getString(res.getColumnIndex(SubjectId));
                String b=res.getString(res.getColumnIndex(Elective_bool));

                if(subjectid.equals(a))
                {
                    if(b=="0")
                    {
                        return false;
                    }
                    else if(b=="1")
                    {
                        return true;
                    }
                }
            }while (res.moveToNext());

        }
        return true;
    }

    boolean addattendance(List<Student> checkedStudentList,String subject_table_name,String yeary,String monthy,String dayy)
    {
        for(Student i:checkedStudentList)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Id,i.getId());
            contentValues.put(Year,yeary);
            contentValues.put(Month,monthy);
            contentValues.put(Day,dayy);
            contentValues.put(Attendance,"1");
            long result = db.insert(subject_table_name,null ,contentValues);
        }
        return true;
    }

    public int showattendance(String subject_table_name,String student_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+subject_table_name,null);

        //res.getCount();
        int count=0;
        if(res.moveToFirst())
        {
            do {
                String id=res.getString(res.getColumnIndex(Id));
                if(id.equals(student_id))
                {
                    count=count+1;
                }
            }while (res.moveToNext());

        }
        return count;
    }

    public int showattendance_filter(String subject_table_name,String student_id,String selected_month,String selected_year)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+subject_table_name,null);

        //res.getCount();
        int count=0;
        if(res.moveToFirst())
        {
            do {
                String id=res.getString(res.getColumnIndex(Id));
                String monthy=res.getString(res.getColumnIndex(Month));
                String yeary=res.getString(res.getColumnIndex(Year));
                if(id.equals(student_id) && monthy.equals(selected_month) && yeary.equals(selected_year) )
                {
                    count=count+1;
                }
            }while (res.moveToNext());

        }
        return count;
    }

    public boolean check_date(String subject_table_name,String year,String month,String day)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+subject_table_name,null);

        int old_data_size=res.getCount();
        db.delete(subject_table_name, "Year=? AND Month=? AND Day=?", new String[]{year,month,day});

        Cursor res1 = db.rawQuery("select * from "+subject_table_name,null);

        int old_data_size1=res1.getCount();
        if(old_data_size1<old_data_size)
        {
            //Duplicacy present but now removed
            //Decrease TotalAttendance Count per subject by 1
            add_to_subject_attendance_count_list_decrease_by_1(subject_table_name);
            return true;
        }
        else
        {
            //No Duplicacy Present
            return true;
        }

    }
    /*public boolean addToOrderView(ViewOrderItem viewOrderItem)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Date,viewOrderItem.getDate());
        contentValues.put(Time,viewOrderItem.getTime());
        contentValues.put(PriceOrder,viewOrderItem.getPrice());
        contentValues.put(Address,viewOrderItem.getAddress());
        long result = db.insert(TableName2,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



    public void update(String name, String number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Quantity,number);
        String whereArgs[] = {name};
        db.update(TableName,contentValues,"ProductName=?", whereArgs);
    }

    public List<ViewOrderItem> getOrderViewCart()
    {
        List<ViewOrderItem> result;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableName2,null);
        result = new ArrayList<>();


        if(res.moveToFirst())
        {
            do {
                String a=res.getString(res.getColumnIndex(Date));
                String b=res.getString(res.getColumnIndex(Time));
                String c=res.getString(res.getColumnIndex(PriceOrder));
                String d=res.getString(res.getColumnIndex(Address));

                ViewOrderItem viewOrderItem=new ViewOrderItem(a,b,c,d);
                result.add(viewOrderItem);

            }while (res.moveToNext());

        }
        return result;
    }

    public void delete(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //db.delete(TABLE_NAME, IMAGE + "=" + image_name, null);
        //db.execSQL("DELETE FROM " + TableName2 + " WHERE " + Task + "=" +task_name + "=");
        db.delete(TableName, ProductName+ "=?", new String[]{String.valueOf(name)});


        //database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + CONTACTS_COLUMN_TITLE + "= '" + title + "'");
    }

    public void deleteall()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TableName, null, null);
    }*/
}
