package com.example.milagros.improof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.milagros.improof.Model.Tareas;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        final EditText task= (EditText)findViewById(R.id.dotask);
        Button agregar = (Button) findViewById(R.id.btnAceptar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Agregar de verdad al contacto
                Tareas.agregarTarea(task.getText().toString());
                finish();
                Toast.makeText(NewTaskActivity.this,"You Added a new Task to do", Toast.LENGTH_SHORT).show();
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
