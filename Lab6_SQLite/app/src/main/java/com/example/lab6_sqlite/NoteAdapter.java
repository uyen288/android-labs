package com.example.lab6_sqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    List<Note> noteList;
    Context context;
    DatabaseHandler db;

    public NoteAdapter(Context context, List<Note> noteList, DatabaseHandler db) {
        this.context = context;
        this.noteList = noteList;
        this.db = db;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNote;
        ImageView btnMenu;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNote = itemView.findViewById(R.id.tv_note);
            btnMenu = itemView.findViewById(R.id.btn_menu);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_note, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Note note = noteList.get(position);

        holder.tvNote.setText(note.getContent());

        holder.btnMenu.setOnClickListener(v -> {

            PopupMenu popup = new PopupMenu(context, holder.btnMenu);

            popup.getMenu().add("Sửa ghi chú");
            popup.getMenu().add("Xóa");

            popup.setOnMenuItemClickListener(item -> {

                if(item.getTitle().equals("Sửa ghi chú")){

                    showEditDialog(note, position);

                } else if(item.getTitle().equals("Xóa")){

                    db.deleteNote(note.getId());

                    noteList.remove(position);
                    notifyItemRemoved(position);
                }

                return true;
            });

            popup.show();
        });
    }

    private void showEditDialog(Note note, int position){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        EditText edt = new EditText(context);
        edt.setText(note.getContent());

        builder.setTitle("Sửa ghi chú");
        builder.setView(edt);

        builder.setPositiveButton("Lưu", (dialog, which) -> {

            String newText = edt.getText().toString();

            db.updateNote(note.getId(), newText);

            noteList.get(position).setContent(newText);

            notifyItemChanged(position);
        });

        builder.setNegativeButton("Hủy", null);

        builder.show();
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
