package com.example.qlsv.adapter;

import static java.util.Collections.addAll;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qlsv.MainActivity;
import com.example.qlsv.R;
import com.example.qlsv.SQLite.StudentDao;
import com.example.qlsv.model.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private List<Student> list;
    private StudentAdapter studentAdapter;
    private ListView listView_Student_241;


    public StudentAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Student Student = list.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_student, null);
        }
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvMaSV = view.findViewById(R.id.tv_masv);
        Button btn_edit = view.findViewById(R.id.btn_edit);
        Button btn_del = view.findViewById(R.id.btn_del);

        //Chỉnh sửa thông tin sinh viên
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEdit(Student);
            }
        });

        //Xóa sinh viên
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Xóa sinh viên")
                        .setMessage("Bạn có chắc chăn muốn xóa ?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StudentDao dao = new StudentDao(context);
                                dao.del(Student);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_delete)
                        .show();
            }
        });

        tvName.setText(Student.getName());
        tvMaSV.setText(Student.getMasv());

        return view;
    }
    private void onClickEdit(Student student) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_student);
        dialog.show();

        TextView title = dialog.findViewById(R.id.tv_title);
        title.setText("Chỉnh sửa thông tin");
        EditText msv = dialog.findViewById(R.id.edt_msv);
        EditText phone = dialog.findViewById(R.id.edt_phone);
        EditText name = dialog.findViewById(R.id.edt_name);
        EditText lop = dialog.findViewById(R.id.edt_lop);
        EditText email = dialog.findViewById(R.id.edt_email);
        EditText password = dialog.findViewById(R.id.edt_password);
        Button btn_add = (Button) dialog.findViewById(R.id.btn_yes);
        btn_add.setText("EDIT");
        Button btn_exit = (Button) dialog.findViewById(R.id.btn_exit);

        msv.setText(student.getMasv());
        phone.setText(student.getPhone());
        name.setText(student.getName());
        lop.setText(student.getLop());
        email.setText(student.getEmail());
        password.setText(student.getPassword());

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

                StudentDao dao = new StudentDao(context);

                dao.update(student);
                setDataStudent(view);
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
    public void setDataStudent(View view) {
        StudentDao dao = new StudentDao(context.getApplicationContext());
        list = dao.getAll();
        studentAdapter = new StudentAdapter(context.getApplicationContext(), list);
        listView_Student_241 = view.findViewById(R.id.lv_student);
        listView_Student_241.setAdapter(studentAdapter);
    }
}
