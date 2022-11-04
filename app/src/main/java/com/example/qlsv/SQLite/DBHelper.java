package com.example.qlsv.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_name = "QLSV";
    public static final int DB_version = 1;
    public static final String create_tb_sinhvien = "create table if not exists SinhViens("
            + "masv text primary key, "
            + "phone text not null, "
            + "name text not null, "
            + "email text not null, "
            + "password text not null,"
            + "lop text not null)";
    public static final String insert_data_tb_sinhvien = "insert into SinhViens values" +
            "('2050531200241', '0339354373', 'Nha', 'nvp@gmail.com', '123', '20T2')," +
            "('2050531200244', '0339354373', 'Vo Phong Nha', 'nvp@gmail.com', '123', '20T2')";


    public DBHelper(Context context) {
        super(context, DB_name, null, DB_version);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_tb_sinhvien);
        sqLiteDatabase.execSQL(insert_data_tb_sinhvien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists SinhViens");
    }
}
