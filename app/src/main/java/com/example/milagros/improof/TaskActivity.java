package com.example.milagros.improof;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milagros.improof.Model.Tareas;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Intent intent= getIntent();
        final String tarea= intent.getExtras().getString("tarea");
        TextView desc=(TextView)findViewById(R.id.description);
        desc.setText(tarea);
        Button end= (Button)findViewById(R.id.btnComplete);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tareas.RemoverTarea(tarea);
                finish();
            }
        });
        Button volver = (Button) findViewById(R.id.btnCancel);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
