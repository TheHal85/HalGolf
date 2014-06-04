package com.ryan.hallermeier.golfrules.main.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hal on 6/3/2014.
 */
public class Round {

    private int roundId;
    private int courseId;
    private Date date;
    private ArrayList<Team> teams;

    public Round(){}

    public Round(int roundId, int courseId,  Date date) {
        super();
        this.roundId = roundId;
        this.courseId = courseId;
        this.date = date;
    }

    //getters & setters

    @Override
    public String toString() {
        return "Round";
    }
}
