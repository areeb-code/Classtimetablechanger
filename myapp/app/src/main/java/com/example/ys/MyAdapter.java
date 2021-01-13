package com.example.ys;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Studentinfo> todoList = new ArrayList<Studentinfo>();
    Context context;
    public MyAdapter(Context ct, ArrayList<Studentinfo> todo ){
        context=ct;
        todoList=todo;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_rowview,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Studentinfo todo = todoList.get(position);
        holder.tx1.setText(todo.lName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(v.getContext(), DataDisplay.class);
                intent.putExtra("obj", todo);
                v.getContext().startActivity(intent);

            }
        });
        holder.tx2.setText(todo.status);
        holder.tx3.setText(todo.RollNo);
        holder.tx4.setText(todo.Section);
        holder.tx5.setText(todo.fName);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tx1,tx2,tx3,tx4,tx5;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


        tx1=itemView.findViewById(R.id.textView);
        tx2=itemView.findViewById(R.id.textView2);
        tx3=itemView.findViewById(R.id.dateText);
        tx4=itemView.findViewById(R.id.textView6);
            tx5=itemView.findViewById(R.id.nameTxt);


            }
    }

}
