package com.example.milagros.improof;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.example.milagros.improof.Adapters.proyectosAdapter;
import com.example.milagros.improof.Dialog.Project_Dialog;
import com.example.milagros.improof.Model.Proyecto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProjectsActivity extends AppCompatActivity {

    private static ArrayList<Proyecto> proyectos= new ArrayList<>();
    private String jsonResponse;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyects);
        final Button button= (Button) findViewById(R.id.newpro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Project_Dialog.dialog(ProjectsActivity.this);
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        getProjects();
    }

    private void populateListViewproyect() {
        final ArrayAdapter<Proyecto> adapter = new proyectosAdapter(this, proyectos);
        final ListView listproyect = (ListView) findViewById(R.id.listproyects);
        listproyect.setAdapter(adapter);
        listproyect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // Logica cuando se hace click a un item

            }
        });
    }
    public void getProjects(){
        this.proyectos.clear();
        load=  new ProgressDialog(ProjectsActivity.this);
        load.setCancelable(false);
        load.requestWindowFeature(Window.FEATURE_NO_TITLE);
        load.setMessage("Actualizando");
        load.show();
        RequestQueue cola = Volley.newRequestQueue(getApplicationContext());
        String url = "https://inproof-development.herokuapp.com/projects/?user_id=1";
        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("volley", response.toString());
                        try {
                            JSONArray projects = response.getJSONArray("projects");
                            for (int i = 0; i < projects.length(); i++) {
                                JSONObject proyecto = (JSONObject) projects.get(i);
                                Proyecto p = new Proyecto();
                                p.setName(proyecto.getString("name"));
                                p.setCategory("Science");
                                p.setTime(0.0);
                                proyectos.add(p);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        populateListViewproyect();
                        load.dismiss();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley", "Ocurrió un error!");
                    }
                });

        sr.setRetryPolicy(new DefaultRetryPolicy(
                6000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        cola.add(sr);

    }
}
