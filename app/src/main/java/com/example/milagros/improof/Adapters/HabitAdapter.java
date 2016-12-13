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
import java.util.Locale;

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
        row = LayoutInflater.from(mContext).inflate(R.layout.item_habit_layout, parent, false);
        habito.nombre = (TextView) row.findViewById(R.id.habitName);
        habito.meta = (TextView) row.findViewById(R.id.habitGoal);
        habito.avance = (TextView) row.findViewById(R.id.habitCurrentI);
        habito.experiencia = (TextView) row.findViewById(R.id.habitExp);
        habito.bronceRecompenza = (TextView) row.findViewById(R.id.habitBronceReward);
        habito.plataRecompenza = (TextView) row.findViewById(R.id.habitSilverReward);
        habito.oroRecompenza = (TextView) row.findViewById(R.id.habitGoldReward);

        Habit currentHabit = habitList.get(position);

        String meta = String.format(Locale.getDefault(), "%1$d", currentHabit.getGoalIterations());
        String avance = String.format(Locale.getDefault(), "%1$d", currentHabit.getCurrentIterations());
        String experiencia = String.format(Locale.getDefault(), "%1$.2f",currentHabit.getExpReward());
        String bronce = String.format(Locale.getDefault(), "%1$d", currentHabit.getBronceMoneyReward());
        String plata = String.format(Locale.getDefault(), "%1$d", currentHabit.getSilverMoneyReward());
        String oro = String.format(Locale.getDefault(), "%1$d", currentHabit.getGoldMoneyReward());

        habito.nombre.setText(currentHabit.getName());
        habito.meta.setText(meta);
        habito.avance.setText(avance);
        habito.experiencia.setText(experiencia);
        habito.bronceRecompenza.setText(bronce);
        habito.plataRecompenza.setText(plata);
        habito.oroRecompenza.setText(oro);

        return row;
    }
}