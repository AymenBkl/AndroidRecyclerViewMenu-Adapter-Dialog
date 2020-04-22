package com.example.tp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;

import com.example.tp.Fragments.MyFragment;
import com.example.tp.Fragments.MyFragment2;
import com.example.tp.Model.Item;
import com.example.tp.Mydialogs.ZawachDialog;
import com.example.tp.R;

public class MainActivity extends AppCompatActivity {
    private MyFragment fragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmanager;
    private MyFragment2 fragment2;
    private int displayMode;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (ScrollView) findViewById(R.id.scrview);
        fragment = new MyFragment();
        fragment2 = new MyFragment2();
        fragmanager = getSupportFragmentManager();
        displayMode = getResources().getConfiguration().orientation;
        fragmentTransaction = fragmanager.beginTransaction();
        fragmentTransaction.add(R.id.placeHolder, fragment);
        if (displayMode == Configuration.ORIENTATION_LANDSCAPE){
            fragmentTransaction.add(R.id.placeHolder2,fragment2);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater myzawach = getMenuInflater();
        myzawach.inflate(R.menu.twits,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.zawach:
                ZawachDialog zawachDialog = new ZawachDialog(fragment);
                zawachDialog.show(getSupportFragmentManager(),"showZawach");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        displayMode = newConfig.orientation;
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            scrollView.setVisibility(View.GONE);
            FragmentTransaction fragmentTransaction = fragmanager.beginTransaction();
            fragmentTransaction.replace(R.id.placeHolder2,fragment2);
            fragmentTransaction.commit();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            scrollView.setVisibility(View.GONE);
            FragmentTransaction fragmentTransaction = fragmanager.beginTransaction();
            MyFragment2 frag2 = (MyFragment2) fragmanager.findFragmentById(R.id.placeHolder2);
            if (frag2 != null || frag2.isInLayout()){
                fragmentTransaction.remove(frag2);
                fragmentTransaction.commit();
            }

        }
    }

    public void SetFragment(Item item){

        if (displayMode == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(MainActivity.this, DisplayItem.class);
            intent.putExtra("header",item.getHeader());
            intent.putExtra("context",item.getSubject());
            startActivity(intent);
        }
        else {
            scrollView.setVisibility(View.VISIBLE);
            fragment2.SetElements(item);
            FragmentTransaction fragmentTransaction = fragmanager.beginTransaction();
            fragmentTransaction.replace(R.id.placeHolder2,fragment2);
            fragmentTransaction.commit();
        }

    }
    public void RemovedItem(){
        if (displayMode == Configuration.ORIENTATION_LANDSCAPE){
            FragmentTransaction fragmentTransaction = fragmanager.beginTransaction();
            MyFragment2 frag2 = (MyFragment2) fragmanager.findFragmentById(R.id.placeHolder2);
            if (frag2 != null || frag2.isInLayout()){
                fragmentTransaction.remove(frag2);
                fragmentTransaction.commit();
            }
        }
    }
}
