package com.example.tp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tp.Mydialogs.ZawachDialog;

public class MainActivity extends AppCompatActivity {
    public MyFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new MyFragment();
        FragmentManager fragmanager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmanager.beginTransaction();
        fragmentTransaction.add(R.id.placeHolder, fragment);
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
}
