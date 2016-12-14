package com.example.milagros.improof;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.milagros.improof.Adapters.proyectosAdapter;
import com.example.milagros.improof.Model.Proyecto;
import com.example.milagros.improof.Model.Tareas;

import java.util.ArrayList;
import java.util.List;

public class toDoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        final Button newTask= (Button)findViewById(R.id.todo);
        ListView lv= (ListView)findViewById(R.id.listtodo);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Tareas.tareas());
        lv.setAdapter(adapter);

        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(toDoListActivity.this, NewTaskActivity.class);

                startActivity(intent);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // Logica cuando se hace click a un item
                Intent taskactivity = new Intent(getApplicationContext(), TaskActivity.class);
                taskactivity.putExtra("tarea",Tareas.tareas().get(position));
                startActivity(taskactivity);
            }
        });
    }
}
