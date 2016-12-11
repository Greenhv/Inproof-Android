package com.example.milagros.improof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.milagros.improof.Adapters.HabitAdapter;
import com.example.milagros.improof.Model.Habit;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HabitsActivity extends AppCompatActivity {

    private ArrayList<Habit> habitos = new ArrayList<>();
    private ListView habitsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);

        String wsUrl = "https://inproof-development.herokuapp.com/habits/?user_id=1";
        this.fillHabitsList(wsUrl);
        HabitAdapter contactAdapter = new HabitAdapter(getApplicationContext(), this.habitos);
        this.habitsView = (ListView) findViewById(R.id.contact_list);
        this.habitsView.setAdapter(contactAdapter)

    }

    public void fillHabitsList(String wsUrl) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsObRequest = new JsonObjectRequest(Request.Method.GET, wsUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
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
