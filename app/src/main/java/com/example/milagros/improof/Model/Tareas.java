package com.example.milagros.improof.Model;

import java.util.ArrayList;

/**
 * Created by Milagros on 13/12/2016.
 */

public class Tareas {
    private static ArrayList<String> tareas= new ArrayList<>();


    public static ArrayList<String> tareas(){
        return tareas;
    }
    public static void agregarTarea(String a){
        if(a!=null){
            tareas.add(a);
        }
    }
    public static void RemoverTarea(String a){
        tareas.remove(a);
    }

}
