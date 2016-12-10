package com.example.milagros.improof;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class WorkActivity extends AppCompatActivity implements View.OnClickListener {

    private Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        ((Button) findViewById(R.id.tomato_start)).setOnClickListener(this);
        ((Button) findViewById(R.id.tomato_stop)).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tomato_start:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.tomato_stop:
                chronometer.stop();
                break;
        }
    }

}
