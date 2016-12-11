package com.example.milagros.improof.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.milagros.improof.Model.Proyecto;
import com.example.milagros.improof.R;

import java.util.ArrayList;

/**
 * Created by Milagros on 10/12/2016.
 */

public class proyectosAdapter extends ArrayAdapter<Proyecto> {

    public static ArrayList<Proyecto> pros= new ArrayList<>();

    public proyectosAdapter(Context  context, ArrayList<Proyecto> proyectos) {
        super(context, R.layout.list_proyectview, proyectos);
        pros = proyectos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = null;
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(R.layout.list_proyectview, null);
        Proyecto currentPro =pros.get(position);

        TextView nombre = (TextView) itemView.findViewById(R.id.namepro);
        nombre.setText(currentPro.getName());
        TextView hora = (TextView) itemView.findViewById(R.id.hour);
        TextView min = (TextView) itemView.findViewById(R.id.min);
        double time= currentPro.getTime();
        int h= (int)(time/3600);
        int m= (int)(time-h*3600);
        hora.setText(h+"h");
        min.setText(m+"m");
        return itemView;
    }

}
