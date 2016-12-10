package com.example.milagros.improof;

/**
 * Created by herbert on 12/10/16.
 */

public class Habit {

    private String name;
    private int goalIterations;
    private int curentIterations;
    private double expReward;
    private int bronceMoneyReward;
    private int silverMoneyReward;
    private int goldMoneyReward;

    public Habit() {
    }

    public Habit(String name, int goalI, int currentI, double expReward, int bronceR, int silverR, int goldR) {
        setHabit(name, goalI, currentI, expReward, bronceR, silverR, goldR);
    }

    private void setHabit(String name, int goalI, int currentI, double expReward, int bronceR, int silverR, int goldR) {
        this.setName(name);
        this.setGoalIterations(goalI);
        this.setCurentIterations(currentI);
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

    public int getCurentIterations() {
        return curentIterations;
    }

    public void setCurentIterations(int curentIterations) {
        this.curentIterations = curentIterations;
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
