package com.coronavirus.demo.model;

public class LocationStats {

    private String state;
    private double recovered;
    private double active;
    private long death;
    private long totalDeath;
    private long totalRecovered;
    private long totalActive;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getRecovered() {
        return recovered;
    }

    public void setRecovered(double recovered) {
        this.recovered = recovered;
    }

    public double getActive() {
        return active;
    }

    public void setActive(double active) {
        this.active = active;
    }

    public long getDeath() {
        return death;
    }

    public void setDeath(long death) {
        this.death = death;
    }

    public long getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(long totalDeath) {
        this.totalDeath = totalDeath;
    }

    public long getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(long totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public long getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(long totalActive) {
        this.totalActive = totalActive;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", recovered=" + recovered +
                ", active=" + active +
                ", death=" + death +
                ", totalDeath=" + totalDeath +
                ", totalRecovered=" + totalRecovered +
                ", totalActive=" + totalActive +
                '}';
    }
}
