package com.example.milagros.improof.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.milagros.improof.Model.Habit;
import com.example.milagros.improof.R;

import java.util.ArrayList;

/**
 * Created by herbert on 12/10/16.
 */

public class HabitAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Habit> habitList = new ArrayList<>();

    public HabitAdapter(Context oContext, ArrayList<Habit> habitos) {
        this.mContext = oContext;
        this.habitList = habitos;
    }

    @Override
    public int getCount() {
        return this.habitList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.habitList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class Habito {
        TextView nombre;
        TextView meta;
        TextView avance;
        TextView experiencia;
        TextView bronceRecompenza;
        TextView plataRecompenza;
        TextView oroRecompenza;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Habito habito = new Habito();
        View row;
        row = LayoutInflater.from(mContext).inflate(R.layout.activity_character, parent, false);

        return row;
    }
}