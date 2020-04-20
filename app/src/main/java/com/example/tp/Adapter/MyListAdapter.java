package com.example.tp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp.Model.Item;
import com.example.tp.R;
import com.example.tp.SharedPrefrences.SharedPreference;


import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ListViewHolder> {
    private List<Item> myitems;
    private SharedPreference sharedPreference;
    private Context contexts;
    public MyListAdapter(List<Item> items,Context context){
        this.myitems = items;
        this.contexts = context;
        sharedPreference = new SharedPreference();
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.BindView(position);
    }



    @Override
    public int getItemCount() {
        return myitems.size();

    }
    public void AddItem(List<Item> items){
        this.myitems = items;
        this.notifyItemInserted(items.size());
    }
    public void RemoveItem(int position){
        this.myitems.remove(position);
        this.notifyItemRemoved(position);
        sharedPreference.removeItem(contexts,position);
    }
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        public TextView header;
        public TextView context;

        public ListViewHolder(View viewHolder){
            super(viewHolder);
            Assign(viewHolder);
        }
        public void Assign(View viewHolder){
            header = (TextView) viewHolder.findViewById(R.id.user);
            context = (TextView) viewHolder.findViewById(R.id.subject);
            viewHolder.setOnLongClickListener(this);
        }
        public void BindView(int position){
            header.setText(myitems.get(position).getHeader());
            context.setText(myitems.get(position).getSubject());
        }
        public boolean onLongClick(View view){
            final AlertDialog.Builder delete = new AlertDialog.Builder(contexts);
            delete.setTitle("Alert")
                    .setMessage("Do you want to delete")
                    .setIcon(android.R.drawable.ic_delete)
                    .setNegativeButton("Cancel",null)
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int position = getAdapterPosition();
                            RemoveItem(position);
                            dialog.dismiss();
                        }
                    })
                    .show();


            return true;
        }
    }
}
