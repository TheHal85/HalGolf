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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
