package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.qlsv.SQLite.DBHelper;
import com.example.qlsv.SQLite.StudentDao;
import com.example.qlsv.adapter.StudentAdapter;
import com.example.qlsv.model.Student;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    StudentAdapter studentAdapter;
    ListView listView_Student_241;
    Button btn_Add_Student_241;

    private List<Student> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper DB = new DBHelper(this);

        listView_Student_241 = findViewById(R.id.lv_student);
        btn_Add_Student_241 = findViewById(R.id.btn_addSV);

        StudentDao dao = new StudentDao(this);

        showDataStudent();

        //Thêm sinh viên
        btn_Add_Student_241.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_student);
                dialog.show();

                EditText msv = dialog.findViewById(R.id.edt_msv);
                EditText phone = dialog.findViewById(R.id.edt_phone);
                EditText name = dialog.findViewById(R.id.edt_name);
                EditText lop = dialog.findViewById(R.id.edt_lop);
                EditText email = dialog.findViewById(R.id.edt_email);
                EditText password = dialog.findViewById(R.id.edt_password);

                Button btn_add = (Button) dialog.findViewById(R.id.btn_yes);
                Button btn_exit = (Button) dialog.findViewById(R.id.btn_exit);
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Student student = new Student();
                        student.setMasv(msv.getText().toString().trim());
                        student.setName(name.getText().toString());
                        student.setLop(lop.getText().toString());
                        student.setEmail(email.getText().toString().trim());
                        student.setPassword(password.getText().toString().trim());
                        student.setPhone(phone.getText().toString().trim());

                        dao.insert(student);
                        showDataStudent();
                        dialog.dismiss();
                    }
                });
                btn_exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });
    }

    public void showDataStudent() {
        StudentDao dao = new StudentDao(this);
        list = dao.getAll();
        studentAdapter = new StudentAdapter(this, list);
        listView_Student_241.setAdapter(studentAdapter);
    }
}