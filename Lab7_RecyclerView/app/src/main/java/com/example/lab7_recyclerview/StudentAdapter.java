package com.example.lab7_recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.squareup.picasso.Picasso;
import java.io.File;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    List<Student> studentList;
    Context context;
    DatabaseHandler db;

    public StudentAdapter(Context context, List<Student> studentList, DatabaseHandler db) {
        this.context = context;
        this.studentList = studentList;
        this.db = db;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivAvatar,btnMenu;
        TextView tvName,tvEmail;

        public ViewHolder(View itemView){

            super(itemView);

            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            btnMenu = itemView.findViewById(R.id.btn_menu);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_student,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){

        Student student = studentList.get(position);

        holder.tvName.setText(student.getName());
        holder.tvEmail.setText(student.getEmail());

        if(student.getAvatar() != null && !student.getAvatar().isEmpty()){

            Picasso.get().load(student.getAvatar()).into(holder.ivAvatar);
        }else{
            holder.ivAvatar.setImageResource(R.drawable.avatar);
        }

        holder.btnMenu.setOnClickListener(v->{

            PopupMenu popup = new PopupMenu(context,holder.btnMenu);

            popup.getMenu().add("Update");
            popup.getMenu().add("Delete");

            popup.setOnMenuItemClickListener(item->{

                if(item.getTitle().equals("Update")){

                    showUpdateDialog(student,position);

                }else{

                    db.deleteStudent(student.getId());

                    studentList.remove(position);
                    notifyItemRemoved(position);
                }

                return true;
            });

            popup.show();
        });
    }

    private void showUpdateDialog(Student st,int position){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText edtAvatar = new EditText(context);
        edtAvatar.setText(st.getAvatar());

        EditText edtName = new EditText(context);
        edtName.setText(st.getName());

        EditText edtEmail = new EditText(context);
        edtEmail.setText(st.getEmail());

        layout.addView(edtAvatar);
        layout.addView(edtName);
        layout.addView(edtEmail);

        builder.setTitle("Update Student");
        builder.setView(layout);

        builder.setPositiveButton("Save",(d,w)->{

            st.setAvatar(edtAvatar.getText().toString());
            st.setName(edtName.getText().toString());
            st.setEmail(edtEmail.getText().toString());

            db.updateStudent(st);

            notifyItemChanged(position);
        });

        builder.setNegativeButton("Cancel",null);

        builder.show();
    }

    @Override
    public int getItemCount(){
        return studentList.size();
    }
}
