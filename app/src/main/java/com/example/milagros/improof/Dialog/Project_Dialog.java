package com.example.milagros.improof.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.milagros.improof.ProjectsActivity;
import com.example.milagros.improof.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milagros on 10/12/2016.
 */

public class Project_Dialog {
    public static Dialog dialog;
    public static Context context;
    public  static EditText txtname;
    public  static Spinner spinner;
    public static String cat="" ;
    public static String user_id;

    public static void dialog(final Context _context, String id) {
        context = _context;
        dialog = new Dialog(_context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.new_project);
        txtname = (EditText) dialog.findViewById(R.id.nombreproyect);
        spinner = (Spinner) dialog.findViewById(R.id.category);
        user_id = id;

        final List<String> list = new ArrayList<>();
        list.add("Cultural");
        list.add("Investigation" );
        list.add("Recreational");
        list.add("Science");
        list.add("Language");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        final Button btnAceptar = (Button)dialog.findViewById(R.id.btnAceptar);
        final Button btnCancel= (Button)dialog.findViewById(R.id.btnCancel);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearProyecto();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ProjectsActivity)context).getProjects();
                dialog.dismiss();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cat=list.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                cat="";
            }
        });

        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.WHITE));
        dialog.show();

    }


    public static void crearProyecto() {
        //post para el proyecto
        try {
            String name = txtname.getText().toString().trim();
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", name);
            requestBody.put("category", cat);
            requestBody.put("time", "0.0");
            requestBody.put("user_id", user_id);
            String url = "https://inproof-development.herokuapp.com/projects/";

            JsonObjectRequest request = new JsonObjectRequest(url, requestBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                            ((ProjectsActivity)context).getProjects();
                            dialog.dismiss();
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError  error) {
                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            ((ProjectsActivity)context).getProjects();
                            dialog.dismiss();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(request);

        } catch (Exception e) {
            //Si no hay conecction
            ((ProjectsActivity)context).getProjects();
            dialog.dismiss();
        }

    }
}
