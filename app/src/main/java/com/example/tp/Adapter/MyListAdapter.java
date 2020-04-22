package com.example.tp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp.Activities.MainActivity;
import com.example.tp.Model.Item;
import com.example.tp.R;
import com.example.tp.SharedPrefrences.SharedPreference;


import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ListViewHolder> {
    private List<Item> myitems;
    private SharedPreference sharedPreference;
    private Context contexts;
    private MainActivity listner;
    private int clicked,longclicked;
    public MyListAdapter(List<Item> items,Context context){
        this.myitems = items;
        this.contexts = context;
        sharedPreference = new SharedPreference();
        this.listner = (MainActivity) context;
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
        if (longclicked==clicked){
            listner.RemovedItem();
        }
    }
    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnClickListener {
        public TextView header;
        public TextView context;

        public ListViewHolder(View viewHolder){
            super(viewHolder);
            Assign(viewHolder);
        }
        private void Assign(View viewHolder){
            header = (TextView) viewHolder.findViewById(R.id.user);
            context = (TextView) viewHolder.findViewById(R.id.subject);
            viewHolder.setOnLongClickListener(this);
            viewHolder.setOnClickListener(this);
            FadeAnimation(viewHolder);
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
                            longclicked = position;
                            RemoveItem(position);
                            dialog.dismiss();
                        }
                    })
                    .show();


            return true;
        }
        public void onClick(View view){
            int position = getAdapterPosition();
            clicked = position;
            listner.SetFragment(myitems.get(position));
        }
        private void FadeAnimation(View view) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(1500);
            view.setAnimation(anim);
        }
    }
}
