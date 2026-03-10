package com.example.lab6_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notebook";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "notes";
    private static final String KEY_ID = "id";
    private static final String KEY_CONTENT = "content";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_notes_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_CONTENT);
        db.execSQL(create_notes_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);
        onCreate(db);
    }

    public Note getNote(int noteId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] {
                String.valueOf(noteId)
        }, null, null, null);
        if(cursor != null) cursor.moveToFirst();
        Note note = new Note(
                cursor.getInt(0),
                cursor.getString(1)
        );
        return note;
    }

    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<>();
        String query = "SELECT id, content FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Note note = new Note(
                    cursor.getInt(0),
                    cursor.getString(1)
            );
            noteList.add(note);
            cursor.moveToNext();
        }
        return noteList;
    }

    public void addNote(String content) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CONTENT, content);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateNote(int id, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CONTENT, content);

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] {
                String.valueOf(id)
        });
        db.close();
    }

    public void deleteNote(int noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] {
                String.valueOf(noteId)
        });
        db.close();
    }
}
