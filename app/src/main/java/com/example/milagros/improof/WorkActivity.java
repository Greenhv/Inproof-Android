package com.example.milagros.improof;

import android.app.ProgressDialog;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.milagros.improof.Model.Proyecto;
import com.example.milagros.improof.Model.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class WorkActivity extends AppCompatActivity {
    private  CountDownTimer chrono;
    private Spinner spinProject;
    private Spinner milestone;
    private TextView session;
    private TextView chronotext;
    Button start;
    Button surrender;
    private static final String FORMAT = "%02d:%02d";
    private static ArrayList<Proyecto> proyectos= new ArrayList<>();
    ProgressDialog load;
    private Integer nSes;
    private Integer maxSession;
    private int projectselected;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        start=(Button) findViewById(R.id.tomato_start);
        surrender=(Button) findViewById(R.id.tomato_stop);
        spinProject=(Spinner)findViewById(R.id.proname);
        milestone=(Spinner)findViewById(R.id.milestone);
        session= (TextView)findViewById(R.id.session);
        sessionManager = new SessionManager(getApplicationContext());

        final String sessions=session.getText().toString();
        String token[]=sessions.split("/");
        nSes= Integer.parseInt(token[0]);
        maxSession= Integer.parseInt(token[1]);

        List<String> list2 = new ArrayList<>();
        list2.add("Part 1");
        list2.add("Part 2");
        list2.add("Part 3");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(WorkActivity.this,
                android.R.layout.simple_spinner_item, list2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        milestone.setAdapter(dataAdapter2);

        chronotext=(TextView)findViewById(R.id.chrono);
        chronotext.setText("25:00");



        chrono=new CountDownTimer(1500000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                chronotext.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                Toast.makeText(WorkActivity.this,"Session Done!",Toast.LENGTH_LONG).show();
                nSes++;
                session.setText(nSes+"/"+maxSession);
                chronotext.setText("25:00");
                start.setVisibility(View.VISIBLE);
                surrender.setVisibility(View.GONE);
                double a= proyectos.get(projectselected).getTime();
                proyectos.get(projectselected).setTime(a+25*60);// segundos
            }
        };
        spinProject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                projectselected=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.start();
                start.setVisibility(View.GONE);
                surrender.setVisibility(View.VISIBLE);
                milestone.setEnabled(false);
                spinProject.setEnabled(false);
            }
        });
        surrender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // se reinicia
                chronotext.setText("25:00");
                Toast.makeText(WorkActivity.this,"You give up!",Toast.LENGTH_LONG).show();
                chrono.cancel();
                start.setVisibility(View.VISIBLE);
                surrender.setVisibility(View.GONE);
                milestone.setEnabled(true);
                spinProject.setEnabled(true);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getProjects();
    }
    public void populatespinner(){
        List<String> list = new ArrayList<>();
        for(int i=0; i<proyectos.size();i++){
            list.add(proyectos.get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(WorkActivity.this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinProject.setAdapter(dataAdapter);
    }
    public void getProjects(){
        this.proyectos.clear();
        load=  new ProgressDialog(WorkActivity.this);
        load.setCancelable(false);
        load.requestWindowFeature(Window.FEATURE_NO_TITLE);
        load.setMessage("Cargando");
        load.show();
        RequestQueue cola = Volley.newRequestQueue(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String user_id = user.get("id");
        String url = String.format(Locale.getDefault(), "https://inproof-development.herokuapp.com/projects/?user_id=%1$s", user_id);
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
                                p.setCategory(proyecto.getString("category"));
                                p.setTime(proyecto.getDouble("time"));
                                proyectos.add(p);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        populatespinner();
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
