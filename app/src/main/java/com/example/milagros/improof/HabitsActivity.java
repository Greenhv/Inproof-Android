package com.example.milagros.improof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.milagros.improof.Adapters.HabitAdapter;
import com.example.milagros.improof.Dialog.Habit_Dialog;
import com.example.milagros.improof.Model.Habit;
import com.example.milagros.improof.Model.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class HabitsActivity extends AppCompatActivity {

    private ArrayList<Habit> habitos = new ArrayList<>();
    private ListView habitsView;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);
        session = new SessionManager(getApplicationContext());
        Button button = (Button) findViewById(R.id.startNewHabitBtn);
        HashMap<String, String> user = session.getUserDetails();
        final String user_id = user.get("id");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Habit_Dialog.dialog(HabitsActivity.this, user_id);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getHabits();
    }

    public void getHabits() {
        HashMap<String, String> user = session.getUserDetails();
        String id = user.get("id");
        String wsUrl = String.format(Locale.getDefault(), "https://inproof-development.herokuapp.com/habits/?user_id=%1$s", id);
        fillHabitsList(wsUrl);
    }

    private void populateHabitList() {
        HabitAdapter habitAdapter = new HabitAdapter(getApplicationContext(), this.habitos);
        this.habitsView = (ListView) findViewById(R.id.habitList);
        this.habitsView.setAdapter(habitAdapter);
    }

    public void fillHabitsList(String wsUrl) {
        this.habitos = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsObRequest = new JsonObjectRequest(Request.Method.GET, wsUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("volley", response.toString());
                try {
                    JSONArray habits = response.getJSONArray("habits");
                    for (int i = 0; i < habits.length() ; i++) {
                        JSONObject habitJson = (JSONObject) habits.get(i);
                        Habit habit = new Habit(habitJson);
                        habitos.add(habit);
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                populateHabitList();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", "An error ocurred while procesing your request.");
            }
        });

        jsObRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsObRequest);
    }
}
