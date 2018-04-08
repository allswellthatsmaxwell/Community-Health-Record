package com.baby.baby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        TextView textView = (TextView) findViewById(R.id.textView);
        String s = "hi";
        textView.setText(s);
    }
}
