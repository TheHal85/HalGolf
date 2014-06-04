package com.ryan.hallermeier.golfrules.main.models;

import java.util.ArrayList;

/**
 * Created by Hal on 6/3/2014.
 */
public class Hole {

    private int holeId;
    private int courseId;
    private int par;
    private int menDistance;
    private int womenDistance;
    private ArrayList<Shot> shots;

    public Hole(){}

    public Hole(int holeId, int courseId, int par, int menDistance, int womenDistance) {
        super();
        this.holeId = holeId;
        this.courseId = courseId;
        this.par = par;
        this.menDistance = menDistance;
        this.womenDistance = womenDistance;
    }

    //getters & setters

    @Override
    public String toString() {
        return "Hole " + holeId + " Par " + par + " White Tees " + menDistance + " Red Tees " + womenDistance ;
    }

    public int getHoleId() {
        return holeId;
    }

    public void setHoleId(int holeId) {
        this.holeId = holeId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getMenDistance() {
        return menDistance;
    }

    public void setMenDistance(int menDistance) {
        this.menDistance = menDistance;
    }

    public int getWomenDistance() {
        return womenDistance;
    }

    public void setWomenDistance(int womenDistance) {
        this.womenDistance = womenDistance;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void setShots(ArrayList<Shot> shots) {
        this.shots = shots;
    }
}
