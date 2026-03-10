package com.example.lab6_sqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtNote;
    Button btnSave;
    RecyclerView rvNotes;

    DatabaseHandler db;
    NoteAdapter adapter;
    List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        edtNote = findViewById(R.id.edt_note);
        btnSave = findViewById(R.id.btn_save);
        rvNotes = findViewById(R.id.rv_notes);

        db = new DatabaseHandler(this);

        rvNotes.setLayoutManager(new LinearLayoutManager(this));

        loadNotes();

        btnSave.setOnClickListener(v -> {

            String note = edtNote.getText().toString();

            if(!note.isEmpty()){

                db.addNote(note);

                edtNote.setText("");

                loadNotes();
            }
        });
    }

    private void loadNotes(){

        noteList = db.getAllNotes();

        adapter = new NoteAdapter(this, noteList, db);

        rvNotes.setAdapter(adapter);
    }

}