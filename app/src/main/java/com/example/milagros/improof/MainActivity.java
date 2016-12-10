package com.example.milagros.improof;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Improof");
        ImageView avatar= (ImageView) findViewById(R.id.avatar);
        avatar.setImageResource(R.drawable.mendicant);
        Button habitsSection = (Button) findViewById(R.id.habits);

        habitsSection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showHabitsSection(view);
            }
        });
    }

    public void showHabitsSection(View view) {
        Intent intent = new Intent(this, HabitsActivity.class);
        startActivity(intent);
    }



}
