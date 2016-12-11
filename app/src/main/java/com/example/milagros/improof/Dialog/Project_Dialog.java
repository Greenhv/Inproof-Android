package com.example.milagros.improof.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
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

    public static void dialog(final Context _context) {
        context = _context;
        dialog = new Dialog(_context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.new_project);
        txtname = (EditText) dialog.findViewById(R.id.nombreproyect);
        spinner = (Spinner) dialog.findViewById(R.id.category);
        List<String> list = new ArrayList<>();
        list.add("Cultural");
        list.add("Investigation" );
        list.add("Recreational");
        list.add("Science");
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
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }


    public static void crearProyecto() {
        //post para el proyecto
        try {
            String name = txtname.getText().toString().trim();
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", name);
            requestBody.put("user_id", "1");
            String url = "https://inproof-development.herokuapp.com/projects/";

            JsonObjectRequest request = new JsonObjectRequest(url, requestBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError  error) {
                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });

//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            Toast.makeText(context, response, Toast.LENGTH_LONG).show();
//                            dialog.dismiss();
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
//                            dialog.dismiss();
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("name", name);
//                    params.put("user_id", "1");
//                    return params;
//                }
//            };
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(request);

        } catch (Exception e) {
            //Si no hay conecction
            dialog.dismiss();
        }

    }
}
