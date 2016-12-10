package com.example.milagros.improof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Improof");
        ImageView avatar= (ImageView) findViewById(R.id.avatar);
        avatar.setImageResource(R.drawable.mendicant);


    }
}
