package com.example.qlsv.SQLite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.qlsv.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private SQLiteDatabase db;

    public StudentDao(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Student> get(String sql, String... selectArgs) {
        List<Student> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()) {
            Student u = new Student();
            u.setMasv(cursor.getString(cursor.getColumnIndex("masv")));
            u.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            u.setName(cursor.getString(cursor.getColumnIndex("name")));
            u.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            u.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            u.setLop(cursor.getString(cursor.getColumnIndex("lop")));

            list.add(u);
        }
        return list;
    }

    public List<Student> getAll() {
        String sql = "select * from SinhViens";
        return get(sql);
    }

    public Student getByPhone(String phone) {
        String sql = "select * from StudentS where phone = ?";

        List<Student> list = get(sql, phone);

        return list.get(0);
    }

    public long insert(Student Student) {
        ContentValues values = new ContentValues();
        values.put("masv", Student.getMasv());
        values.put("name", Student.getName());
        values.put("email", Student.getEmail());
        values.put("phone", Student.getPhone());
        values.put("lop", Student.getLop());
        values.put("password", Student.getPassword());

        return db.insert("Sinhviens", null, values);
    }

    public long update(Student Student) {
        ContentValues values = new ContentValues();
        values.put("masv", Student.getMasv());
        values.put("name", Student.getName());
        values.put("email", Student.getEmail());
        values.put("phone", Student.getPhone());
        values.put("lop", Student.getLop());
        values.put("password", Student.getPassword());

        return db.update("Sinhviens", values, "masv =?", new String[]{Student.getMasv()});
    }
    public long del(Student Student) {
        ContentValues values = new ContentValues();

        return db.delete("Sinhviens", "masv = ?", new String[]{Student.getMasv()});
    }


    public boolean checkPhone(String phone) {
        Cursor cursor = db.rawQuery("select * from StudentS where phone = ?", new String[]{phone});
        if (cursor.getCount() > 0) {
            return true;
        } else return false;
    }

    public boolean checkPhonePassword(String phone, String pass) {
        Cursor cursor = db.rawQuery("select * from StudentS where phone = ? and password = ?", new String[]{phone, pass});
        if (cursor.getCount() > 0) {
            return true;
        } else return false;
    }

    public String getIdMax(){
        String sql = "select max(id) from StudentS";

        String max = String.valueOf(get(sql));
        return max;
    }
}
