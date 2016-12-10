package com.example.milagros.improof.Model;

/**
 * Created by Milagros on 10/12/2016.
 */

public class Proyecto {
    private String name;
    private String Category;
    private double time; // en segundos

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
