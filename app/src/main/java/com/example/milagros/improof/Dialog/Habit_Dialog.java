package com.example.milagros.improof.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.milagros.improof.HabitsActivity;
import com.example.milagros.improof.Model.SessionManager;
import com.example.milagros.improof.R;

import org.json.JSONObject;

/**
 * Created by herbert on 12/13/16.
 */

public class Habit_Dialog {
    public static Dialog dialog;
    public static Context context;
    public  static EditText txtname;
    public  static EditText txtgoal;
    public  static Spinner spinner;
    public static String id_usuario;

    public static void dialog(final Context _context, String _id) {
        context = _context;
        dialog = new Dialog(_context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.new_habit);
        txtname = (EditText) dialog.findViewById(R.id.newHabitName);
        txtgoal = (EditText) dialog.findViewById(R.id.newHabitGoalCount);
        id_usuario = _id;
        final Button btnAceptar = (Button)dialog.findViewById(R.id.newHabitAccept);
        final Button btnCancel= (Button)dialog.findViewById(R.id.newHabitCancel);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearHabito();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HabitsActivity)context).getHabits();
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.WHITE));
        dialog.show();
    }


    public static void crearHabito() {
        //post para un habito
        try {
            String name = txtname.getText().toString().trim();
            String goal = txtgoal.getText().toString().trim();
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", name);
            requestBody.put("user_id", id_usuario);
            requestBody.put("count", "0");
            requestBody.put("goal", goal);
            requestBody.put("exp", "20");
            requestBody.put("bronze", "1");
            requestBody.put("silver", "1");
            requestBody.put("gold", "1");
            String url = "https://inproof-development.herokuapp.com/habits/";

            JsonObjectRequest request = new JsonObjectRequest(url, requestBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("volley", response.toString());
                            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                            ((HabitsActivity)context).getHabits();
                            dialog.dismiss();
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("volley", error.toString());
                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            ((HabitsActivity)context).getHabits();
                            dialog.dismiss();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(request);

        } catch (Exception e) {
            //Si no hay conexion
            ((HabitsActivity)context).getHabits();
            dialog.dismiss();
        }

    }
}
