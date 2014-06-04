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
        return "Hole";
    }
}
