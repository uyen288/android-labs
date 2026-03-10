package com.example.lab7_recyclerview;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvStudents;
    FloatingActionButton btnAdd;

    DatabaseHandler db;
    StudentAdapter adapter;
    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        rvStudents = findViewById(R.id.rv_students);
        btnAdd = findViewById(R.id.btn_add);
        db = new DatabaseHandler(this);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));

        insertSampleData();
        loadStudents();
        
        btnAdd.setOnClickListener(v -> showAddDialog());
    }

    private void insertSampleData() {
        db.addStudent(new Student("https://i.pravatar.cc/150?img=1","Nguyen Van A","a@gmail.com"));
        db.addStudent(new Student("https://i.pravatar.cc/150?img=2","Tran Van B","b@gmail.com"));
        db.addStudent(new Student("https://i.pravatar.cc/150?img=3","Le Van C","c@gmail.com"));
    }

    private void loadStudents(){
        studentList = db.getAllStudents();
        adapter = new StudentAdapter(this,studentList,db);
        rvStudents.setAdapter(adapter);
    }

    private void showAddDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText edtAvatar = new EditText(this);
        edtAvatar.setHint("Avatar image path");

        EditText edtName = new EditText(this);
        edtName.setHint("Name");

        EditText edtEmail = new EditText(this);
        edtEmail.setHint("Email");

        layout.addView(edtAvatar);
        layout.addView(edtName);
        layout.addView(edtEmail);

        builder.setTitle("Add Student");
        builder.setView(layout);

        builder.setPositiveButton("Add",(d,w)->{

            String avatar = edtAvatar.getText().toString();
            String name = edtName.getText().toString();
            String email = edtEmail.getText().toString();

            Student st = new Student(avatar,name,email);

            db.addStudent(st);

            loadStudents();
        });

        builder.setNegativeButton("Cancel",null);

        builder.show();
    }
}