package com.example.milagros.improof;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.milagros.improof.Model.SessionManager;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    SessionManager session;
    Button work;
    Button projects;
    Button habits;
    Button todo;
    Button goals;
    Button character;
    Button stats;
    Button buttons[];
    TextView user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        work = (Button) findViewById(R.id.work);
        projects = (Button) findViewById(R.id.projects);
        habits = (Button) findViewById(R.id.habits);
        todo = (Button) findViewById(R.id.todo);
        goals = (Button) findViewById(R.id.goals);
        character = (Button) findViewById(R.id.character);
        stats = (Button) findViewById(R.id.stats);

        buttons = new Button[] {work, projects, habits, todo, goals, character, stats};

        session = new SessionManager(getApplicationContext());

        user_name = (TextView) findViewById(R.id.name);

        ImageView avatar= (ImageView) findViewById(R.id.avatar);
        avatar.setImageResource(R.drawable.mendicant);

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent workAct = new Intent(getApplicationContext(), WorkActivity.class);
                startActivity(workAct);
            }
        });

        habits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHabitsSection(view);
            }
        });

        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proyect_Activity = new Intent(getApplicationContext(), ProjectsActivity.class);
                startActivity(proyect_Activity);
            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent todo_Activity = new Intent(getApplicationContext(), toDoListActivity.class);
                startActivity(todo_Activity);
            }
        });

        HashMap<String, String> user = session.getUserDetails();
        user_name.setText(user.get("name"));

        setTypeface();
    }

    private void setTypeface(){
        Typeface font = Typeface.createFromAsset(
                getApplicationContext().getAssets(), "fonts/Metropolische.ttf");

        for (Button button : buttons){
            button.setTypeface(font);
        }
    }

    public void showHabitsSection(View view) {
        Intent intent = new Intent(this, HabitsActivity.class);
        startActivity(intent);
    }

}
