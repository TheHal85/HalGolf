package com.ryan.hallermeier.golfrules.main.models;

import java.util.ArrayList;

/**
 * Created by Hal on 6/3/2014.
 */
public class Team {

    private int id;
    private int teamId;
    private int roundId;
    private int teamNumber;
    private ArrayList<Player> players;

    public Team(){}

    public Team(int teamId, int roundId, int teamNumber) {
        super();
        this.teamId = teamId;
        this.roundId = roundId;
        this.teamNumber = teamNumber;
    }

    //getters & setters

    @Override
    public String toString() {
        return "Team";
    }
}
