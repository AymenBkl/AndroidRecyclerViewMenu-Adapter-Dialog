package com.example.tp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tp.R;

public class DisplayItem extends AppCompatActivity {
    private TextView txt1;
    private TextView txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_fragment);
        Intent intent = getIntent();
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        String header = intent.getStringExtra("header");
        String context = intent.getStringExtra("context");
        txt1.setText(header);
        txt2.setText(context);
    }
}
