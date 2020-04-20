package com.example.tp.Mydialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tp.Model.Item;
import com.example.tp.MyFragment;
import com.example.tp.R;
import com.example.tp.SharedPrefrences.SharedPreference;

public class ZawachDialog extends AppCompatDialogFragment {
    private EditText header;
    private EditText context;
    private SharedPreference sharedPreference;
    private MyFragment fragment;
    public ZawachDialog(MyFragment fragment){
        this.fragment = fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder mybuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialoglayout,null);
        mybuilder.setView(view)
                .setTitle("MyZawachAdd")
                .setIcon(android.R.drawable.btn_plus)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreference = new SharedPreference();
                        Item myitem = new Item(header.getText().toString(),context.getText().toString());
                        sharedPreference.addItem(getActivity(),myitem);
                        int position = sharedPreference.getItems(getActivity()).size();
                        fragment.AddItem();
                        dialog.cancel();
                    }
                });
        header = (EditText) view.findViewById(R.id.dialogeditText);
        context = (EditText) view.findViewById(R.id.dialogeditText2);
        return mybuilder.create();
    }
}
