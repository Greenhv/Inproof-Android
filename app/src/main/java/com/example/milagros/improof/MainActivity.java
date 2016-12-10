package com.example.milagros.improof;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import static android.R.attr.button;
import static com.example.milagros.improof.R.id.todo;
import static com.example.milagros.improof.R.string.project;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    Button work;
    Button projects;
    Button habits;
    Button todo;
    Button goals;
    Button character;
    Button stats;

    Button buttons[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageView avatar= (ImageView) findViewById(R.id.avatar);
        avatar.setImageResource(R.drawable.mendicant);

        work = (Button) findViewById(R.id.work);
        projects = (Button) findViewById(R.id.projects);
        habits = (Button) findViewById(R.id.habits);
        todo = (Button) findViewById(R.id.todo);
        goals = (Button) findViewById(R.id.goals);
        character = (Button) findViewById(R.id.character);
        stats = (Button) findViewById(R.id.stats);

        buttons = new Button[] {work, projects, habits, todo, goals, character, stats};

        setTypeface();
    }

    private void setTypeface(){
        Typeface font = Typeface.createFromAsset(
                getApplicationContext().getAssets(), "fonts/Metropolische.ttf");

        for (Button button : buttons){
            button.setTypeface(font);
        }

    }
}