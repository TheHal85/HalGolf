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

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
