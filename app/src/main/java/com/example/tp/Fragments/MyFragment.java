package com.example.tp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tp.Adapter.MyListAdapter;
import com.example.tp.Model.Item;
import com.example.tp.R;
import com.example.tp.SharedPrefrences.SharedPreference;

import java.util.List;


public class MyFragment extends Fragment {
    public MyListAdapter adapter;
    private RecyclerView mylist;
    private List<Item> myitems;
    private TextView txt1;
    private SharedPreference sharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return Initialize(inflater,container,savedInstanceState);
    }


    public void AddItem(){
        myitems = sharedPreference.getItems(getActivity());
        if (mylist.getVisibility() == View.GONE){
            InitizializeRecycle();
        }
        adapter.AddItem(myitems);
    }

    public View Initialize(LayoutInflater inflater,ViewGroup container,
                           Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        mylist = (RecyclerView) view.findViewById(R.id.myitems);
        txt1 = (TextView) view.findViewById(R.id.txt);
        sharedPreference = new SharedPreference();
        myitems = sharedPreference.getItems(getActivity());
        if (myitems.size() != 0) {
            InitizializeRecycle();
        }
        else {
            txt1.setVisibility(View.VISIBLE);
            mylist.setVisibility(View.GONE);
        }
        return view;
    }

    public void InitizializeRecycle(){
        mylist.setVisibility(View.VISIBLE);
        txt1.setVisibility(View.GONE);
        mylist.setHasFixedSize(true);
        adapter = new MyListAdapter(myitems, getActivity());
        mylist.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mylist.setLayoutManager(layoutManager);
    }

}
