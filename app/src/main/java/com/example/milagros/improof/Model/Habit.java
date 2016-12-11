package com.example.milagros.improof.Model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by herbert on 12/10/16.
 */

public class Habit {

    private String name;
    private int goalIterations;
    private int currentIterations;
    private double expReward;
    private int bronceMoneyReward;
    private int silverMoneyReward;
    private int goldMoneyReward;

    public Habit() {
    }

    public Habit(String name, int goalI, int currentI, double expReward, int bronceR, int silverR, int goldR) {
        setHabit(name, goalI, currentI, expReward, bronceR, silverR, goldR);
    }

    public Habit(JSONObject data) {
        try {
            String name = data.getString("name");
            int goalI =  Integer.parseInt(data.getString("goalIterations"));
            int currentI = Integer.parseInt(data.getString("currentIterations"));
            double expReward = Double.parseDouble(data.getString("expReward"));
            int bronceR = Integer.parseInt(data.getString("bronceMoneyReward"));
            int silverR =Integer.parseInt(data.getString("silverMoneyReward"));
            int goldR = Integer.parseInt(data.getString("goldMoneyReward"));
            this.setHabit(name, goalI, currentI, expReward, bronceR, silverR, goldR);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void setHabit(String name, int goalI, int currentI, double expReward, int bronceR, int silverR, int goldR) {
        this.setName(name);
        this.setGoalIterations(goalI);
        this.setCurrentIterations(currentI);
        this.setExpReward(expReward);
        this.setBronceMoneyReward(bronceR);
        this.setSilverMoneyReward(silverR);
        this.setGoldMoneyReward(goldR);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoalIterations() {
        return goalIterations;
    }

    public void setGoalIterations(int goalIterations) {
        this.goalIterations = goalIterations;
    }

    public int getCurrentIterations() {
        return currentIterations;
    }

    public void setCurrentIterations(int currentIterations) {
        this.currentIterations = currentIterations;
    }

    public double getExpReward() {
        return expReward;
    }

    public void setExpReward(double expReward) {
        this.expReward = expReward;
    }

    public int getBronceMoneyReward() {
        return bronceMoneyReward;
    }

    public void setBronceMoneyReward(int bronceMoneyReward) {
        this.bronceMoneyReward = bronceMoneyReward;
    }

    public int getSilverMoneyReward() {
        return silverMoneyReward;
    }

    public void setSilverMoneyReward(int silverMoneyReward) {
        this.silverMoneyReward = silverMoneyReward;
    }

    public int getGoldMoneyReward() {
        return goldMoneyReward;
    }

    public void setGoldMoneyReward(int goldMoneyReward) {
        this.goldMoneyReward = goldMoneyReward;
    }
}
