package com.example.milagros.improof.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.milagros.improof.Model.Proyecto;
import com.example.milagros.improof.R;

import java.util.ArrayList;

/**
 * Created by Milagros on 10/12/2016.
 */

public class proyectosAdapter extends ArrayAdapter<Proyecto> {

    public proyectosAdapter(Context  context, ArrayList<Proyecto> proyectos) {
        super(context, R.layout.list_proyectview, proyectos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = null;

        if (itemView == null) {

            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.list_proyectview, null);
        } else {
            itemView = convertView;
        }


        return itemView;
    }

}
